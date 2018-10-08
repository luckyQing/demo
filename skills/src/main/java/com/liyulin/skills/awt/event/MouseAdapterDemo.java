package com.liyulin.skills.awt.event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 鼠标事件
 * 
 * @author liyulin
 * @date 2013/03/08
 */
public class MouseAdapterDemo extends MouseAdapter {

	private JFrame f;
	private JPanel p;
	private JButton b;

	public MouseAdapterDemo() {
		f = new JFrame("抓不到我");
		p = new JPanel();
		b = new JButton("你就碰不到我");
		b.addMouseListener(this);
		p.add(b);
		f.add(p);
		f.setSize(400, 300);
		f.setLocation(100, 100);
		f.setDefaultCloseOperation(3);
		f.setVisible(true);
	}

	public void mouseEntered(MouseEvent e) {
		int x = (int) (Math.random() * 400);
		int y = (int) (Math.random() * 300);
		b.setLocation(x, y);
	}

	public static void main(String[] args) {
		new MouseAdapterDemo();
	}
}
