package com.liyulin.skills.awt.other;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 卡片布局、点击事件、按键移动问题
 * 
 * @author liyulin
 * @date 2012/12/19
 */
public class Card implements KeyListener {
	JFrame frame; // 框架
	JPanel mainPanel, panel1, panel2; // 主面板，分面板1，分面板2
	CardLayout myCard; // 卡片布局
	JButton button1, button2; // 按钮1、2
	int x1 = 0, y1 = 0, x2 = 0, y2 = 0;// 按钮1、2的初始位置

	public Card() {
		frame = new JFrame("卡片布局问题");
		mainPanel = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		myCard = new CardLayout();
		button1 = new JButton("按钮1");
		button2 = new JButton("按钮2");

		frame.add(mainPanel);
		mainPanel.setLayout(myCard);// 将主面板设置为卡片布局
		mainPanel.add(panel1, "first");
		mainPanel.add(panel2, "second");
		// 将按钮1、2分别添加到面板1、2
		panel1.add(button1);
		panel2.add(button2);
		// 设置面板1、2的背景
		panel1.setBackground(Color.BLACK);
		panel2.setBackground(Color.RED);

		// 面板1添加鼠标事件
		panel1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				panel1MouseClicked(evt);
			}
		});

		// 面板2添加鼠标事件
		panel2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				panel2MouseClicked(evt);
			}
		});

		// 按钮1、2添加键盘事件
		button1.addKeyListener(this);
		button2.addKeyListener(this);
		// 将主面板设置为第一个显示的面板
		myCard.show(mainPanel, "first");
		frame.setDefaultCloseOperation(3);
		frame.setSize(400, 300);
		frame.setVisible(true);
	}

	/**
	 * panel1点击事件
	 * 
	 * @param e
	 */
	public void panel1MouseClicked(MouseEvent e) {
		myCard.next(mainPanel);
		button2.requestFocus();// button2获得焦点
	}

	/**
	 * panel2点击事件
	 * 
	 * @param e
	 */
	public void panel2MouseClicked(MouseEvent e) {
		myCard.next(mainPanel);
		button1.requestFocus();// button1获得焦点
	}

	/**
	 * 键盘事件
	 */
	public void keyPressed(KeyEvent keycommand) {
		switch (keycommand.getKeyCode()) {
		case KeyEvent.VK_DOWN:// 按钮1下移
			y1 += 15;
			button1.setLocation(x1, y1);
			break;
		case KeyEvent.VK_UP:// 按钮1上移
			y1 -= 15;
			button1.setLocation(x1, y1);
			break;
		case KeyEvent.VK_LEFT:// 按钮2左移
			x2 -= 15;
			button2.setLocation(x2, y2);
			break;
		case KeyEvent.VK_RIGHT:// 按钮2右移
			x2 += 15;
			button2.setLocation(x2, y2);
			break;
		default:
			break;
		}
	}

	public void keyReleased(KeyEvent keycommand) {
	}

	public void keyTyped(KeyEvent keycommand) {
	}

	public static void main(String[] args) {
		new Card();
	}
}
