/*
 * DoubleClickHandler.java
 * DesktopKit
 * 
 * Copyright(c)2009 NTT COMWARE BILLING SOLUTIONS CORPORATION All Rights Reserved.
 * 
 * 2009/12/16
 */
package com.mac.tarchan.desktop.event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * DoubleClickHandler
 * 
 * @author v-togura
 */
public class DoubleClickHandler extends MouseAdapter
{
	private static final Log log = LogFactory.getLog(DoubleClickHandler.class);

	/** イベントリスナー */
	protected MouseListener mouseClicked;

	/**
	 * ダブルクリックのイベントリスナーを作成します。
	 * 
	 * @param mouseClicked イベントリスナー
	 */
	public DoubleClickHandler(MouseListener mouseClicked)
	{
		this.mouseClicked = mouseClicked;
	}

	/**
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent e)
	{
		log.debug("click=" + e);
		if (e.getClickCount() == 2) fireDoubleClicked(e);
	}

	/**
	 * イベントリスナーを呼び出します。
	 * 
	 * @param e イベント
	 */
	protected void fireDoubleClicked(MouseEvent e)
	{
		log.debug("dblclick=" + e);
		mouseClicked.mouseClicked(e);
	}
}
