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
import com.pos.view.root.MainPosFrameRoot;


public class LoginView implements ActionListener{
	
	//创建socket
	public JPanel panel;//主面板
	public JLabel titleLabel;//标题标签
	public JLabel userLabel;//登录按钮
	public JTextField userText;//登录按钮
	public JLabel passwordLabel;//登录按钮
	public JPasswordField passwordText;//登录按钮
	public JButton loginButton;//登录按钮
	public JButton passwordButton;//重置密码
	public JFrame frame;//主窗体
	private static ApplicationContext ct = Dutil.getApplicationContext();
	
	public LoginView()
	{
		//this.socket = socket;
		frame = new JFrame("Login");
		
        frame.setSize(650, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();    
        // 添加面板
        frame.add(panel);
        /* 
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents(panel);

        // 设置界面可见
        frame.setVisible(true);
	}
	
	public void placeComponents(JPanel panel) {

        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);
        
        titleLabel = new JLabel("welcome to use application");
        titleLabel.setFont(new   java.awt.Font("Dialog",1,15));   
        titleLabel.setForeground(Color.red);
        titleLabel.setBounds(220,70,255,35);
        panel.add(titleLabel);
        
        // 创建 JLabel
        userLabel = new JLabel("User:");
        userLabel.setBounds(190,120,80,25);
        userLabel.setFont(new  java.awt.Font("Dialog",1,15));   
        userLabel.setForeground(Color.red);
        panel.add(userLabel);

        /* 
         * 创建文本域用于用户输入
         */
        userText = new JTextField(20);
        userText.setBounds(280,120,165,25);
        panel.add(userText);

        // 输入密码的文本域
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(190,150,80,25);
        passwordLabel.setFont(new  java.awt.Font("Dialog",1,15));   
        passwordLabel.setForeground(Color.red);
        panel.add(passwordLabel);

        
        passwordText = new JPasswordField(20);
        passwordText.setBounds(280,150,165,25);
        panel.add(passwordText);

        // 创建登录按钮
        loginButton = new JButton("登录");
        loginButton.setBounds(200, 200, 90, 25);
        loginButton.addActionListener(this);
        
        // 创建修改密码
        passwordButton = new JButton("忘记密码");
        passwordButton.setBounds(340, 200, 90, 25);
        passwordButton.addActionListener(this);
        
        panel.add(loginButton);
        panel.add(passwordButton);
    }
	
	@SuppressWarnings({ "deprecation"})
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource() == loginButton) {
			
			String username = userText.getText();
			String password = passwordText.getText();
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			
			UserServices userServices = (UserServicesImpl)ct.getBean("userServicesImpl");
			User user2 = userServices.login(user);
			
			if(user2!=null)	{
				if ("root".equals(user2.getType())||"superroot".equals(user2.getType())) {
					frame.dispose();
					new MainPosFrameRoot(user2,user2.getType());
				}
			}
			else {
				frame.dispose();
				new LoginView();
			} 
		}
		else {
			frame.dispose();
			new UpdatePwordView();
		}
	}
}


