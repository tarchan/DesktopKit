/*
 * Copyright (c) 2011 tarchan. All rights reserved.
 */
package com.mac.tarchan.desktop;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;

/**
 * ステータスバーを生成します。
 */
@SuppressWarnings("serial")
public class StatusBar extends Box
{
	/**
	 * 空のステータスバーを構築します。
	 */
	public StatusBar()
	{
		super(BoxLayout.LINE_AXIS);
		setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
	}

	/**
	 * セパレータを追加します。
	 * 
	 * @return StatusBar
	 */
	public StatusBar addSeparator()
	{
		JSeparator sep = new JSeparator(JSeparator.VERTICAL);
		sep.setMaximumSize(new Dimension(2, 16));
		sep.setMinimumSize(new Dimension(2, 16));
//		sep.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

		add(Box.createRigidArea(new Dimension(4, 4)));
		add(sep);
		add(Box.createRigidArea(new Dimension(4, 4)));

		return this;
	}
}
