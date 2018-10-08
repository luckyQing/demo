package com.liyulin.skills.awt.event;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 画图
 * 
 * @author liyulin
 * @date 2013/03/08
 */
@SuppressWarnings("serial")
public class MouseDemo extends JFrame implements MouseMotionListener {

	private JPanel p;
	private int x, y;

	public MouseDemo() {
		super("画板");
		p = new JPanel();
		p.addMouseMotionListener(this);
		this.add(p);
		this.setSize(400, 300);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(3);
	}

	public void paint(Graphics g) {
		g.setColor(Color.blue);
		g.fillOval(x, y, 10, 10);
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		this.repaint();
	}

	public static void main(String[] args) {
		MouseDemo f = new MouseDemo();
		f.setVisible(true);
	}
}
