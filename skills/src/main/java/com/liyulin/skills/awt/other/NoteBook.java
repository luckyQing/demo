package com.liyulin.skills.awt.other;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

/**
 * 记事本模拟
 * 
 * @author liyulin
 * @date 2013/03/08
 */
@SuppressWarnings("serial")
public class NoteBook extends JFrame implements ActionListener {

	private JMenuBar menuBar;
	private JMenu menuFile, menuHelp;
	private JMenuItem miNew, miOpen, miSave, miExit, miAbout;
	private JScrollPane sp;
	private JTextArea txtContent;

	public NoteBook() {
		super("记事本");
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		menuFile = new JMenu("文件");
		menuHelp = new JMenu("帮助");
		menuBar.add(menuFile);
		menuBar.add(menuHelp);
		miNew = new JMenuItem("新建");
		miOpen = new JMenuItem("打开");
		miSave = new JMenuItem("保存");
		miExit = new JMenuItem("退出");
		miAbout = new JMenuItem("关于");
		menuFile.add(miNew);
		menuFile.add(miOpen);
		menuFile.add(miSave);
		menuFile.addSeparator();
		menuFile.add(miExit);
		menuHelp.add(miAbout);
		// 监听事件
		miNew.addActionListener(this);
		miOpen.addActionListener(this);
		miSave.addActionListener(this);
		miExit.addActionListener(this);
		miAbout.addActionListener(this);
		txtContent = new JTextArea(20, 20);
		sp = new JScrollPane(txtContent);
		this.add(sp);
		this.setSize(400, 300);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == miNew) {
			txtContent.setText("");
		} else if (source == miOpen) {
			txtContent.setText("");
			openFile();
		} else if (source == miSave) {
			saveFile();
		} else if (source == miExit) {
			System.exit(0);
		} else if (source == miAbout) {
			JOptionPane.showMessageDialog(this, "版本：20121101\n作者：李玉林\n版权所有：Liyulin", "关于", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void openFile() {
		JFileChooser fc = new JFileChooser();
		int rVal = fc.showOpenDialog(this);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			String fileName = fc.getSelectedFile().getName();
			String path = fc.getCurrentDirectory().toString();
			try {
				FileReader fread = new FileReader(path + "/" + fileName);
				BufferedReader bread = new BufferedReader(fread);
				String line = bread.readLine();
				while (line != null) {
					txtContent.append(line + "\n");
					line = bread.readLine();
				}
				bread.close();
				fread.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void saveFile() {
		JFileChooser fc = new JFileChooser();
		int rVal = fc.showSaveDialog(this);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			String fileName = fc.getSelectedFile().getName();
			String path = fc.getCurrentDirectory().toString();
			try {
				FileWriter fwriter = new FileWriter(path + File.separator + fileName);
				fwriter.write(txtContent.getText());
				fwriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setLookAndFeel() {
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println(os);
		try {
			if (os.contains("win")) {
				UIManager.setLookAndFeel(new WindowsLookAndFeel());
			} else if (os.contains("linux")) {
				UIManager.setLookAndFeel(new MetalLookAndFeel());
			} else if (os.contains("mac")) {
				UIManager.setLookAndFeel(new NimbusLookAndFeel());
			}
		} catch (Exception e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this, "Look And Feel Exception", "异常", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new NoteBook().setLookAndFeel();
	}
}
