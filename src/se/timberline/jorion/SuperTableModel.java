package se.timberline.jorion;

import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class SuperTableModel extends AbstractTableModel {

	private List<Integer> content = Collections.emptyList();

	@Override
	public int getColumnCount() {
		return 16;
	}

	@Override
	public int getRowCount() {
		int rows = content.size() / getColumnCount() + 1;
		return rows;
	}

	@Override
	public Object getValueAt(int col, int row) {
		int index = col*getColumnCount() + row;
		if (content.size() > index) {
			return String.format("%X", content.get(index));
		}
		return null;
	}

	public void setContent(List<Integer> content) {
		this.content = content;
	}

}
