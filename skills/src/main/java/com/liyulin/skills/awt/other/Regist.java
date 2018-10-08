package com.liyulin.skills.awt.other;

import java.awt.*;
import javax.swing.*;

/**
 * 注册界面模拟
 * 
 * @author liyulin
 * @date 2013/03/08
 */
@SuppressWarnings("serial")
public class Regist extends JFrame {

	private JPanel p;
	private JLabel lblName, lblPwd, lblRePwd, lblSex, lblLike, lblAddr, lblDegree;
	private JTextField txtName;
	private JPasswordField txtPwd, txtRePwd;
	private JRadioButton rbMale, rbFemale;
	private JCheckBox ckbRead, ckbNet, ckbSwim, ckbTour;
	private JTextArea txtAddr;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbDegree;
	private JButton btnOK, btnCancle;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Regist() {
		super("用户注册");
		p = new JPanel(new GridLayout(8, 1));
		lblName = new JLabel("用户名：");
		lblPwd = new JLabel("密    码：");
		lblRePwd = new JLabel("确认密码");
		lblSex = new JLabel("性别：");
		lblLike = new JLabel("爱好：");
		lblAddr = new JLabel("地址");
		lblDegree = new JLabel("学历：");
		txtName = new JTextField(20);
		txtPwd = new JPasswordField(20);
		txtRePwd = new JPasswordField(20);
		rbMale = new JRadioButton("男");
		rbFemale = new JRadioButton("女");
		ckbRead = new JCheckBox("阅读");
		ckbNet = new JCheckBox("上网");
		ckbSwim = new JCheckBox("游泳");
		ckbTour = new JCheckBox("旅游");
		txtAddr = new JTextArea(3, 20);
		String str[] = { "小学", "中学", "高中", "本科", "研究生" };
		cmbDegree = new JComboBox(str);
		cmbDegree.setEditable(true);
		btnOK = new JButton("确定");
		btnCancle = new JButton("取消");
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbMale);
		bg.add(rbFemale);
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p1.add(lblName);
		p1.add(txtName);
		p.add(p1);
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p2.add(lblPwd);
		p2.add(txtPwd);
		p.add(p2);
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p3.add(lblRePwd);
		p3.add(txtRePwd);
		p.add(p3);
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p4.add(lblSex);
		p4.add(rbMale);
		p4.add(rbFemale);
		p.add(p4);
		JPanel p5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p5.add(lblLike);
		p5.add(ckbRead);
		p5.add(ckbNet);
		p5.add(ckbSwim);
		p5.add(ckbTour);
		p.add(p5);
		JPanel p6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p6.add(lblAddr);
		p6.add(txtAddr);
		p.add(p6);
		JPanel p7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p7.add(lblDegree);
		p7.add(cmbDegree);
		p.add(p7);
		JPanel p8 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p8.add(btnOK);
		p8.add(btnCancle);
		p.add(p8);
		this.add(p);
		this.setSize(305, 350);
		this.setLocation(300, 300);
		this.setResizable(false);
		this.setDefaultCloseOperation(3);
	}

	public static void main(String[] args) {
		Regist f = new Regist();
		f.setVisible(true);
	}
}
