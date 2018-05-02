package com.pos.view.root;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.pos.tool.GetSocket;

@SuppressWarnings("serial")
public class DataBackupsView extends JPanel implements ActionListener{

	public JLabel mainLabel,hostIP,userName,password,savePath,fileName,databaseName;
	public JTextField textFieldhostIP,textFielduserName,textFieldsavePath,textFieldfileName,textFielddatabaseName;
	public JPasswordField textFieldpassword;
	public JButton save;
	public JPanel panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8;
	public String Sip,Susername,Spassword,SsavePath,SfileName,SdatabaseName;
	
	public DataBackupsView() {
		init();
	}
	
	private void init() {
		this.setLayout(null);
		
		panel1 = new JPanel();
		panel1.setBounds(0,0,960,50);
		mainLabel = new JLabel("备份数据库");
		panel1.add(mainLabel);
		
		panel2 = new JPanel();
		panel2.setBounds(0,60,960,50);
		hostIP = new JLabel("IP地址  ");
		textFieldhostIP = new JTextField(13);
		panel2.add(hostIP);panel2.add(textFieldhostIP);
		
		panel3 = new JPanel();
		panel3.setBounds(0,120,960,50);
		userName = new JLabel("用户名  ");
		textFielduserName = new JTextField(13);
		panel3.add(userName);panel3.add(textFielduserName);
		
		panel4 = new JPanel();
		panel4.setBounds(0,180,960,50);
		password = new JLabel("密码        ");
		textFieldpassword = new JPasswordField(13);
		panel4.add(password);panel4.add(textFieldpassword);
		
		panel5 = new JPanel();
		panel5.setBounds(0,240,960,50);
		savePath = new JLabel("保存路径");
		textFieldsavePath = new JTextField(13);
		panel5.add(savePath);panel5.add(textFieldsavePath);
		
		panel6 = new JPanel();
		panel6.setBounds(0, 300, 960, 50);
		fileName = new JLabel("文件名称");
		textFieldfileName = new JTextField(13);
		panel6.add(fileName);panel6.add(textFieldfileName);
		
		panel7 = new JPanel();
		panel7.setBounds(0, 360, 960, 50);
		databaseName = new JLabel("数据库名");
		textFielddatabaseName = new JTextField(13);
		panel7.add(databaseName);panel7.add(textFielddatabaseName);
		
		panel8 = new JPanel();
		panel8.setBounds(0, 420, 960, 50);
		save = new JButton("save");
		panel8.add(save);
		save.addActionListener(this);
		
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.add(panel5);
		this.add(panel6);
		this.add(panel7);
		this.add(panel8);
		
		this.validate();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = null;
		
		Sip = textFieldhostIP.getText();
		Susername = textFielduserName.getText();
		Spassword = textFieldpassword.getText();
		SsavePath = textFieldsavePath.getText();
		SfileName = textFieldfileName.getText();
		SdatabaseName = textFielddatabaseName.getText();
		
		if(!Sip.equals("")&&!Susername.equals("")&&!Spassword.equals("")&&!SsavePath.equals("")&&!SfileName.equals("")&&!SdatabaseName.equals(""))
		{
			msg = "database"+"+"+Sip+"+"+Susername+"+"+Spassword+"+"+SsavePath+"+"+SfileName+"+"+SdatabaseName;
			try {
				Socket socket = GetSocket.getSocke();
				OutputStream outputStream = socket.getOutputStream();
				PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream,"utf-8"));
				printWriter.println(msg);
				printWriter.flush();
				
				msg = null;
				InputStream inputStream = socket.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				msg = bufferedReader.readLine();
				if("yes".equals(msg))
				{
					JOptionPane.showMessageDialog(null, "备份成功", "警告",JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "备份失败", "警告",JOptionPane.WARNING_MESSAGE);
				}
				
			} catch (Exception e2) {
				
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "字段为空", "警告",JOptionPane.WARNING_MESSAGE);
		}
	
	}
	
}
