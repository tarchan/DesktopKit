/**
 * Copyright (c) 2010 tarchan. All rights reserved.
 */
package com.mac.tarchan.desktop;

import java.io.FileNotFoundException;
import java.net.URI;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * InputBox
 *
 * @author tarchan
 */
public class InputBox
{
	/** ログ */
	private static final Log log = LogFactory.getLog(InputBox.class);

	/** ブラウザ */
	private static final String[] browsers = {"google-chrome", "firefox", "opera", "konqueror", "epiphany", "seamonkey", "galeon", "kazehakase", "mozilla"};

	static
	{
		log.debug("ダイアログを初期化します。");
		UIManager.put("OptionPane.yesButtonText", "はい");
		UIManager.put("OptionPane.noButtonText", "いいえ");
		UIManager.put("OptionPane.okButtonText", "OK");
		UIManager.put("OptionPane.cancelButtonText", "キャンセル");
		UIManager.put("OptionPane.inputDialogTitle", "");	// 入力
		UIManager.put("OptionPane.messageDialogTitle", "");	// 警告
		UIManager.put("OptionPane.titleText", "");			// 確認
	}

	/**
	 * 警告ダイアログを表示します。
	 * 
	 * @param message メッセージ
	 * @see JOptionPane#showMessageDialog(java.awt.Component, Object)
	 * @see JOptionPane#INFORMATION_MESSAGE
	 */
	public static void alert(Object message)
	{
		JOptionPane.showMessageDialog(null, message);
	}

	/**
	 * 確認ダイアログを表示します。
	 * 
	 * @param message メッセージ
	 * @return OK の場合は true、そうでない場合は false
	 * @see JOptionPane#showConfirmDialog(java.awt.Component, Object, String, int)
	 * @see JOptionPane#QUESTION_MESSAGE
	 */
	public static boolean confirm(Object message)
	{
		int inputValue = JOptionPane.showConfirmDialog(null, message,
			UIManager.getString("OptionPane.titleText"), JOptionPane.OK_CANCEL_OPTION);
		return inputValue == JOptionPane.OK_OPTION;
	}

	/**
	 * 入力ダイアログを表示します。
	 * 
	 * @param message メッセージ
	 * @param text 初期入力テキスト
	 * @return OK の場合は入力したテキスト、そうでない場合は null
	 * @see JOptionPane#showInputDialog(Object, Object)
	 * @see JOptionPane#QUESTION_MESSAGE
	 */
	public static String prompt(Object message, Object text)
	{
		String inputValue = JOptionPane.showInputDialog(message, text);
		return inputValue;
	}

	/**
	 * 入力ダイアログを表示します。
	 * 
	 * @param message メッセージ
	 * @param text 初期入力テキスト
	 * @param title タイトル
	 * @return OK の場合は入力したテキスト、そうでない場合は null
	 * @see JOptionPane#showConfirmDialog(java.awt.Component, Object, String, int)
	 * @see JOptionPane#QUESTION_MESSAGE
	 */
	public static String prompt(Object message, Object text, String title)
	{
		String inputValue = JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);
		return inputValue;
	}

	/**
	 * 指定された URL をブラウザで表示します。
	 * 
	 * @param url ブラウザで表示する URL
	 * @see <a href="http://www.centerkey.com/java/browser/">Bare Bones Browser Launch for Java</a>
	 */
	public static void browse(String url)
	{
		try
		{
			// attempt to use Desktop library from JDK 1.6+ (even if on 1.5)
			Class<?> d = Class.forName("java.awt.Desktop");
			d.getDeclaredMethod("browse", new Class[]{URI.class})
				.invoke(d.getDeclaredMethod("getDesktop")
				.invoke(null), new Object[]{URI.create(url)});
			// above code mimics:
			// java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		}
		catch (Exception ignore)
		{
			if (log.isTraceEnabled()) log.trace(ignore);
			// library not available or failed
			String osName = System.getProperty("os.name");
			try
			{
				if (osName.startsWith("Mac OS"))
				{
					Class.forName("com.apple.eio.FileManager").getDeclaredMethod("openURL", new Class[]{String.class}).invoke(null, new Object[]{url});
				}
				else if (osName.startsWith("Windows"))
				{
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
				}
				else
				{
					// assume Unix or Linux
					boolean found = false;
					for (String browser : browsers)
					{
						found = Runtime.getRuntime().exec(new String[]{"which", browser}).waitFor() == 0;
						if (found) Runtime.getRuntime().exec(new String[]{browser, url});
					}
					if (!found) throw new FileNotFoundException(Arrays.toString(browsers));
				}
			}
			catch (Exception x)
			{
				throw new RuntimeException(x);
			}
		}
	}
}
