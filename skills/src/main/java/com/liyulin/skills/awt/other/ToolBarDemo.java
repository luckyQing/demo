package com.liyulin.skills.awt.other;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 * 工具条
 * 
 * @author liyulin
 * @date 2013/03/08
 */
@SuppressWarnings("serial")
public class ToolBarDemo extends JFrame {

	private JPanel p;
	private JToolBar toolBar;
	private JButton btnCopy, btnPost, btnCut;

	public ToolBarDemo() {
		super("工具栏");
		p = new JPanel();
		toolBar = new JToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		String imagePath = ToolBarDemo.class.getClassLoader().getResource("awt/").getPath();
		btnCopy = new JButton(new ImageIcon(imagePath + "/copy.gif"));
		btnPost = new JButton(new ImageIcon(imagePath + "/paste.gif"));
		btnCut = new JButton(new ImageIcon(imagePath + "/cut.gif"));
		btnCopy.setToolTipText("复制");
		btnPost.setToolTipText("粘贴");
		btnCut.setToolTipText("剪切");
		toolBar.add(btnCopy);
		toolBar.add(btnPost);
		toolBar.add(btnCut);
		this.add(p);
		this.setSize(200, 150);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(3);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new ToolBarDemo();
	}
	
}
