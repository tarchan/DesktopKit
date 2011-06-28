/*
 * Copyright (c) 2011 tarchan
 * All rights reserved.
 * 
 * Distributed under the BSD Software License (see LICENSE.txt) 
 */
package com.mac.tarchan.desktop.mock;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import com.mac.tarchan.desktop.DesktopSupport;
import com.mac.tarchan.desktop.InputBox;
import com.mac.tarchan.desktop.SexyControl;
import com.mac.tarchan.desktop.event.EventQuery;
import com.sun.awt.AWTUtilities;

/**
 * DesktopKit を使用したモックアップです。
 * 
 * @see DesktopSupport
 * @see EventQuery
 * @see InputBox
 */
public class MockApp
{
	/**
	 * モックアップを起動します。
	 * 
	 * @param args なし
	 */
	public static void main(String[] args)
	{
		// TODO getopt (AnyOption.getOption().parse(args))
		SexyControl.setAppleMenuAboutName("MockApp");
		SexyControl.useScreenMenuBar();
		DesktopSupport.useSystemProxies();
		DesktopSupport.useSystemLookAndFeel();
		DesktopSupport.show(new MockApp().createWindow());
	}

//	OptionBox option;

	private Window createWindow()
	{
		JFrame window = new JFrame("MockApp");
		window.addHierarchyListener(new HierarchyListener()
		{
			public void hierarchyChanged(HierarchyEvent e)
			{
				System.out.println("hierarchyChanged: " + e);
				if (e.getChangeFlags() == HierarchyEvent.DISPLAYABILITY_CHANGED)
				{
					System.out.println("packed: " + e);
				}
			}
		});
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("window.add");
		window.add(createPanel());
		System.out.println("window.setSize");
		window.setSize(400, 300);
//		System.out.println("window.pack");
//		window.pack();

//		option = new OptionBox(window);

		 new EventQuery(window)
			.button().click(this, "onclick", "source.text").end()
			.input().click(this, "onclick", "source.text").end()
			.ready(this, "onready", "changeFlags").end();

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
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(radiobutton1);
		radioGroup.add(radiobutton2);
		JToggleButton togglebutton1 = new JToggleButton("One");
		JToggleButton togglebutton2 = new JToggleButton("Two");
		JToggleButton togglebutton3 = new JToggleButton("Three");
		ButtonGroup toggleGroup = new ButtonGroup();
		toggleGroup.add(togglebutton1);
		toggleGroup.add(togglebutton2);
		toggleGroup.add(togglebutton3);
		JButton button2 = new JButton("TransparentFrame");

		JTextField textfield1 = new JTextField(30);
		EventQuery.from(textfield1).ready(this, "onready_main", "");

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
		main.add(togglebutton2);
		main.add(togglebutton3);
		main.add(textfield1);
		main.add(button2);
//		main.getRootPane().setDefaultButton(button1);
		return main;
	}

	/**
	 * フォーカスをリクエストします。
	 * 
	 * @param evt 階層イベント
	 */
	public void onready_main(HierarchyEvent evt)
	{
		System.out.printf("onready_main: %s%n", evt);
		System.out.printf("onready_main: %s%n", evt.getSource());
		Component main = (Component)evt.getSource();
		main.requestFocusInWindow();
	}

	@SuppressWarnings("serial")
	static class ImageCanvas extends JPanel
	{
		private Image image = null;
		
		public ImageCanvas(String filename) throws IOException
		{
			BufferedImage image = ImageIO.read(new URL(filename));
			final int background = image.getRGB(0, 0);
			Toolkit tk = Toolkit.getDefaultToolkit();
			RGBImageFilter chromakey = new RGBImageFilter()
			{
				@Override
				public int filterRGB(int x, int y, int rgb)
				{
					return rgb == background ? 0 : rgb;
				}
			};
			Image img = tk.createImage(new FilteredImageSource(image.getSource(), chromakey));
			setImage(img);
		}

		public void setImage(Image image)
		{
			this.image = image;
		}

		@Override
		public void paintComponent(Graphics g)
		{
			if (image != null)  g.drawImage(image, 0, 0, this);
		}
	}

	private void showTransparentFrame()
	{
		try
		{
			JFrame window = new JFrame("TransparentFrame");
			window.setSize(512, 512);
			window.setLocation(576, 336);
			window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//			window.setBackground(new Color(0, true));
			window.setUndecorated(true);
			window.add(new ImageCanvas("http://a2.att.hudong.com/32/45/19300001150142130630457789150_950.jpg"));
//			window.setVisible(true);
			AWTUtilities.setWindowOpaque(window, false);
			DesktopSupport.show(window);
		}
		catch (IOException x)
		{
			throw new RuntimeException("矩形ウインドウを作成できません。", x);
		}
	}

	/**
	 * onclick
	 * 
	 * @param text テキスト
	 */
	public void onclick(String text)
	{
		if (text.equals("TransparentFrame"))
		{
			showTransparentFrame();
		}
		else
		{
			InputBox.alert("クリックしました。: " + text);
		}
//		option.setVisible(true);
	}

	/**
	 * onready
	 * 
	 * @param changeFlags 階層イベントの型を示すビットマスク
	 * @see HierarchyEvent#DISPLAYABILITY_CHANGED
	 * @see HierarchyEvent#SHOWING_CHANGED
	 */
	public void onready(long changeFlags)
	{
		System.out.printf("ready3: 0x%02x%n", changeFlags);
	}
}
