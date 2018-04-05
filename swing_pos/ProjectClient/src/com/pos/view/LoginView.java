package com.pos.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.pos.mode.User;
import com.pos.tool.GetSocket;
import com.pos.tool.SingleUser;

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
	public Socket socket = GetSocket.getSocke();
	
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
		
		InputStream inputStream = null;//输入流
		BufferedReader bufferedReader = null;
		
		OutputStream outputStream = null;//输出流
		PrintWriter printWriter = null;
		
		String msg = null;//消息
		
		if (e.getSource() == loginButton) {
			
			try {
				
				String username = userText.getText();
				String password = passwordText.getText();
				User user = SingleUser.getUser();
				user.setUsername(username);
				user.setPassword(password);	
				
				outputStream = socket.getOutputStream();
				printWriter = new PrintWriter(outputStream);
				msg	= "login+validate+"+user.toString();
				printWriter.println(msg);
				printWriter.flush();
		
				inputStream = socket.getInputStream();
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				msg = bufferedReader.readLine();
				
				if("yes".equals(msg))	{
					frame.dispose();
					new MainPosFrame();
				}
				else {
					frame.dispose();
					new LoginView();
				}
				
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
		}
		else {
			frame.dispose();
			new UpdatePwordView();
		}
	}
}


