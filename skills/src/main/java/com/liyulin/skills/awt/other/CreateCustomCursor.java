package com.liyulin.skills.awt.other;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

/***
 * 自定义光标
 */
public class CreateCustomCursor {

	private JFrame frame;

	public CreateCustomCursor() {
		frame = new JFrame("自定义光标");
		JPanel panel = new JPanel();
		panel.setCursor(CreateCursor());
		frame.add(panel);
		frame.setSize(400, 250);
		frame.setDefaultCloseOperation(3);
		frame.setVisible(true);
	}

	// 自定义光标
	public Cursor CreateCursor() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		String imagePath = ToolBarDemo.class.getClassLoader().getResource("awt/cursor.gif").getPath();
		Image img = tk.getImage(imagePath);
		Cursor dynamiteCursor = tk.createCustomCursor(img, new Point(10, 10), imagePath);
		return dynamiteCursor;
	}

	public static void main(String[] args) {
		new CreateCustomCursor();
	}

}
