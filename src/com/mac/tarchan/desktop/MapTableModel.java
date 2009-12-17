/*
 *  Copyright (c) 2009 tarchan. All rights reserved.
 *  
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *  
 *  1. Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *  
 *  THIS SOFTWARE IS PROVIDED BY TARCHAN ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 *  MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
 *  EVENT SHALL TARCHAN OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 *  INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *  
 *  The views and conclusions contained in the software and documentation are
 *  those of the authors and should not be interpreted as representing official
 *  policies, either expressed or implied, of tarchan.
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
