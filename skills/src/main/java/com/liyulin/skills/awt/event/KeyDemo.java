package com.liyulin.skills.awt.event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 键盘事件
 * 
 * @author liyulin
 * @date 2013/03/08
 */
@SuppressWarnings("serial")
public class KeyDemo extends JFrame implements KeyListener {

	private JPanel p;
	private JButton b;

	public KeyDemo() {
		super("键盘控制");
		p = new JPanel();
		b = new JButton("用方向键控制");
		p.add(b);
		b.addKeyListener(this);
		this.add(p);
		this.setSize(200, 150);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(3);
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();// 获取按下键盘的码值
		int x = b.getX();
		int y = b.getY();
		if (key == KeyEvent.VK_RIGHT) {
			b.setLocation(x + 5, y);
		} else if (key == KeyEvent.VK_LEFT) {
			b.setLocation(x - 5, y);
		} else if (key == KeyEvent.VK_UP) {
			b.setLocation(x, y - 5);
		} else if (key == KeyEvent.VK_DOWN) {
			b.setLocation(x, y + 5);
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public static void main(String[] args) {
		KeyDemo f = new KeyDemo();
		f.setVisible(true);
	}
}
