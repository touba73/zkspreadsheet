/*

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2013/12/01 , Created by dennis
}}IS_NOTE

Copyright (C) 2013 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.zss.ngmodel.impl;

import java.io.Serializable;

import org.zkoss.zss.ngmodel.CellRegion;
import org.zkoss.zss.ngmodel.sys.dependency.Ref;
/**
 * 
 * @author dennis
 * @since 3.5.0
 */
public class RefImpl implements Ref, Serializable {

	private static final long serialVersionUID = 1L;
	final private RefType type;
	final protected String bookName;
	final protected String sheetName;
	final protected String lastSheetName;
	final private int row;
	final private int column;
	final private int lastRow;
	final private int lastColumn;

	public RefImpl(String bookName, String sheetName, int row, int column,
			int lastRow, int lastColumn) {
		this(RefType.AREA, bookName, sheetName, null, row, column, lastRow,lastColumn);
	}

	public RefImpl(String bookName, String sheetName, int row, int column) {
		this(RefType.CELL, bookName, sheetName, null, row, column, row, column);
	}

	public RefImpl(String bookName, String sheetName, String lastSheetName, int row, int column,
			int lastRow, int lastColumn) {
		this(RefType.AREA, bookName, sheetName, lastSheetName, row, column, lastRow,lastColumn);
	}

	public RefImpl(String bookName, String sheetName, String lastSheetName, int row, int column) {
		this(RefType.CELL, bookName, sheetName, lastSheetName, row, column, row, column);
	}

	public RefImpl(String bookName, String sheetName) {
		this(RefType.SHEET, bookName, sheetName, null, -1, -1, -1, -1);
	}

	public RefImpl(String bookName) {
		this(RefType.BOOK, bookName, null, null, -1, -1, -1, -1);
	}

	public RefImpl(AbstractCellAdv cell) {
		this(RefType.CELL, cell.getSheet().getBook().getBookName(), cell.getSheet().getSheetName(), null, cell.getRowIndex(),
		cell.getColumnIndex(), cell.getRowIndex(), cell.getColumnIndex());
	}

	public RefImpl(AbstractSheetAdv sheet) {
		this(RefType.SHEET, ((AbstractBookAdv) sheet.getBook()).getBookName(), sheet.getSheetName(), null, -1, -1, -1, -1);
	}

	public RefImpl(AbstractBookAdv book) {
		this(RefType.BOOK, book.getBookName(), null, null, -1, -1, -1, -1);
	}

	protected RefImpl(RefType type, String bookName, String sheetName, String lastSheetName,
			int row, int column, int lastRow, int lastColumn) {
		this.type = type;
		this.bookName = bookName;
		this.sheetName = sheetName;
		this.lastSheetName = lastSheetName;
		this.row = row;
		this.column = column;
		this.lastRow = lastRow;
		this.lastColumn = lastColumn;
	}

	@Override
	public RefType getType() {
		return type;
	}

	@Override
	public String getBookName() {
		return bookName;
	}

	@Override
	public String getSheetName() {
		return sheetName;
	}
	
	@Override
	public String getLastSheetName() {
		return lastSheetName;
	}

	@Override
	public int getRow() {
		return row;
	}

	@Override
	public int getColumn() {
		return column;
	}

	@Override
	public int getLastRow() {
		return lastRow;
	}

	@Override
	public int getLastColumn() {
		return lastColumn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bookName == null) ? 0 : bookName.hashCode());
		result = prime * result + column;
		result = prime * result + lastColumn;
		result = prime * result + lastRow;
		result = prime * result + row;
		result = prime * result
				+ ((sheetName == null) ? 0 : sheetName.hashCode());
		result = prime * result
				+ ((lastSheetName == null) ? 0 : lastSheetName.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RefImpl other = (RefImpl) obj;
		
		if (bookName == null) {
			if (other.bookName != null)
				return false;
		} else if (!bookName.equals(other.bookName))
			return false;
		if (column != other.column)
			return false;
		if (lastColumn != other.lastColumn)
			return false;
		if (lastRow != other.lastRow)
			return false;
		if (row != other.row)
			return false;
		if (sheetName == null) {
			if (other.sheetName != null)
				return false;
		} else if (!sheetName.equals(other.sheetName))
			return false;
		if (lastSheetName == null) {
			if (other.lastSheetName != null)
				return false;
		} else if (!lastSheetName.equals(other.lastSheetName))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		switch (type) {
		case AREA:
			sb.insert(0,":"+ new CellRegion(lastRow, lastColumn).getReferenceString());
		case CELL:
			sb.insert(0, new CellRegion(row, column).getReferenceString());
		case SHEET:
			if(lastSheetName != null) {
				sb.insert(0, sheetName + ":" + lastSheetName + "!");
			} else {
				sb.insert(0, sheetName + "!");
			}
			break;
		case OBJECT://will be override
			if(lastSheetName!=null){
				sb.insert(0, sheetName + ":" + lastSheetName + "!");
			}else if(sheetName!=null){
				sb.insert(0, sheetName + "!");
			}
		case NAME://will be override
		case BOOK:
		}

		sb.insert(0, bookName + ":");
		return sb.toString();
	}
}
