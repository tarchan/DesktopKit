/*
 * Copyright (c) 2011 tarchan. All rights reserved.
 */
package com.mac.tarchan.desktop.mock;

import java.awt.Component;
import java.awt.Window;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

import com.mac.tarchan.desktop.DesktopSupport;
import com.mac.tarchan.desktop.InputBox;
import com.mac.tarchan.desktop.event.EventQuery;

/**
 * DesktopKit を使用したモックアプリケーションです。
 * 
 * @see DesktopSupport
 * @see EventQuery
 * @see InputBox
 */
public class Mock
{
	/**
	 * モックアプリケーションを起動します。
	 * 
	 * @param args なし
	 */
	public static void main(String[] args)
	{
		DesktopSupport.useSystemLookAndFeel();
		DesktopSupport.show(new Mock().createWindow());
	}

	private Window createWindow()
	{
		JFrame window = new JFrame("Mock");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(createPanel());
		window.setSize(400, 300);

		EventQuery.from(window)
			.button().click(this, "onclick", "source.text").end();
		return window;
	}

	private Component createPanel()
	{
		JButton button1 = new JButton("Button1");
		JCheckBox checkbox1 = new JCheckBox("Checkbox1");
		JCheckBox checkbox2 = new JCheckBox("Checkbox2");
		JComboBox combobox1 = new JComboBox(new String[]{"Apple", "Google"});
		JLabel label1 = new JLabel("Label1");
		JList list1 = new JList(new String[]{"Apple", "Google"});
		JRadioButton radiobutton1 = new JRadioButton("RadioButton1");
		JRadioButton radiobutton2 = new JRadioButton("RadioButton2");
		ButtonGroup group = new ButtonGroup();
		group.add(radiobutton1);
		group.add(radiobutton2);
		JToggleButton togglebutton1 = new JToggleButton("ToggleButton1");

		JPanel main = new JPanel();
		main.add(button1);
		main.add(checkbox1);
		main.add(checkbox2);
		main.add(combobox1);
		main.add(label1);
		main.add(list1);
		main.add(radiobutton1);
		main.add(radiobutton2);
		main.add(togglebutton1);
//		main.getRootPane().setDefaultButton(button1);
		return main;
	}

	/**
	 * onclick
	 * 
	 * @param text テキスト
	 */
	public void onclick(String text)
	{
		InputBox.alert("クリックしました。: " + text);
	}
}
