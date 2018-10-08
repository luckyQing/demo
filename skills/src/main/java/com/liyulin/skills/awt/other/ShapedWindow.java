package com.liyulin.skills.awt.other;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;

public class ShapedWindow {

	/** 创建任意形状的窗口 */
	public static void createShapedWindow() {
		JFrame frame = new JFrame("任意形状窗口");
		frame.setUndecorated(true);
		Shape shape = new Ellipse2D.Float(0, 0, 400, 300);
		frame.setShape(shape);// 设置窗口形状
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(2);
		frame.setLocationRelativeTo(null);// 居中
		/**
		 * Window类中有一组方法用来获取和设置窗口的透明度，分别是getOpacity和setOpacity。
		 * 透明度的区间是0到1,0表示完全透明，而1则表示完全不透明。
		 */
		frame.setOpacity(0.9f);

		frame.setVisible(true);
	}

	public static void main(String[] args) {
		createShapedWindow();
	}

}
