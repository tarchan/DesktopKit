/*
 * Copyright (c) 2009 tarchan. All rights reserved.
 */
package com.mac.tarchan.desktop;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * コマンドライン引数を解析するために使用します。
 * 
 * <h3>POSIX</h3>
 * <p>IEEE標準1003.1-2001でコマンドライン引数の書式について次のように定めています。</p>
 * <ul>
 * <li>オプション名は、アルファベットまたは数字1文字。ただし、-Wはベンダーオプションとして予約されている。
 * <li>すべてのオプション名は、'-'で始まる。
 * <li>オプション引数のないオプションはグループ化できる。
 * <li>オプション名とオプション引数は別のコマンドライン引数である。ただし、例外としてSYNOPSISに明記すればオプション名とオプション引数を一つのコマンドライン引数としてもよい。
 * <li>オプション引数は省略可能ではない。
 * <li>オプション引数を複数指定するときは、コマンドライン上は1つとし、カンマやスペースで区切る。
 * <li>オプションは、コマンドライン上ではオペランドより前に存在する。
 * <li>--はオプション指定の終端として扱われ、それ以降はすべてオペランド(値)となる。
 * <li>オプションの順序は問われない。ただし、排他的と記述されたオプション、先行するオプションを打ち消すようなものは除く。
 * <li>オペランドで読み書きするファイルを指定する場合、'-'は標準入力(または標準出力)を意味する。
 * </ul>
 * 
 * @author tarchan
 * @see <a href="http://www.02.246.ne.jp/~torutk/javahow2/commandline.html">Javaでコマンドライン引数を処理する</a>
  */
public class AnyOption
{
	/** オプションリスト */
	Map<String, String> map = new LinkedHashMap<String, String>();

	/** コマンドライン引数 */
	String[] args;

	/** オペランド */
	String[] operand;

	/** リスト */
	List<String> list;

	/**
	 * コマンドライン引数を解析します。
	 * 
	 * @param args コマンドライン引数
	 */
	public AnyOption(String[] args)
	{
		this.args = args;
		int i = 0;
		for (String arg : args)
		{
			if (arg.startsWith("--"))
			{
				// オプション引数の終端。残りはオペランドとみなす。
//				operand = Arrays.asList(args).subList(i + 1, args.length).toArray(new String[]{});
				operand = subArray(args, i + 1, args.length);
//				System.out.println("(a)");
				break;
			}
			else if (arg.equals("-"))
			{
				// TODO 標準入出力
//				operand = Arrays.asList(args).subList(i, args.length).toArray(new String[]{});
				operand = subArray(args, i, args.length);
//				System.out.println("(b)");
				break;
			}
			else if (arg.startsWith("-"))
			{
				// オプション引数
				String name = arg.substring(1, 2);
				String value = arg.substring(2);
				map.put(name, value);
			}
			else
			{
				// オプション引数の終端。残りはオペランドとみなす。
//				operand = Arrays.asList(args).subList(i, args.length).toArray(new String[]{});
				operand = subArray(args, i, args.length);
				list = new CopyOnWriteArrayList<String>(args).subList(i, args.length);
//				System.out.println("(c)");
				break;
			}
			i++;
		}

		// すべてオプション引数だった場合は、空のオペランドを設定する。
		if (i == args.length) operand = new String[]{};
	}

	/**
	 * Stringの配列の部分コピーを作成します。
	 * 
	 * @param args 元の配列
	 * @param from 開始インデックス
	 * @param to 終了インデックス
	 * @return 部分コピーの配列
	 */
	static String[] subArray(String[] args, int from, int to)
	{
		return Arrays.asList(args).subList(from, to).toArray(new String[]{});
	}

	/**
	 * コマンドライン引数を解析します。
	 * 
	 * @param args コマンドライン引数
	 * @return AnyOptionオブジェクト
	 */
	public static AnyOption parse(String[] args)
	{
		AnyOption option = new AnyOption(args);
//		option.listOption(System.out);
		return option;
	}

	/**
	 * 引数の配列リストを返します。
	 * 
	 * @return 配列リスト
	 */
	public List<String> list()
	{
		return list;
	}

	/**
	 * 配列リストの最初の要素を返します。
	 * 最初の要素を削除して、後続の要素は左に移動します。
	 * 
	 * @return 最初の要素
	 */
	public String shift()
	{
//		List<String> list = list();
		return list.size() > 0 ? list.remove(0) : null;
	}

	/**
	 * 指定された出力ストリームに、引数リストを出力します。
	 * 
	 * @param out 出力ストリーム
	 */
	public void listOption(PrintStream out)
	{
		out.format("-- listing option & operand -- 0x%X\n", hashCode());
		for (Map.Entry<String, String> entry : map.entrySet())
		{
			out.format("%s:%s\n", entry.getKey(), entry.getValue());
		}
		out.format("%s\n", Arrays.asList(operand));
	}

	/**
	 * 指定された名前のオプションがあるかどうか判定します。
	 * 
	 * @param name オプション名
	 * @return オプションがある場合は true
	 */
	public boolean hasOption(String name)
	{
		return map.containsKey(name);
	}

	/**
	 * 指定されたオプション名の値を返します。
	 * 
	 * @param name オプション名
	 * @return オプション値
	 */
	public String getOption(String name)
	{
		return map.get(name);
	}

	/**
	 * コマンドライン引数を返します。
	 * 
	 * @return コマンドライン引数
	 */
	public String[] getArguments()
	{
		return args.clone();
	}

	/**
	 * オペランドを返します。
	 * 
	 * @return オペランド
	 */
	public String[] getOperand()
	{
		return operand.clone();
	}

	/**
	 * オプション引数の数を返します。
	 * 
	 * @return オプション引数の数
	 */
	public int size()
	{
		return map.size();
	}
}
