/*
 * Copyright (c) 2011 tarchan. All rights reserved.
 */
package com.mac.tarchan.desktop;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

/**
 * コマンドライン引数を解析する機能です。
 */
public class Command
{
	/** オプション定義 */
	private Options opts = new Options();

	/** コマンドライン引数 */
	private CommandLine cmd;

	/**
	 * オプション定義を追加します。
	 * 
	 * @param opt オプション文字
	 * @param hasArg オプション引数を持つ場合は true
	 * @param description オプションの説明
	 * @return Command オブジェクト
	 * @see #parse(String[])
	 */
	public Command addOption(String opt, boolean hasArg, String description)
	{
		opts.addOption(opt, hasArg, description);
		return this;
	}

	/**
	 * オプション定義を追加します。
	 * 
	 * @param opt オプション文字
	 * @param longOpt 長いオプション文字
	 * @param hasArg オプション引数を持つ場合は true
	 * @param description オプションの説明
	 * @return Command オブジェクト
	 * @see #parse(String[])
	 */
	public Command addOption(String opt, String longOpt, boolean hasArg, String description)
	{
		opts.addOption(opt, longOpt, hasArg, description);
		return this;
	}

	/**
	 * オプション定義を追加します。
	 * 
	 * @param opt オプション文字
	 * @param argNum オプション引数の数
	 * @param description オプションの説明
	 * @return Command オブジェクト
	 * @see #parse(String[])
	 */
	public Command addOption(String opt, int argNum, String description)
	{
		opts.addOption(opt, argNum > 0, description);
		Option option = opts.getOption(opt);
		option.setArgs(argNum);
		return this;
	}

	/**
	 * オプション定義を追加します。
	 * 
	 * @param opt オプション文字
	 * @param longOpt 長いオプション文字
	 * @param argNum オプション引数の数
	 * @param description オプションの説明
	 * @return Command オブジェクト
	 * @see #parse(String[])
	 */
	public Command addOption(String opt, String longOpt, int argNum, String description)
	{
		opts.addOption(opt, longOpt, argNum > 0, description);
		Option option = opts.getOption(opt);
		option.setArgs(argNum);
		return this;
	}

	/**
	 * コマンドライン引数を解析します。
	 * 
	 * @param args コマンドライン引数
	 * @return Command オブジェクト
	 * @throws IllegalArgumentException コマンドライン引数が不正な場合
	 * @see PosixParser
	 */
	public Command parse(String[] args)
	{
		try
		{
			cmd = new PosixParser().parse(opts, args);
			return this;
		}
		catch (ParseException x)
		{
			throw new IllegalArgumentException("コマンドライン引数が不正です。", x);
		}
	}

	/**
	 * 指定されたオプションがあるかどうか判定します。
	 * 
	 * @param opt オプション文字
	 * @return オプションがある場合は true
	 */
	public boolean hasOption(String opt)
	{
		return cmd.hasOption(opt);
	}

	/**
	 * 指定されたオプションの引数を返します。
	 * 
	 * @param opt オプション文字
	 * @return オプション引数
	 */
	public String getOptionValue(String opt)
	{
		return cmd.getOptionValue(opt);
	}

	/**
	 * 指定されたオプションの引数を返します。
	 * オプションが見つからない場合はデフォルト値を返します。
	 * 
	 * @param opt オプション文字
	 * @param def デフォルト値
	 * @return オプション引数
	 */
	public String getOptionValue(String opt, String def)
	{
		return cmd.getOptionValue(opt, def);
	}

	/**
	 * オプション以外の引数を返します。
	 * 
	 * @return 引数
	 */
	public String[] getArgs()
	{
		return cmd.getArgs();
	}

	/**
	 * 引数があるかどうか判定します。
	 * 
	 * @return 引数がある場合は true
	 */
	public boolean hasArgs()
	{
		return getArgs().length > 0;
	}

	/**
	 * オプションの詳細を出力します。
	 * 
	 * @param usage 書式文字列
	 */
	public void printHelp(String usage)
	{
		HelpFormatter help = new HelpFormatter();
		help.printHelp(usage, opts);
	}
}
