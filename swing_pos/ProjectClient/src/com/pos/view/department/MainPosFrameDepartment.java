package com.pos.view.department;

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
import com.pos.mode.Department;
import com.pos.tool.GetSocket;
import com.pos.tool.SingleUser;

@SuppressWarnings("serial")
public class MainPosFrameDepartment extends JFrame implements ActionListener{
	
	public JMenuBar bar;
	public JMenu departmentMenu,personalMenu;
	public JMenuItem dpadd,dpselect,logout,usermessage,updatepassword;
	public JFrame jFrame;
	public JPanel panel = new JPanel();
	
	public MainPosFrameDepartment() {
		init();
		this.setTitle("MainFromDepartment");
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
		updatepassword = new JMenuItem("修改密码");
		usermessage = new JMenuItem(SingleUser.getUser().getUsername());
		personalMenu.add(usermessage);
		personalMenu.add(updatepassword);
		personalMenu.add(logout);
		bar.add(personalMenu);
	
		departmentMenu = new JMenu("部门管理");
		dpadd = new JMenuItem("添加");
		dpselect = new JMenuItem("查询");
		departmentMenu.add(dpadd);
		departmentMenu.add(dpselect);
		bar.add(departmentMenu);
		
		logout.addActionListener(this);
		updatepassword.addActionListener(this);
		
		dpadd.addActionListener(this);
		dpselect.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==dpadd)	{
			
			this.remove(panel);
			this.panel = new DepartAddView(this,new Department());
			this.add(panel);
			this.validate();
			
		}else if (e.getSource()==dpselect) {

			List<Department> list = new ArrayList<Department>();
			this.remove(panel);
			panel = new DepartView(this,list);
			this.add(panel);
			this.validate();
			
		}else if (e.getSource()==updatepassword) {

			new UpdatePasswordView();
			
		}else if (e.getSource()==logout){
			
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
			
		}
	}
	
}
