package com.liyulin.skills.awt.other;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

/***
 * 参考《Java核心技术（卷|）》第 275／708 页
 */
public class Brighter {

	public static void main(String[] args) {
		JFrame frame = new JFrame("颜色变淡");
		JPanel panel = new JPanel();
		panel.setBackground(Color.green.brighter().brighter().brighter());// 加亮当前颜色
		panel.setBackground(Color.green.darker().darker().darker()); // 变暗当前颜色
		frame.add(panel);
		frame.setSize(200, 150);
		frame.setDefaultCloseOperation(3);
		frame.setVisible(true);
	}
}
