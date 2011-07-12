package com.mac.tarchan.desktop.mock;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import javax.script.Compilable;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import com.mac.tarchan.desktop.script.UserScript;

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
			new ScriptBox().getVersion().helloWorld().myName().userScript();
		}
		catch (ScriptException x)
		{
			throw new RuntimeException(x);
		}
		catch (IOException x)
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
			System.out.println("================================");
			System.out.println("  EngineName: " + factory.getEngineName());
			System.out.println("  EngineVersion: " + factory.getEngineVersion());
			System.out.println("  LanguageName: " + factory.getLanguageName());
			System.out.println("  LanguageVersion: " + factory.getLanguageVersion());
			System.out.println("  Extensions: " + factory.getExtensions());
			System.out.println("  MimeTypes: " + factory.getMimeTypes());
			System.out.println("  Names: " + factory.getNames());
			System.out.println("  ScriptEngine: " + factory.getScriptEngine());
			System.out.println("  Invocable: " + Invocable.class.isInstance(factory.getScriptEngine()));
			System.out.println("  Compilable: " + Compilable.class.isInstance(factory.getScriptEngine()));
//			Invocable invocable = Invocable.class.cast(factory.getScriptEngine());
//			invocable.invokeFunction("name", "args");
			System.out.println("================================");
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

	ScriptBox userScript() throws ScriptException, IOException
	{
		File dir = new File("userscript");
		for (File file : dir.listFiles())
		{
			System.out.println(file);
//			if (file.isFile()) UserScript(file);
			if (file.isFile()) new UserScript(file);
		}
		return this;
	}

	ScriptBox userScript(File file) throws ScriptException, IOException
	{
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		SimpleBindings vars = new SimpleBindings();
		vars.put("event", new Object());
//		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		FileReader in = new FileReader(file);
		engine.eval(in, vars);
		for (Map.Entry<String, Object> entry : vars.entrySet())
		{
//			System.out.printf("%s=%s (%s)%n", entry.getKey(), entry.getValue(), entry.getValue().getClass());
			System.out.printf("%s=(%s)%n", entry.getKey(), entry.getValue().getClass().getName());
		}
		in.close();
		return this;
	}
}
