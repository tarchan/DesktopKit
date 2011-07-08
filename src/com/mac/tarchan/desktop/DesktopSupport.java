/*
 * Copyright (c) 2009 tarchan. All rights reserved.
 */
package com.mac.tarchan.desktop;

import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Window;

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
	 * 
	 * @see UIManager#setLookAndFeel(String)
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
	 * 
	 * @see <a href="http://java.sun.com/javase/ja/6/docs/ja/technotes/guides/net/proxies.html">Java ネットワークとプロキシ </a>
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
		boolean isEventDispatchThread = EventQueue.isDispatchThread();
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
	 * 指定されたクラスのオーナーコンテナを返します。
	 * 
	 * @param c コンポーネント
	 * @return オーナーコンテナ
	 */
	public static <T> T componentOwner(Component c)
	{
		for (Container p = c.getParent(); p != null; p = p.getParent())
		{
			try
			{
				@SuppressWarnings("unchecked")
				T owner = (T)p;
				return owner;
			}
			catch (Throwable ignore)
			{
			}
		}
		return null;
	}

	/**
	 * 指定されたコマンドをイベントディスパッチスレッドで実行します。
	 * 
	 * @param command 実行したいコマンド
	 * @see EventQueue#invokeLater(Runnable)
	 */
	public static void execute(Runnable command)
	{
		EventQueue.invokeLater(command);
	}

	/**
	 * ウインドウを安全に表示します。
	 * 
	 * @param window ウインドウ
	 * @see EventQueue#invokeLater(Runnable)
	 */
	public static void show(final Window window)
	{
		execute(new Runnable()
		{
			public void run()
			{
				window.setVisible(true);
			}
		});
	}

	/**
	 * ウインドウを安全に表示します。
	 * 
	 * @param window ウインドウ
	 * @see EventQueue#invokeLater(Runnable)
	 * @deprecated {@link #show(Window)} を使用します。
	 */
	public static void windowVisible(final Window window)
	{
		show(window);
	}

	/**
	 * 指定されたコマンドをシャットダウン時に呼び出します。
	 * 
	 * @param command 実行したいコマンド
	 * @see Runtime#addShutdownHook(Thread)
	 */
	public static void shutdown(Runnable command)
	{
		Thread hook = new Thread(command);
		Runtime.getRuntime().addShutdownHook(hook);
	}
}
