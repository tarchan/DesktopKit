/*
 * Copyright (c) 2009 tarchan. All rights reserved.
 */
package com.mac.tarchan.desktop;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * ConfigPit は、設定データの書き方を一般化します。
 * 
 * @author tarchan
 * @see <a href="http://perl-users.jp/modules/config_pit.html">パスワード設定をコードに書かない(Config::Pit) - モダンなPerl入門 - モダンなPerl入門</a>
 * @see Preferences
 */
public class ConfigPit
{
	/**
	 * 指定された設定ノードを読み込みます。
	 * これは、次のような呼び出しと同じです。
	 * <pre>
	 * {@code Preferences.userRoot().node(pathName);}
	 * </pre>
	 * 
	 * @param pathName 設定ノードのパス名
	 * @return 設定ノード
	 */
	public static Preferences load(String pathName)
	{
		return Preferences.userRoot().node(pathName);
	}

	/**
	 * 指定された設定ノードのパス名を返します。
	 * 
	 * @param node 設定ノード
	 * @return 設定ノードのパス名
	 */
	public static String getPathName(Preferences node)
	{
		return node != null ? node.absolutePath().substring(1) : null;
	}

	/**
	 * ユーザールートに存在する設定ノードの名前の配列を返します。
	 * 
	 * @return 設定ノード名の配列
	 * @throws IllegalStateException バッキングストアで障害が発生したかストアとの通信が行えないために、このオペレーションを完了できない場合
	 * @see BackingStoreException
	 */
	public static String[] listNames()
	{
		try
		{
			Preferences root = Preferences.userRoot();
			return root.childrenNames();
		}
		catch (BackingStoreException x)
		{
			throw new IllegalStateException(x);
		}
	}

	/**
	 * 指定された設定ノードが存在するかどうかを判定します。
	 * 
	 * @param pathName 設定ノードのパス名
	 * @return 指定された設定ノードが存在する場合は true
	 * @throws IllegalStateException バッキングストアで障害が発生したかストアとの通信が行えないために、このオペレーションを完了できない場合
	 * @see BackingStoreException
	 */
	public static boolean exists(String pathName)
	{
		try
		{
			Preferences root = Preferences.userRoot();
			return root.nodeExists(pathName);
		}
		catch (BackingStoreException x)
		{
			throw new IllegalStateException(x);
		}
	}

	/**
	 * 指定されたドメインの設定を削除します。
	 * 
	 * @param domain ドメイン名
	 * @return 設定が正常に削除された場合は true
	 */
	public static boolean delete(String domain)
	{
		if (!exists(domain)) return false;

		try
		{
			Preferences.userRoot().node(domain).removeNode();
			return true;
		}
		catch (BackingStoreException x)
		{
			x.printStackTrace();
		}

		return false;
	}
}
