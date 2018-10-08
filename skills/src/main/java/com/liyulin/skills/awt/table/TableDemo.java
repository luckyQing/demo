package com.liyulin.skills.awt.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

@SuppressWarnings("serial")
public class TableDemo extends JFrame {
	private JTable table;
	private JScrollPane sp;
	private Object[][] data = { { "张三", "24", new Boolean(false), "滑雪" }, { "李四", 60, new Boolean(true), "滑雪" },
			{ "王五", 63, new Boolean(false), "滑雪" }, { "刘六", 33, new Boolean(true), "滑雪" } };
	private String[] columnNames = { "姓名", "年龄", "性别", "婚否" };

	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	public TableDemo() {
		super("表格");
		MyTableModel myTableModel = new MyTableModel(data, columnNames);
		// 设置3、4列可编辑
		int[] locsEditable = { 1, 2, 3 };
		myTableModel.setLcos(locsEditable);

		table = new JTable(myTableModel);

		// 设置指定单元格背景
		int[] locsFont = { 0, 1, 3 };
		myTableModel.setBackground(table, locsFont);

		table.setGridColor(Color.BLUE);// 设置表格线的颜色
		sp = new JScrollPane(table);
		this.add(sp);
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		// this.setResizable(false);
		this.setDefaultCloseOperation(3);

		table.setColumnSelectionAllowed(true);// 使选择框成为一个单元格
		// 为数据表中指定列加入下拉列表框
		TableColumn sportColumn1 = table.getColumnModel().getColumn(3);

		JComboBox comboBox = new JComboBox(

				new String[] { "滑雪", "保龄球", "跑步", "游泳" });
		sportColumn1.setCellEditor(new DefaultCellEditor(comboBox));

		TableColumn sportColumn2 = table.getColumnModel().getColumn(2);
		sportColumn2.setCellEditor(new DefaultCellEditor(new JCheckBox()));
		// 监听表格是否被修改
		myTableModel.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				Toolkit.getDefaultToolkit().beep();
				if (JOptionPane.showConfirmDialog(null, "您确定要修改表格内容吗！", "表格修改",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

				}
				;
			}
		});
	}

	public static void main(String[] args) {
		TableDemo f = new TableDemo();
		f.setVisible(true);
	}
}

// 表格模式类

@SuppressWarnings("serial")
class MyTableModel extends AbstractTableModel {

	private Object[][] data;// 表格数据
	private String[] columnNames;// 表格标题
	private int[] lcos;// 可编辑的列号

	public MyTableModel(Object[][] data, String[] columnNames) {
		this.data = data;
		this.columnNames = columnNames;
	}

	// 下面三个方法是一定要加入AbstractTableModel类

	// 分别是设置列数,行数与列表的数据

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	// 该方法不一定要加入AbstractTableModel类,该方法获得列表的标题

	public String getColumnName(int col) {
		return columnNames[col];
	}

	// 通过getClass()方法设置默认的控件,如true与false的默认控件是复选框,

	// 该方法不一定要加入AbstractTableModel类

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	// 指定列设置背景颜色
	public static void setBackground(JTable table, int[] columnFont) {
		EvenOddRenderer evenOddRenderer = new EvenOddRenderer();
		evenOddRenderer.setClolums(columnFont);
		table.setDefaultRenderer(Object.class, evenOddRenderer);
	}

	// 设置可编辑的列号
	public void setLcos(int[] lcos) {
		this.lcos = lcos;
	}

	// 定义可以编辑的单元,true表示可编辑,false表示不可编辑

	public boolean isCellEditable(int row, int col) {
		boolean isEdibale = false;
		for (int i = 0; i < lcos.length; i++) {
			if (col == lcos[i]) {
				isEdibale = true;
			}
		}
		return isEdibale;
	}

	// 将改变后的值保存在表格中

	public void setValueAt(Object value, int row, int col) {

		data[row][col] = value;

		fireTableCellUpdated(row, col);
	}
}

/***
 * 设置单元格背景色类
 */

@SuppressWarnings("serial")
class EvenOddRenderer extends JLabel implements TableCellRenderer {

	public final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
	private int[] columnFont;// 需设置字体背景的颜色

	public void setClolums(int[] columnFont) {
		this.columnFont = columnFont;
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
				column);
		Color foreground = null;

		for (int i = 0; i < columnFont.length; i++) {
			if (column == columnFont[i]) {
				foreground = Color.red;
			}
		}

		this.setHorizontalAlignment(JLabel.CENTER);

		renderer.setForeground(foreground);
		return renderer;
	}
}
