package com.mac.tarchan.desktop.mock;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

/**
 * JSBox
 * 
 * @see <a href="http://d.hatena.ne.jp/AWAWA/20080514/1210768459">MacのJava6でJavaScriptエンジン(Rhino)を使う</a>
 */
public class ScriptBox
{
	/**
	 * @param args なし
	 */
	public static void main(String[] args)
	{
		try
		{
			new ScriptBox().getVersion().helloWorld().myName();
		}
		catch (ScriptException x)
		{
			throw new RuntimeException(x);
		}
	}

	ScriptBox getVersion()
	{
		ScriptEngineManager manager = new ScriptEngineManager();
		for (ScriptEngineFactory factory : manager.getEngineFactories())
		{
			System.out.println(factory);
			System.out.println("  EngineName: " + factory.getEngineName());
			System.out.println("  EngineVersion: " + factory.getEngineVersion());
			System.out.println("  LanguageName: " + factory.getLanguageName());
			System.out.println("  LanguageVersion: " + factory.getLanguageVersion());
			System.out.println("  Extensions: " + factory.getExtensions());
			System.out.println("  MimeTypes: " + factory.getMimeTypes());
			System.out.println("  Names: " + factory.getNames());
			System.out.println("  ScriptEngine: " + factory.getScriptEngine());
		}

		return this;
	}

	ScriptBox helloWorld() throws ScriptException
	{
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		engine.eval("print('Hello, World!\\n');");
		return this;
	}

	ScriptBox myName() throws ScriptException
	{
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		SimpleBindings vars = new SimpleBindings();
		vars.put("username", System.getProperty("user.name"));
		System.out.println("username=" + vars.get("username"));
		engine.eval("print('My name is ' + username + '.\\n');", vars);
		return this;
	}
}
