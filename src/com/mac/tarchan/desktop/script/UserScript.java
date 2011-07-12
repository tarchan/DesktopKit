/*
 * UserScript.java
 * DesktopKit
 * 
 * Created by tarchan on 2011/07/12.
 * Copyright (c) 2011 tarchan. All rights reserved.
 */
package com.mac.tarchan.desktop.script;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Locale;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * UserScript
 * 
 * @see <a href="http://java.sun.com/javase/ja/6/docs/ja/technotes/guides/scripting/programmer_guide/index.html">Java スクリプトプログラマーズガイド</a>
 */
public class UserScript
{
	/** インタプリタ */
	protected ScriptEngine engine;

	/**
	 * UserScript
	 * 
	 * @param file スクリプトファイル
	 * @throws ScriptException スクリプトが実行できない場合
	 * @throws IOException スクリプトファイルが読めない場合
	 */
	public UserScript(File file) throws ScriptException, IOException
	{
		String extension = getExtension(file.getName());
		ScriptEngineManager manager = new ScriptEngineManager();
		engine = manager.getEngineByExtension(extension);
		FileReader reader = null;
		try
		{
			reader = new FileReader(file);
			eval(reader);
		}
		catch (ScriptException x)
		{
			throw x;
		}
		finally
		{
			if (reader != null) reader.close();
		}
	}

	/**
	 * スクリプトエンジンを返します。
	 * 
	 * @return スクリプトエンジン
	 */
	public ScriptEngine getEngine()
	{
		return engine;
	}

	/**
	 * スクリプトエンジンのバージョンを返します。
	 * 
	 * @return バージョン
	 */
	public String getEngineVersion()
	{
		ScriptEngineFactory factory = engine.getFactory();
		return factory.getEngineName() + " " + factory.getEngineVersion();
	}

	/**
	 * 指定されたファイル名の拡張子を返します。
	 * 
	 * @param fileName ファイル名
	 * @return 拡張子
	 */
	private static String getExtension(String fileName)
	{
		if (fileName == null) return "";

		int i = fileName.lastIndexOf('.');
		if (i > 0 && i < fileName.length() - 1)
		{
			String desiredExtension = fileName.substring(i + 1).toLowerCase(Locale.ENGLISH);
			return desiredExtension;
		}
		else
		{
			return "";
		}
	}

	/**
	 * 指定されたスクリプトを実行します。
	 * 
	 * @param script スクリプト
	 * @return スクリプトの実行によって返される値
	 * @throws ScriptException スクリプトの実行に失敗した場合
	 */
	public Object eval(String script) throws ScriptException
	{
		return engine.eval(script);
	}

	/**
	 * 指定されたスクリプトを実行します。
	 * 
	 * @param script スクリプトソース
	 * @return スクリプトの実行によって返される値
	 * @throws ScriptException スクリプトの実行に失敗した場合
	 */
	public Object eval(Reader script) throws ScriptException
	{
		return engine.eval(script);
	}

	/**
	 * 指定されたキーと値のペアを設定します。
	 * 
	 * @param key キー
	 * @param value 値
	 */
	public void put(String key, Object value)
	{
		engine.put(key, value);
	}

	/**
	 * 指定されたキーの値を返します。
	 * 値は、変数・関数・オブジェクトなどを表します。
	 * 
	 * @param key キー
	 * @return 指定されたキーの値
	 */
	public Object get(String key)
	{
		return engine.get(key);
	}

	/**
	 * 指定されたインタフェースの実装を返します。
	 * 
	 * @param type インタフェースの Class オブジェクト
	 * @return インタフェースの実装
	 */
	public <T> T get(Class<T> type)
	{
		Invocable inv = Invocable.class.cast(engine);
		return inv.getInterface(type);
	}

	/**
	 * 指定されたインタフェースの実装を返します。
	 * 
	 * @param obj インタフェースの実装を持つスクリプトオブジェクト
	 * @param type インタフェースの Class オブジェクト
	 * @return インタフェースの実装
	 */
	public <T> T get(Object obj, Class<T> type)
	{
		Invocable inv = Invocable.class.cast(engine);
		return inv.getInterface(obj, type);
	}

	/**
	 * 指定されたファンクションを実行します。
	 * 
	 * @param func ファンクション名
	 * @param args ファンクションに渡される引数
	 * @return ファンクションによって返される値
	 * @throws ScriptException スクリプトの実行に失敗した場合
	 */
	public Object invoke(String func, Object... args) throws ScriptException
	{
		try
		{
			Invocable inv = Invocable.class.cast(engine);
			return inv.invokeFunction(func, args);
		}
		catch (NoSuchMethodException x)
		{
			throw new ScriptException(x);
		}
	}

	/**
	 * 指定されたオブジェクトのファンクションを実行します。
	 * 
	 * @param obj オブジェクト
	 * @param func ファンクション名
	 * @param args ファンクションに渡される引数
	 * @return ファンクションによって返される値
	 * @throws ScriptException スクリプトの実行に失敗した場合
	 */
	public Object invoke(Object obj, String func, Object... args) throws ScriptException
	{
		try
		{
			Invocable inv = Invocable.class.cast(engine);
			return inv.invokeMethod(obj, func, args);
		}
		catch (NoSuchMethodException x)
		{
			throw new ScriptException(x);
		}
	}
}
