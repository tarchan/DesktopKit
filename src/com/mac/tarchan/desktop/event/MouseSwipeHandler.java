/*
 * MouseSwipeHandler.java
 * OwlPreview
 * 
 * Copyright(c)2009 NTT COMWARE BILLING SOLUTIONS CORPORATION All Rights Reserved.
 * 
 * 2009/08/26
 */
package com.mac.tarchan.desktop.event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * MouseSwipeHandler
 * 
 * @author v-togura
 */
public class MouseSwipeHandler extends MouseAdapter implements MouseMotionListener
{
	/** スワイプ開始イベント */
	MouseEvent start;

	/** スワイプ終端イベント */
	MouseEvent end;

	/** イベントリスナー */
	ArrayList<MouseMotionListener> list = new ArrayList<MouseMotionListener>();

	/**
	 * スワイプの開始
	 */
	@Override
	public void mousePressed(MouseEvent evt)
	{
		start = end = evt;
	}

	/**
	 * スワイプの継続
	 */
	public void mouseDragged(MouseEvent evt)
	{
		end = evt;
		fireMouseSwipeEvent();
	}

	/* (非 Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	public void mouseMoved(MouseEvent e)
	{
		// 未使用
	}

	/**
	 * MouseSwipeEvent を発生します。
	 */
	protected void fireMouseSwipeEvent()
	{
		MouseSwipeEvent evt = new MouseSwipeEvent(start, end);
		System.out.println("fireMouseSwipeEvent: " + evt);
		for (MouseMotionListener l : list)
		{
			l.mouseDragged(evt);
		}
	}
}
