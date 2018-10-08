package com.liyulin.skills.awt.other;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * RadioButton按钮
 * 
 * @author liyulin
 * @date 2013/03/08
 * @version 1.0
 */
@SuppressWarnings("serial")
public class RadioButtonDemo extends JFrame {

	private JPanel p;
	private JRadioButton rb1, rb2;
	private ButtonGroup bg;

	public RadioButtonDemo() {
		super("单选");
		p = new JPanel();
		rb1 = new JRadioButton("男", true);
		rb2 = new JRadioButton("女");
		bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		p.add(rb1);
		p.add(rb2);
		this.add(p);
		this.setSize(200, 100);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(3);
	}

	public static void main(String[] args) {
		RadioButtonDemo f = new RadioButtonDemo();
		f.setVisible(true);
	}
}
