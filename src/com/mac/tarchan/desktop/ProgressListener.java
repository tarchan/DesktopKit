/*
 * Copyright (c) 2012 tarchan. All rights reserved.
 */
package com.mac.tarchan.desktop;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JProgressBar;

/**
 * プログレスバーを更新するイベントハンドラです。
 * 
 * @see javax.swing.SwingWorker#addPropertyChangeListener(PropertyChangeListener)
 */
public class ProgressListener implements PropertyChangeListener
{
	private JProgressBar bar;

	/**
	 * 指定されたプログレスバーを更新するイベントハンドラを作成します。
	 * 
	 * @param bar プログレスバー
	 */
	public ProgressListener(JProgressBar bar)
	{
		this.bar = bar;
		bar.setIndeterminate(true);
		bar.setVisible(true);
	}

	public void propertyChange(PropertyChangeEvent evt)
	{
		if ("progress".equals(evt.getPropertyName()))
		{
			bar.setIndeterminate(false);
			bar.setValue((Integer)evt.getNewValue());
		}
	}
}
