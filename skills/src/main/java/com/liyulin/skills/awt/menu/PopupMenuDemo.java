package com.liyulin.skills.awt.menu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 * 弹出菜单
 * 
 * @author liyulin
 * @date 2013/03/08
 */
@SuppressWarnings("serial")
public class PopupMenuDemo extends JFrame {

	private JPanel p;
	private JPopupMenu popMenu;
	private JMenuItem miUndo, miCopy, miPost, miCut;

	public PopupMenuDemo() {
		super("弹出菜单");
		p = new JPanel();
		popMenu = new JPopupMenu("弹出");
		miUndo = new JMenuItem("撤销");
		miCopy = new JMenuItem("复制");
		miPost = new JMenuItem("粘贴");
		miCut = new JMenuItem("剪切");
		popMenu.add(miUndo);
		popMenu.addSeparator();
		popMenu.add(miCopy);
		popMenu.add(miPost);
		popMenu.add(miCut);
		p.addMouseListener(new MouseAction());
		this.add(p);
		this.setSize(300, 200);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(3);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new PopupMenuDemo();
	}

	private class MouseAction extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON3) {// 左键弹出为BUTTON1，右键弹出为BUTTON3
				int x = e.getX();
				int y = e.getY();
				popMenu.show(p, x, y);
			}
		}
	}
}
