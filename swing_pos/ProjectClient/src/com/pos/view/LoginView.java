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
import com.pos.view.cargo.MainPosFrameCargo;
import com.pos.view.customer.MainPosFrameCustomer;
import com.pos.view.department.MainPosFrameDepartment;
import com.pos.view.employee.MainPosFrameEmployee;
import com.pos.view.purchase.MainPosFramePurchase;
import com.pos.view.root.MainPosFrameRoot;
import com.pos.view.sale.MainPosFrameSale;
import com.pos.view.warehouse.MainPosFrameWarehouse;

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
				
				String[] args = msg.split("\\+");
				
				if("yes".equals(args[0]))	{
					//权限控制
					if("总经理".equals(args[1]))
					{
						frame.dispose();
						new MainPosFrame();
					}
					else if("部门管理员".equals(args[1]))
					{
						frame.dispose();
						new MainPosFrameDepartment();
					}
					else if ("人事管理员".equals(args[1])) {
						frame.dispose();
						new MainPosFrameEmployee();
					}
					else if ("客户管理员".equals(args[1])) {
						frame.dispose();
						new MainPosFrameCustomer();
					}
					else if ("货物管理员".equals(args[1])) {
						frame.dispose();
						new MainPosFrameCargo();
					}
					else if ("仓库管理员".equals(args[1])) {
						frame.dispose();
						new MainPosFrameWarehouse();
					}
					else if ("销售管理员".equals(args[1])) {
						frame.dispose();
						new MainPosFrameSale();
					}
					else if ("采购管理员".equals(args[1])) {
						frame.dispose();
						new MainPosFramePurchase();
					}
					else if ("root".equals(args[1])||"superroot".equals(args[1])) {
						frame.dispose();
						new MainPosFrameRoot(args[1]);
					}
					
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
			new ForgetPwordView();
		}
	}
}


