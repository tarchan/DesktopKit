/*
 * Copyright (c) 2010 tarchan. All rights reserved.
 */
package com.mac.tarchan.desktop;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Box;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * OptionBox
 */
@SuppressWarnings("serial")
public class OptionBox extends JDialog
{
	/** ログ */
	private static final Log log = LogFactory.getLog(OptionBox.class);

	/** OKのキーストロークです。 */
	public static final KeyStroke ENTER = KeyStroke.getKeyStroke("ENTER");

	/** キャンセルのキーストロークです。 */
	public static final KeyStroke ESCAPE = KeyStroke.getKeyStroke("ESCAPE");

	/** キャンセルのキーストロークです。 */
	public static final KeyStroke PERIOD = KeyStroke.getKeyStroke("meta PERIOD");

	/** メインパネル */
	protected JPanel mainBox = new JPanel();

	/** OKボタン */
	protected JButton okButton = new JButton("OK");

	/** キャンセルボタン */
	protected JButton cancelButton = new JButton("キャンセル");

	/** パック済み */
	private boolean packed;

	/**
	 * オプションダイアログを初期化します。
	 * 
	 * @param owner オーナーウインドウ
	 */
	public OptionBox(Window owner)
	{
		super(owner);
		mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
		add(mainBox, BorderLayout.CENTER);
		useDocumentModalSheet();
		setActionMap();
		setActionBox();
		pack();
	}

	@Override
	public void setVisible(boolean visible)
	{
		if (!packed)
		{
			pack();
			packed = true;
		}
		super.setVisible(visible);
	}

	/**
	 * シートダイアログで表示します。
	 */
	protected void useDocumentModalSheet()
	{
		setModalityType(ModalityType.DOCUMENT_MODAL);
		getRootPane().putClientProperty("apple.awt.documentModalSheet", true);
	}

	/**
	 * アクションを設定します。
	 */
	protected void setActionMap()
	{
		Action ok = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
//				ok();
				okButton.doClick();
			}
		};
		Action cancel = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
//				cancel();
				cancelButton.doClick();
			}
		};
		okButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ok();
			}
		});
		cancelButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				cancel();
			}
		});

		ActionMap actionMap = mainBox.getActionMap();
		actionMap.put(ok, ok);
		actionMap.put(cancel, cancel);

		InputMap inputMap = mainBox.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(ENTER, ok);
		inputMap.put(ESCAPE, cancel);
		inputMap.put(PERIOD, cancel);
	}

	/**
	 * アクションボタンをフッタに追加します。
	 */
	protected void setActionBox()
	{
		Box actionBox = Box.createHorizontalBox();
		actionBox.add(Box.createHorizontalGlue());
		actionBox.add(cancelButton);
		actionBox.add(okButton);
		actionBox.add(Box.createHorizontalStrut(12));
		actionBox.setBorder(new EmptyBorder(0, 12, 12, 12));
		add(actionBox, BorderLayout.SOUTH);
		getRootPane().setDefaultButton(okButton);
	}

	/**
	 * OKアクションを実行します。
	 */
	public void ok()
	{
		log.debug("ok");
//		okButton.doClick();
		setVisible(false);
	}

	/**
	 * キャンセルアクションを実行します。
	 */
	public void cancel()
	{
		log.debug("cancel");
//		cancelButton.doClick();
		setVisible(false);
	}

	/**
	 * OKアクションのハンドラを追加します。
	 * 
	 * @param handler アクションハンドラ
	 */
	public void addActionListener(ActionListener handler)
	{
		okButton.addActionListener(handler);
	}

	/**
	 * キャンセルアクションのハンドラを追加します。
	 * 
	 * @param handler アクションハンドラ
	 */
	public void addCancelListener(ActionListener handler)
	{
		cancelButton.addActionListener(handler);
	}
}
