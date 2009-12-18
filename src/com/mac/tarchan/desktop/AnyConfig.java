/*
 * Copyright (c) 2009 tarchan. All rights reserved.
 */
package com.mac.tarchan.desktop;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * プロパティファイルやレジストリを解析するために使用します。
 * 
 * @author tarchan
 * @see Properties
 * @see Preferences
 * @see ResourceBundle
 */
public class AnyConfig extends Properties
{
	/** シリアルバージョンID */
	private static final long serialVersionUID = 1L;

	/** プロパティファイルの拡張子 */
	public static final String PROPERTIES_SUFFIX = ".properties";

	/**
	 * プロパティファイルを読み込みます。
	 * 
	 * @param name プロパティファイル名
	 * @throws IOException プロパティファイルを読み込めない場合
	 */
	public void load(String name) throws IOException
	{
		if (name.endsWith(PROPERTIES_SUFFIX))
		{
			// プロパティファイルとみなす
			load(new FileInputStream(name));
		}
		else
		{
			// XMLファイルとみなす
			loadFromXML(new FileInputStream(name));
		}
	}

	/**
	 * プロパティファイルを保存します。
	 * 
	 * @param name プロパティファイル名
	 * @param comment コメント
	 * @throws IOException プロパティファイルを保存できない場合
	 */
	public void store(String name, String comment) throws IOException
	{
		if (name.endsWith(PROPERTIES_SUFFIX))
		{
			// プロパティファイルとみなす
			store(new FileOutputStream(name), comment);
		}
		else
		{
			// XMLファイルとみなす
			storeToXML(new FileOutputStream(name), comment);
		}
	}

	/**
	 * ユーザー設定を読み込みます。
	 * 
	 * @param c ユーザー設定ノードを必要とするパッケージのクラス 
	 * @throws BackingStoreException ユーザー設定を読み込めない場合
	 */
	public void load(Class<?> c) throws BackingStoreException
	{
		Preferences user = Preferences.userNodeForPackage(c);
//		System.out.format("user=%s, %s, %s, %s\n", user.name(), user.absolutePath(), user.isUserNode(), user.nodeExists(""));
		for (String key : user.keys())
		{
			String value = user.get(key, getProperty(key));
			setProperty(key, value);
//			System.out.format("%s=%s\n", key, value);
		}
	}

	/**
	 * ユーザー設定を保存します。
	 * 
	 * @param c ユーザー設定ノードを必要とするパッケージのクラス 
	 * @param comment コメント
	 */
	public void store(Class<?> c, String comment)
	{
		Preferences user = Preferences.userNodeForPackage(c);
//		System.out.println("user=" + user);
		user.put("comment", comment);
		for (Object obj : keySet())
		{
			String key = (String)obj;
			String value = getProperty(key);
			user.put(key, value);
		}
	}
}
