/*
 * Copyright (c) 2009 tarchan. All rights reserved.
 */
package com.mac.tarchan.desktop.event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * MouseSwipeHandler
 * 
 * @author tarchan
 */
public class MouseSwipeHandler extends MouseAdapter
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
	@Override
	public void mouseDragged(MouseEvent evt)
	{
		end = evt;
		fireMouseSwipeEvent();
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
