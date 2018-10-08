package com.liyulin.skills.awt.table;

//三、表格控件
//表格控件使用类JTable。
//功能：它提供以行和列的方式来显示数据，并且可以拖动列
//构造函数：JTable(Object[][] rowData, Object[] columnNames)
//编程方法：
//创建出JTable对象（可以采用表格中的表头数组和数据数组创建出表格、利用实现TableModel接口的类对象）
//再创建出一个JScrollPane对象并指示其ScrollBar的使用策略；
//将表格控件加入JScrollPane中；
//最后将JScrollPane对象加人到顶级容器的ContentPane中。
//下面是一个功能比较强的例子，仔细研究它，当可以知道各个属性的用法。
//样例：

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class MyTable extends JFrame {
	JPanel contentPane = new JPanel();
	JScrollPane TableScrollPane = new JScrollPane();
	JPanel customTablePane = new JPanel();
	JScrollPane TableScrollPane2 = new JScrollPane();
	BorderLayout borderLayout1 = new BorderLayout();

	public MyTable() {
		try {
			jbInit();
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) {
		MyTable frame = new MyTable();
		frame.setVisible(true);
	}

	// 当窗口关闭时的处理代码

	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}
	}

	private void jbInit() throws Exception {
		// 设置窗口的标题,大小,布局
		setTitle("列表实例演示");
		setSize(new Dimension(565, 366));
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		Object[][] data = { { "小李", "男", "滑雪", new Integer(20), "英文", new Boolean(true) },
				{ "小青", "女", "保龄球", new Integer(17), "绘画", new Boolean(true) },
				{ "小吴", "男", "跑步", new Integer(23), "管理", new Boolean(true) },
				{ "小和", "女", "演讲", new Integer(25), "会计", new Boolean(false) },
				{ "小许", "女", "游泳", new Integer(15), "数字", new Boolean(false) } };
		String[] columnNames = { "姓名", "性别", "爱好", "年龄", "专业", "是否被录取" };
		// 创建一个简单的表格
		final JTable table = new JTable(data, columnNames);
		// 使选择框成为一个单元格
		table.setColumnSelectionAllowed(true);
		// 将表格的列排列自动功能设置为OFF,这样便可以使用代码定义列的长度
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(20); // 设置表格的行高度
		// 设置列表的列长度
		TableColumn column = null;
		for (int i = 0; i < 5; i++) {
			// 取得列表的列对象
			column = table.getColumnModel().getColumn(i);
			if (i == 1) {
				column.setPreferredWidth(30); // sport column is bigger
			} else {
				column.setPreferredWidth(50);
			}
		}
		// 取得表格的行
		ListSelectionModel rowSM = table.getSelectionModel();
		// 加入行选择接收器
		rowSM.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// 当多种事件被激发的时候,不执行行接收器后面的代码
				if (e.getValueIsAdjusting())
					return;
				// 取得行对象
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				int selectedRow = lsm.getMinSelectionIndex();
				System.out.println("行" + selectedRow);
			}
		});
		// 取得表格的列
		ListSelectionModel colSM = table.getColumnModel().getSelectionModel();
		// 加入列选择接收器
		colSM.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// 当多种事件被激发的时候,不执行列接收器后面的代码
				if (e.getValueIsAdjusting()) {
					return;
				}
				// 取得列对象
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				int selectedCol = lsm.getMinSelectionIndex();
				System.out.println("列" + selectedCol);
			}
		});
		// 创建带有模式类的列表框,并加入数据改变接收器
		MyTableModel1 myModel = new MyTableModel1();
		final JTable table1 = new JTable(myModel);
		table1.setColumnSelectionAllowed(true);
		table1.setRowHeight(30);
		// 当表格的内容改变的时候取得表格的内容
		final TableModel model = table1.getModel();
		model.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int column = e.getColumn();
				// 取得列表框的数据
				Object data = model.getValueAt(row, column);
				try {
					System.out.println(data);
					table1.requestFocus();
				} catch (Exception e1) {
				}
			}
		});

		// 为数据表中"爱好"一列加入下拉列表框
		TableColumn sportColumn = table1.getColumnModel().getColumn(4);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox comboBox = new JComboBox(new String[] { "滑雪", "保龄球", "保龄球", "跑步", "游泳" });
		sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
		// 为数据表的数字列加入数字检证框
		final JTextField integerField = new JTextField();
		integerField.setHorizontalAlignment(JTextField.RIGHT);
		DefaultCellEditor integerEditor = new DefaultCellEditor(integerField) {
			// 重载返回值的方法,当输入文字的时候，将文字转变为0
			public Object getCellEditorValue() {
				try {
					return new Double(integerField.getText());
				} catch (Exception e) {
					return new Double(0);
				}
			}
		};
		table1.setDefaultEditor(Integer.class, integerEditor);
		// 设置颜色接收器
		setUpColorEditor(table1);
		// 设置颜色渲染器
		table1.setDefaultRenderer(Color.class, new ColorRenderer(true));
		// 设置容器的布局及加入各种控件
		customTablePane.setLayout(borderLayout1);
		TableScrollPane.getViewport().add(table);
		TableScrollPane2.getViewport().add(table1);
		contentPane.add(TableScrollPane);
		contentPane.add(customTablePane, null);
		customTablePane.add(TableScrollPane2, BorderLayout.CENTER);
	}

	// 设置颜色编辑框的方法
	private void setUpColorEditor(JTable table) {
		// 创建按钮,用于激活颜色选择框
		final JButton button = new JButton("") {
			public void setText(String s) {
				// 重新定义SetText方法,使按钮不会显示文字
			}
		};
		// 设置按钮的背景颜色,不绘画按钮的边框,并将按钮的边框设为0
		button.setBackground(Color.white);
		button.setBorderPainted(false);
		button.setMargin(new Insets(0, 0, 0, 0));
		// 创建颜色编辑框,并将该编辑框赋给表格
		final ColorEditor colorEditor = new ColorEditor(button);
		table.setDefaultEditor(Color.class, colorEditor);
		// 创建颜色选择框
		final JColorChooser colorChooser = new JColorChooser();
		ActionListener okListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 将颜色选择框的颜色赋给颜色编辑框
				colorEditor.currentColor = colorChooser.getColor();
			}
		};
		// 创建颜色选择框
		final JDialog dialog = JColorChooser.createDialog(button, "请选择喜欢的颜色", true, colorChooser, okListener, null);
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				// 设置按钮的背景颜色
				button.setBackground(colorEditor.currentColor);
				// 将颜色选择器的颜色设为原先的显示颜色
				colorChooser.setColor(colorEditor.currentColor);
				// 在按钮的旁边显示颜色选择框,省略该命令,颜色选择框会显示在Window
				// 窗口的中央
				dialog.setLocationRelativeTo(button);
				dialog.show();
			}
		});
	}
}

// 表格模式类
@SuppressWarnings("serial")
class MyTableModel1 extends AbstractTableModel {
	final Object[][] data = {
			{ "小李", new Color(56, 78, 82), new ImageIcon("face1.gif"), "男", "滑雪", new Integer(20), "英文",
					new Boolean(true) },
			{ "小青", new Color(56, 78, 82), new ImageIcon("face2.gif"), "女", "保龄球", new Integer(17), "绘画",
					new Boolean(true) },
			{ "小吴", new Color(56, 78, 82), new ImageIcon("face3.gif"), "男", "保龄球", new Integer(23), "管理",
					new Boolean(true) },
			{ "小和", new Color(56, 78, 82), new ImageIcon("face4.gif"), "女", "跑步", new Integer(25), "会计",
					new Boolean(false) },
			{ "小许", new Color(56, 78, 82), new ImageIcon("face5.gif"), "女", "游泳", new Integer(15), "数字",
					new Boolean(false) } };
	final String[] columnNames = { "姓名", "喜欢的颜色", "图片", "性别", "爱好", "年龄", "专业", "是否被录取" };

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

	// 该方法不一定要加入AbstractTableModel类,该方法设置列表的标题
	public String getColumnName(int col) {
		return columnNames[col];
	}

	// 通过getClass()方法设置默认的控件,如true与false的默认控件是复选框,
	// 该方法不一定要加入AbstractTableModel类
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	// 定义可以编辑的单元,true表示可编辑,false表示不可编辑
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	// 将改变后的值保存在表格中
	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

}

// 颜色渲染框的代码
@SuppressWarnings("serial")
class ColorRenderer extends JLabel implements TableCellRenderer {
	Border unselectedBorder = null;
	Border selectedBorder = null;
	boolean isBordered = true;

	public ColorRenderer(boolean isBordered) {
		super();
		this.isBordered = isBordered;
		setOpaque(true);
	}

	// 每当颜色改变时,该方法被激活
	public Component getTableCellRendererComponent(JTable table, Object color, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setBackground((Color) color);
		if (isBordered) {
			if (isSelected) {
				if (selectedBorder == null) {
					selectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5, table.getSelectionBackground());
				}
				setBorder(selectedBorder);
			} else {
				if (unselectedBorder == null) {
					unselectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5, table.getBackground());
				}
				setBorder(unselectedBorder);
			}
		}
		return this;
	}

}

// 颜色编辑框的代码
@SuppressWarnings("serial")
class ColorEditor extends DefaultCellEditor {
	Color currentColor = null;

	public ColorEditor(JButton b) {
		// 由于数据表的编辑器只能是复选框,编辑框与标签,所以首先将数据表的编辑器设为
		// 复选框
		super(new JCheckBox());
		// 利用DefaultCellEditor类的editorComponent属性将编辑器设为按钮
		editorComponent = b;
		setClickCountToStart(1); // 单击按钮时动作事件激活
		// 让编辑器停止事件的处理,让按钮处理动作事件
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}

	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}

	public Object getCellEditorValue() {
		return currentColor;
	}

	// 当颜色改变时,该方法被激活
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		currentColor = (Color) value;
		return editorComponent;
	}

}
