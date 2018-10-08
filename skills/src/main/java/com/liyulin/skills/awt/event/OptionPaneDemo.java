package com.liyulin.skills.awt.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 选择框
 * 
 * @author liyulin
 * @date 2013/03/08
 */
@SuppressWarnings("serial")
public class OptionPaneDemo extends JFrame implements ActionListener {

	private JPanel p;
	private JButton btnInput, btnMsg, btnConfirm, btnOption;
	private JTextField txtResult;

	public OptionPaneDemo() {
		super("标准对话框");
		p = new JPanel();
		btnInput = new JButton("输入");
		btnMsg = new JButton("消息");
		btnConfirm = new JButton("确认");
		btnOption = new JButton("选项");
		txtResult = new JTextField(20);

		btnInput.addActionListener(this);
		btnMsg.addActionListener(this);
		btnConfirm.addActionListener(this);
		btnOption.addActionListener(this);
		p.add(btnInput);
		p.add(btnMsg);
		p.add(btnConfirm);
		p.add(btnOption);
		p.add(txtResult);
		this.add(p);
		this.setSize(300, 150);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(3);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnInput) {
			String strIn = JOptionPane.showInputDialog(btnInput, "请输入姓名：");
			txtResult.setText(strIn);
		}
		if (source == btnMsg) {
			JOptionPane.showMessageDialog(btnMsg, "这是一个消息对话框", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
		if (source == btnConfirm) {
			int r = JOptionPane.showConfirmDialog(btnConfirm, "您确定要删除吗？", "删除", JOptionPane.YES_NO_OPTION);
			if (r == JOptionPane.YES_NO_OPTION) {
				txtResult.setText("删除！");
			}
		}
		if (source == btnOption) {
			Object[] options = { "Red", "Green", "Blue" };
			int sel = JOptionPane.showOptionDialog(btnOption, "颜色选择", "选择", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (sel != JOptionPane.CLOSED_OPTION) {
				txtResult.setText("颜色：" + options[sel]);
			}
		}
	}

	public static void main(String[] args) {
		OptionPaneDemo f = new OptionPaneDemo();
		f.setVisible(true);
	}
}
