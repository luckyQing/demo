package com.liyulin.skills.awt.other;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 颜色面板
 * 
 * @author liyulin
 * @date 2013/03/08
 */
@SuppressWarnings("serial")
public class ColorChooserDemo extends JFrame implements ActionListener {

	private JPanel p;
	private JButton b;
	private JColorChooser ch;
	private JDialog colorDialog;

	public ColorChooserDemo() {
		super("颜色对话框");
		p = new JPanel();
		b = new JButton("改变面板背景颜色");
		b.addActionListener(this);
		ch = new JColorChooser();
		colorDialog = JColorChooser.createDialog(this, "选取颜色", true, ch, null, null);
		p.add(b);
		this.add(p);
		this.setSize(200, 150);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		colorDialog.setVisible(true);
		p.setBackground(ch.getColor());
	}

	public static void main(String[] args) {
		ColorChooserDemo f = new ColorChooserDemo();
		f.setVisible(true);
	}
}
