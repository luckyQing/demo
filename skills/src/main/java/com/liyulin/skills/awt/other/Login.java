package com.liyulin.skills.awt.other;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 登陆界面模拟
 * 
 * @author liyulin
 * @date 2013/03/08
 */
@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel p;
	private JLabel lblName, lblPwd;
	private JTextField txtName;
	private JPasswordField txtPwd;
	private JButton btnOK, btnCancle;

	public Login() {
		super("登录");
		p = new JPanel();
		p.setLayout(null);
		lblName = new JLabel("用户名");
		lblPwd = new JLabel("密码");
		txtName = new JTextField(20);
		txtPwd = new JPasswordField(20);
		txtPwd.setEchoChar('*');
		btnOK = new JButton("确定");
		btnCancle = new JButton("取消");
		lblName.setBounds(30, 30, 60, 25);
		lblPwd.setBounds(30, 60, 60, 25);
		txtName.setBounds(95, 30, 120, 25);
		txtPwd.setBounds(95, 60, 120, 25);
		btnOK.setBounds(60, 90, 60, 25);
		btnCancle.setBounds(125, 90, 60, 25);
		p.add(lblName);
		p.add(txtName);
		p.add(lblPwd);
		p.add(txtPwd);
		p.add(btnOK);
		p.add(btnCancle);
		this.add(p);
		this.setSize(250, 170);
		this.setLocation(300, 300);
		this.setResizable(false);
		this.setDefaultCloseOperation(3);
	}

	public static void main(String[] args) {
		Login f = new Login();
		f.setVisible(true);
	}
}
