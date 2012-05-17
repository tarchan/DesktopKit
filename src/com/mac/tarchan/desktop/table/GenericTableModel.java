/*
 * GenericTableModel.java
 * DesktopKit
 * 
 * Created by tarchan on 2012/05/17.
 * Copyright (c) 2012 tarchan. All rights reserved.
 */
package com.mac.tarchan.desktop.table;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * 指定された型のデータを格納する {@link TableModel} の実装です。
 * 
 * @param <T> 格納されるデータの型
 * @see DefaultTableModel#getColumnClass(int)
 */
@SuppressWarnings("serial")
public class GenericTableModel<T> extends DefaultTableModel
{
	/**
	 * 列が 1、行が 0 の GenericTableModel を構築します。
	 */
	public GenericTableModel()
	{
		super(0, 1);
	}

	/**
	 * 指定された列の名前を持つ GenericTableModel を構築します。
	 * 
	 * @param columnNames 列の名前
	 */
	public GenericTableModel(String... columnNames)
	{
		super(columnNames, 0);
	}

	@Override
	public Class<?> getColumnClass(int column)
	{
		return getValueAt(0, column).getClass();
	}

	/**
	 * モデルの最後に行を追加します。
	 * 
	 * @param rowData 追加される行のデータ
	 */
	public void addData(T... rowData)
	{
		super.addRow(rowData);
	}
}
