package com.mac.tarchan.desktop.mock;

import java.awt.Component;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mac.tarchan.desktop.DesktopSupport;

/**
 * UserInfoBox
 */
public class UserInfoBox
{
	/**
	 * @param args なし
	 */
	public static void main(String[] args)
	{
		DesktopSupport.useSystemLookAndFeel();
		DesktopSupport.useSystemProxies();
		DesktopSupport.show(new UserInfoBox().createWindow());
	}

	Window createWindow()
	{
		JFrame window = new JFrame("UserInfo");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(createMain());
		window.setSize(500, 300);
		return window;
	}

	Component createMain()
	{
		String[] header = {"ニックネーム", "ユーザー名", "アドレス"};
		DefaultTableModel model = new DefaultTableModel(header, 10);
		JTable table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		JPanel main = new JPanel();
		main.add(sp);
		return main;
	}
}
