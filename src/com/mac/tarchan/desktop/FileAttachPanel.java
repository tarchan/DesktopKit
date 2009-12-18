/*
 * Copyright (c) 2009 tarchan. All rights reserved.
 */
package com.mac.tarchan.desktop;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

import com.mac.tarchan.desktop.event.EventQuery;

/**
 * FileAttachPanel
 * 
 * @author tarchan
 */
public class FileAttachPanel extends JComponent
{
	private static final long serialVersionUID = 1235901861612711123L;

	/** ファイルダイアログ */
	JFileChooser chooser;

	/** 添付ファイル */
//	File attachFile;

	/**
	 * ファイル添付パネルを作成します。
	 * 
	 * @param columns カラム数
	 */
	public FileAttachPanel(int columns)
	{
		UIManager.put("FileChooser.saveButtonText", "保存");							// ファイルを保存
		UIManager.put("FileChooser.openButtonText", "選択");						// ファイルを選択
		UIManager.put("FileChooser.directoryOpenButtonText", "開く");			// ディレクトリを開く
		UIManager.put("FileChooser.cancelButtonText", "キャンセル");			// ファイル選択を取り消し
		UIManager.put("FileChooser.openDialogTitleText", "ファイルを選択");			// "ファイルを選択
		chooser = new JFileChooser();
		chooser.setName("fileChooser");

		JTextField attachField = new JTextField(columns);
		attachField.setName("attachName");
		JButton attachButton = new JButton("参照...");
		attachButton.setName("attachFile");

		setLayout(new BorderLayout(5, 5));
		add(attachField, BorderLayout.CENTER);
		add(attachButton, BorderLayout.EAST);

		EventQuery.ready(this).find("attachFile").click(this);
//		EventQuery.ready(chooser).change(this, "change", "");
	}

//	public void change(PropertyChangeEvent evt)
//	{
//		String propertyName = evt.getPropertyName();
//		System.out.println("change=" + evt.getPropertyName() + ", " + evt);
//		if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(propertyName))
//		{
//			System.out.println("select=" + evt.getNewValue());
//			System.out.println("deselect=" + evt.getOldValue());
//		}
//	}

	/**
	 * ファイル添付パネルを作成します。
	 */
	public FileAttachPanel()
	{
		this(0);
	}

	/**
	 * ファイル名拡張子を使ってフィルタを適用します。
	 * 
	 * @param description フィルタの説明テキスト (null の場合もある)
	 * @param extensions 受け入れるファイル名拡張子
	 * @see JFileChooser#addChoosableFileFilter(FileFilter)
	 * @see FileNameExtensionFilter
	 */
	public void addFileFilter(String description, String... extensions)
	{
		FileFilter filter = new FileNameExtensionFilter(description, extensions);
		chooser.addChoosableFileFilter(filter);
	}

	/**
	 * ファイルのみ、ディレクトリのみ、またはファイルとディレクトリの両方を選択できるようにします。
	 * 
	 * @param mode ファイル選択モード
	 * @see JFileChooser#setFileSelectionMode(int)
	 * @see JFileChooser#FILES_ONLY
	 * @see JFileChooser#DIRECTORIES_ONLY
	 * @see JFileChooser#FILES_AND_DIRECTORIES
	 */
	public void setFileSelectionMode(int mode)
	{
		chooser.setFileSelectionMode(mode);
	}

	/**
	 * ファイルダイアログで添付ファイルを指定します。
	 */
	public void attachFile()
	{
//		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(getFile());
		int returnVal = chooser.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			File file = chooser.getSelectedFile();
			if (file.exists())
			{
				setFile(file);
			}
			else
			{
				int alertVal = confirm("指定されたファイルは存在しません: " + file);
				if(alertVal == JFileChooser.APPROVE_OPTION)
				{
//					System.out.println("添付する");
					setFile(file);
				}
				else
				{
//					System.out.println("キャンセル");
				}
			}
		}
	}

	int confirm(String message)
	{
		message += "\nこのファイルを添付しますか？";
//		JOptionPane.showMessageDialog(this, message);
//		JOptionPane.showMessageDialog(null, message, "alert", JOptionPane.ERROR_MESSAGE);
//		return JOptionPane.showConfirmDialog(this, message, "ファイル添付", JOptionPane.YES_NO_OPTION);
		return JOptionPane.showOptionDialog(this, message, "ファイル添付"
				, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null
				, new Object[]{"添付する", "キャンセル"}, null);
	}

	/**
	 * 指定されたファイルを設定します。
	 * 
	 * @param file ファイル
	 */
	public void setFile(File file)
	{
//		this.attachFile = file;
//		EventQuery.ready(this).find("attachName").text(file.getName());
		EventQuery.ready(this).find("attachName").text(file.getPath());
	}

	/**
	 * 指定されたパスを設定します。
	 * 
	 * @param path パス
	 */
	public void setPath(String path)
	{
		setFile(new File(path));
	}

	/**
	 * 設定されたファイルのパスを返します。
	 * 
	 * @return パス
	 */
	public String getPath()
	{
		return EventQuery.ready(this).find("attachName").text();
	}

	/**
	 * 設定されたファイルを返します。
	 * 
	 * @return ファイル
	 */
	public File getFile()
	{
//		return attachFile;
		return new File(getPath());
	}

	/**
	 * ファイル添付パネルの文字列表現を返します。
	 */
	@Override
	public String toString()
	{
		return String.format("[%s=%s]", getName(), getPath());
	}
}
