package com.pos.view.root;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import com.pos.mode.User;

@SuppressWarnings("serial")
public class MainPosFrameRoot extends JFrame implements ActionListener{
	
	public JMenuBar bar;
	public JMenu personalMenu,adminMenu,dataMenu;
	public JMenuItem logout,usermessage,updatepassword,adselect,adadd,databackups;
	public JFrame jFrame;
	public JPanel panel = new JPanel();
	public String temp = null;
	public User user;
	public MainPosFrameRoot(User user,String temp) {
		this.user = user;
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
		updatepassword = new JMenuItem("修改密码");
		logout = new JMenuItem("注销");
		usermessage = new JMenuItem(user.getUsername());
		personalMenu.add(usermessage);
		personalMenu.add(updatepassword);
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
		
		updatepassword.addActionListener(this);
		
		databackups.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==adadd) {
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
		}else if (e.getSource()==updatepassword) {
			new UpdatePasswordView();	
		}
		
	}
	
}
