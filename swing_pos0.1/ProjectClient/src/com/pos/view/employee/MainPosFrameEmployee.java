package com.pos.view.employee;

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
import com.pos.mode.Employee;
import com.pos.tool.GetSocket;
import com.pos.tool.SingleUser;
import com.pos.view.employee.EmployAddView;
import com.pos.view.employee.EmployeeView;

@SuppressWarnings("serial")
public class MainPosFrameEmployee extends JFrame implements ActionListener{
	
	public JMenuBar bar;
	public JMenu employeeMenu,personalMenu,departmentMenu;
	public JMenuItem emadd,emselect,logout,usermessage,dpselect;
	public JFrame jFrame;
	public JPanel panel = new JPanel();
	
	public MainPosFrameEmployee() {
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
		usermessage = new JMenuItem(SingleUser.getUser().getUsername());
		personalMenu.add(usermessage);
		personalMenu.add(logout);
		bar.add(personalMenu);
	
		departmentMenu = new JMenu("部门管理");
		dpselect = new JMenuItem("查询");
		departmentMenu.add(dpselect);
		bar.add(departmentMenu);
		
		employeeMenu = new JMenu("员工管理");
		emadd = new JMenuItem("添加");
		emselect = new JMenuItem("查询");
		employeeMenu.add(emadd);
		employeeMenu.add(emselect);
		bar.add(employeeMenu);
		
		logout.addActionListener(this);
		
		dpselect.addActionListener(this);
		
		emadd.addActionListener(this);
		emselect.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==emadd) {
			
			this.remove(panel);
			this.panel = new EmployAddView(this,new Employee());
			this.add(panel);
			this.validate();
		
		}else if (e.getSource()==emselect) {
			
			List<Employee> list = new ArrayList<Employee>();
			this.remove(panel);
			panel = new EmployeeView(this,list);
			this.add(panel);
			this.validate();
		
		}
		else if (e.getSource()==dpselect) {
			
			List<Department> list = new ArrayList<Department>();
			this.remove(panel);
			panel = new DepartView(this,list);
			this.add(panel);
			this.validate();
			
		}
		else if (e.getSource()==logout){
			
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
