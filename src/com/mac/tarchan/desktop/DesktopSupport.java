/*
 * Copyright (c) 2009 tarchan. All rights reserved.
 */
package com.mac.tarchan.desktop;

import java.awt.Window;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * DesktopSupport
 * 
 * @author tarchan
 */
public class DesktopSupport
{
	/**
	 * システム Look&Feel を使用します。
	 */
	public static void useSystemLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception x)
		{
			throw new RuntimeException(x);
		}
	}

	/**
	 * システムプロキシを使用します。
	 */
	public static void useSystemProxies()
	{
		System.setProperty("java.net.useSystemProxies", "true");
	}

	/**
	 * イベントディスパッチスレッドかどうか表示します。
	 */
	public static void printEventDispatchThread()
	{
		boolean isEventDispatchThread = SwingUtilities. isEventDispatchThread();
		if (isEventDispatchThread)
		{
			System.out.println("isEventDispatchThread: " + isEventDispatchThread + ", " + Thread.currentThread());
		}
		else
		{
			System.err.println("isEventDispatchThread: " + isEventDispatchThread + ", " + Thread.currentThread());
		}
	}

	/**
	 * ウインドウをイベントディスパッチスレッドで可視化します。
	 * 
	 * @param window ウインドウ
	 * @see SwingUtilities#invokeLater(Runnable)
	 */
	public static void windowVisible(final Window window)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				if (!SwingUtilities. isEventDispatchThread()) throw new IllegalStateException("Is Not EventDispatchThread");
				window.setVisible(true);
			}
		});
	}
}
