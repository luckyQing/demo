package com.liyulin.skills.awt.other;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/***
 * 设置窗口居中显示
 */
public class CenterWindow {

	private JFrame frame = null;
	private int WIDTH = 0;
	private int HEIGHT = 0;

	public CenterWindow() {
		frame = new JFrame("设置窗口居中");
		setWindowSize();
		setWindowPosition();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(3);
	}

	// 设置窗口居中大小
	public void setWindowSize() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		WIDTH = screenSize.width;
		HEIGHT = screenSize.height;

		frame.setSize(WIDTH / 2, HEIGHT / 2);
		frame.setLocationByPlatform(true);// 设置在下次窗口可见时，此窗口应显示在当前位置
	}

	// 设置窗口居中的位置
	public void setWindowPosition() {
		frame.setLocation(WIDTH / 2 - frame.getWidth() / 2, HEIGHT / 2 - frame.getHeight() / 2);
	}

	public static void main(String[] args) {
		new CenterWindow();
	}

}
