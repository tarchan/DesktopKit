package com.mac.tarchan.desktop.mock;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Window;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
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
		String[] header = {"ニックネーム", "ログイン名", "アドレス", "名前", "サーバ"};
		DefaultTableModel model = new DefaultTableModel(header, 10);
		JTable table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		JButton update = new JButton("更新");
		JButton save = new JButton("ファイルに保存");
		JButton close = new JButton("閉じる");

		Box box = Box.createHorizontalBox();
		box.setBorder(new EmptyBorder(4, 12, 4, 12));
		box.add(Box.createHorizontalGlue());
		box.add(update);
		box.add(save);
		box.add(close);

		JPanel main = new JPanel(new BorderLayout());
		main.add(sp, BorderLayout.CENTER);
		main.add(box, BorderLayout.SOUTH);
		return main;
	}
}
