/*
 * Copyright (c) 2009 tarchan. All rights reserved.
 */
package com.mac.tarchan.desktop;

import java.util.Map;

import javax.swing.table.DefaultTableModel;

/**
 * MapTableModel
 * 
 * @author tarchan
 */
public class MapTableModel extends DefaultTableModel
{
	private static final long serialVersionUID = 922586139155923359L;

	/** データ */
	Map<String, Object> data;

	/** カラム名 */
	String[] columnName;

	/** カラムタイプ */
	Class<?>[] columnClass;

	/**
	 * テーブルモデルを作成します。
	 * 
	 * @param data データ
	 */
	public MapTableModel(Map<String, Object> data)
	{
		this.data = data;
		columnName = data.keySet().toArray(new String[]{});
		columnClass = new Class[columnName.length];
		int i = 0;
		for (String name : columnName)
		{
			columnClass[i++] = data.get(name).getClass();
		}
	}

	/**
	 * @see javax.swing.table.DefaultTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column)
	{
		return columnName[column];
	}

	/**
	 * @see javax.swing.table.DefaultTableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount()
	{
		return columnName.length;
	}

	/**
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		return columnClass[columnIndex];
	}

	/**
	 * @see javax.swing.table.DefaultTableModel#getRowCount()
	 */
	@Override
	public int getRowCount()
	{
		return data != null ? data.size() : 0;
	}

	/**
	 * @see javax.swing.table.DefaultTableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int row, int column)
	{
		Map<String, Object> rowData = this.data;
		String name = columnName[column];
		return rowData.get(name);
	}
}
