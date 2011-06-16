/*
 * Copyright (c) 2011 tarchan. All rights reserved.
 */
package com.mac.tarchan.desktop;

import java.io.PrintStream;
import java.util.LinkedHashMap;

/**
 * ベンチマークのための機能です。
 */
public class Benchmark
{
	/** 観測点毎の時間 */
	private static LinkedHashMap<String, Long> mark = new LinkedHashMap<String, Long>();

	/** 観測開始点を表す名前 */
	public static final String START_SUFFIX = "_start";

	/** 観測終了点を表す名前 */
	public static final String END_SUFFIX = "_end";

	/**
	 * 観測点の時間を記録します。
	 * 
	 * @param name 観測点の名前
	 * @return 観測点の時間
	 * @see System#currentTimeMillis()
	 * @see #elapsedTime(String, String)
	 * @see #elapsedTime(PrintStream)
	 */
	public static long mark(String name)
	{
		long time = System.currentTimeMillis();
		mark.put(name, time);
		return time;
	}

	/**
	 * 経過時間を返します。
	 * 
	 * @param startName 観測開始点の名前
	 * @param endName 観測終了点の名前
	 * @return 経過時間
	 * @see #mark(String)
	 */
	public static long elapsedTime(String startName, String endName)
	{
		long startTime = mark.get(startName);
		long endTime = mark.get(endName);
		return endTime - startTime;
	}

	/**
	 * すべての経過時間を表示します。
	 * 
	 * @param out 出力ストリーム
	 * @see #mark(String)
	 */
	public static void elapsedTime(PrintStream out)
	{
		for (String name : mark.keySet())
		{
			if (name.endsWith(START_SUFFIX))
			{
				String prefix = name.substring(0, name.lastIndexOf(START_SUFFIX));
				String endName = prefix + END_SUFFIX;
				if (mark.containsKey(endName))
				{
					long time = elapsedTime(name, endName);
					out.printf("%s: %,d ms%n", prefix, time);
				}
				else
				{
					long startTime = mark.get(name);
					out.printf("%s: %tT started.%n", prefix, startTime);
				}
			}
		}
	}

	/**
	 * メモリ使用量を返します。
	 * 
	 * @return メモリ使用量
	 */
	public static long memoryUsage()
	{
		long totalMemory = Runtime.getRuntime().totalMemory();
		long freeMemory = Runtime.getRuntime().freeMemory();
		return totalMemory - freeMemory;
	}
}
