package com.pos.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.context.ApplicationContext;

import com.pos.duitl.Dutil;
import com.pos.mode.User;
import com.pos.server.UserServices;
import com.pos.server.impl.UserServicesImpl;

public class UpdatePwordView implements ActionListener{
	
	public JPanel panel;//主面板
	public JLabel titleLabel;//标题标签
	
	public JLabel userLabel;//用户名标签
	public JTextField userText;//文本框
	
	public JLabel passwordLabel0;//新密码
	public JPasswordField passwordText0;//文本框
	
	public JLabel passwordLabel1;//确认密码
	public JPasswordField passwordText1;//文本框
	
	public JButton saveButton,reveiveButton;//登录按钮
	
	public JFrame frame;//主窗体
	
	private static ApplicationContext ct = Dutil.getApplicationContext();
	
	public UpdatePwordView()
	{
		//this.socket = socket;
		frame = new JFrame("UpdatePassword");
		
        frame.setSize(650, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();    
        // 添加面板
        frame.add(panel);
        
        //调用用户定义的方法并添加组件到面板
        placeComponents(panel);

        // 设置界面可见
        frame.setVisible(true);
	}


	private void placeComponents(JPanel panel2) {
		panel.setLayout(null);

		titleLabel = new JLabel("请重置密码");
		titleLabel.setFont(new java.awt.Font("Dialog", 1, 15));
		titleLabel.setForeground(Color.red);
		titleLabel.setBounds(275, 70, 255, 35);
		panel.add(titleLabel);
	        
		// 创建 JLabel
		userLabel = new JLabel("用户名：");
		userLabel.setBounds(190, 120, 110, 25);
		userLabel.setFont(new java.awt.Font("Dialog", 1, 15));
		userLabel.setForeground(Color.red);
		panel.add(userLabel);

		/*
		 * 创建文本域用于用户输入
		 */
		userText = new JTextField(20);
		userText.setBounds(310, 120, 175, 25);
		panel.add(userText);

		// 输入密码的文本域
		passwordLabel0 = new JLabel("新密码：");
		passwordLabel0.setBounds(190, 150, 110, 25);
		passwordLabel0.setFont(new java.awt.Font("Dialog", 1, 15));
		passwordLabel0.setForeground(Color.red);
		panel.add(passwordLabel0);

		passwordText0 = new JPasswordField(20);
		passwordText0.setBounds(310, 150, 175, 25);
		panel.add(passwordText0);
		
		passwordLabel1 = new JLabel("确认密码:");
		passwordLabel1.setBounds(190, 180, 110, 25);
		passwordLabel1.setFont(new java.awt.Font("Dialog", 1, 15));
		passwordLabel1.setForeground(Color.red);
		panel.add(passwordLabel1);

		passwordText1 = new JPasswordField(20);
		passwordText1.setBounds(310, 180, 175, 25);
		panel.add(passwordText1);
		
		saveButton = new JButton("提交");
		saveButton.setBounds(220, 220, 90, 25);
		saveButton.addActionListener(this);
		
		reveiveButton = new JButton("返回");
		reveiveButton.setBounds(330, 220, 90, 25);
		reveiveButton.addActionListener(this);
		
		panel.add(saveButton);
		panel.add(reveiveButton);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == saveButton)
		{
			try {
				
				String username = userText.getText();
				String password = passwordText0.getText();
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);	
				
				UserServices userServices = (UserServicesImpl)ct.getBean("userServicesImpl");
				
				if(true==userServices.update(user.getUsername(),user.getPassword()))
				{
					frame.dispose();
					new LoginView();
				}
				else {
					System.out.println("error");
				}
				
			} catch (Exception e2) {
				
			}
		}
		else {
			frame.dispose();
			new LoginView();
		}
	}
	
}
