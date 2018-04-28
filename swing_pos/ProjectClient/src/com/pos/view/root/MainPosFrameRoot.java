package com.pos.view.root;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.pos.mode.User;
import com.pos.tool.GetSocket;
import com.pos.tool.SingleUser;

@SuppressWarnings("serial")
public class MainPosFrameRoot extends JFrame implements ActionListener{
	
	public JMenuBar bar;
	public JMenu personalMenu,adminMenu,dataMenu;
	public JMenuItem logout,usermessage,adselect,adadd,databackups;
	public JFrame jFrame;
	public JPanel panel = new JPanel();
	public String temp = null;
	
	public MainPosFrameRoot(String temp) {
		this.temp = temp;
		init();
		this.setTitle("MainFrom");
	    this.setVisible(true);
	    this.setSize(950, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
	}

	public void init()
	{
		//菜单栏
		bar = new JMenuBar();
		this.setJMenuBar(bar) ;  //为 窗体设置  菜单工具栏
		
		//菜单项	
		personalMenu = new JMenu("个人中心");
		logout = new JMenuItem("注销");
		usermessage = new JMenuItem(SingleUser.getUser().getUsername());
		personalMenu.add(usermessage);
		personalMenu.add(logout);
		bar.add(personalMenu);
	
		adminMenu = new JMenu("人员管理");
		adselect = new JMenuItem("查询");
		adadd = new JMenuItem("添加");
		adminMenu.add(adadd);
		adminMenu.add(adselect);
		if("superroot".equals(temp))
		{
			bar.add(adminMenu);
			adadd.addActionListener(this);
			adselect.addActionListener(this);
		}
		
		dataMenu = new JMenu("数据管理");
		databackups = new JMenuItem("备份数据库");
		dataMenu.add(databackups);
		bar.add(dataMenu);
		
		logout.addActionListener(this);
		
		databackups.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==logout)
		{
			try {
				Socket socket = GetSocket.getSocke();
				OutputStream outputStream = socket.getOutputStream();
				PrintWriter printWriter = new PrintWriter(outputStream);
				String msg	= "end";
				printWriter.println(msg);
				printWriter.flush();
				
				InputStream inputStream = socket.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				msg = bufferedReader.readLine();
				
				if("end".equals(msg))	{
					socket.close();
					this.dispose();
				}
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if (e.getSource()==adadd) {
			this.remove(panel);
			this.panel = new RootUserAddView(this);
			this.add(panel);
			this.validate();
			
		}else if (e.getSource()==adselect) {
			List<User> list = new ArrayList<User>();
			this.remove(panel);
			panel = new RootUserView(this,list);
			this.add(panel);
			this.validate();
			
		}else if (e.getSource()==databackups) {
			this.remove(panel);
			this.panel = new DataBackupsView();
			this.add(panel);
			this.validate();
			
		}
		
	}
	
}
