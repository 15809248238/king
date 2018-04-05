package com.pos.view;

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

import com.pos.mode.Cargo;
import com.pos.mode.Customer;
import com.pos.mode.Department;
import com.pos.mode.Employee;
import com.pos.mode.Inventory;
import com.pos.mode.Purchaseorder;
import com.pos.mode.SaleOrder;
import com.pos.mode.Warehouse;
import com.pos.tool.GetSocket;
import com.pos.tool.SingleUser;

@SuppressWarnings("serial")
public class MainPosFrame extends JFrame implements ActionListener{
	
	public JMenuBar bar;
	public JMenu departmentMenu,employeeMenu,warehouseMenu,
				 customerMenu,cargoMenu,personalMenu,inventoryMenu,salesMenu,purchaseMenu;
	public JMenuItem dpadd,dpselect,emadd,emselect,whadd,whselect,
					 ctadd,ctselect,cgadd,cgselect,inadd,inselect,saleadd,saleselect,
					 phadd,phselect,logout,usermessage;
	public JFrame jFrame;
	public JPanel panel = new JPanel();
	
	public MainPosFrame() {
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
	
		departmentMenu = new JMenu("部门管理");
		dpadd = new JMenuItem("添加");
		dpselect = new JMenuItem("查询");
		departmentMenu.add(dpadd);
		departmentMenu.add(dpselect);
		bar.add(departmentMenu);
		
		employeeMenu = new JMenu("员工管理");
		emadd = new JMenuItem("添加");
		emselect = new JMenuItem("查询");
		employeeMenu.add(emadd);
		employeeMenu.add(emselect);
		bar.add(employeeMenu);
		
		customerMenu = new JMenu("客户管理");
		ctadd = new JMenuItem("添加");
		ctselect = new JMenuItem("查询");
		customerMenu.add(ctadd);
		customerMenu.add(ctselect);
		bar.add(customerMenu);
		
		cargoMenu = new JMenu("货物管理");
		cgadd = new JMenuItem("添加");
		cgselect = new JMenuItem("查询");
		cargoMenu.add(cgadd);
		cargoMenu.add(cgselect);
		bar.add(cargoMenu);
		
		warehouseMenu = new JMenu("仓库管理");
		whadd = new JMenuItem("添加");
		whselect = new JMenuItem("查询");
		warehouseMenu.add(whadd);
		warehouseMenu.add(whselect);
		bar.add(warehouseMenu);
		
		inventoryMenu = new JMenu("库存管理");
		inselect = new JMenuItem("查询");
		inventoryMenu.add(inselect);
		bar.add(inventoryMenu);
		
		salesMenu = new JMenu("销售管理");
		saleadd = new JMenuItem("添加");
		saleselect = new JMenuItem("查询");
		salesMenu.add(saleadd);
		salesMenu.add(saleselect);
		bar.add(salesMenu);
		
		purchaseMenu= new JMenu("采购管理");
		phadd = new JMenuItem("添加");
		phselect = new JMenuItem("查询");
		purchaseMenu.add(phadd);
		purchaseMenu.add(phselect);
		bar.add(purchaseMenu);
		
		logout.addActionListener(this);
		
		dpadd.addActionListener(this);
		dpselect.addActionListener(this);
		
		emadd.addActionListener(this);
		emselect.addActionListener(this);
		
		ctadd.addActionListener(this);
		ctselect.addActionListener(this);
		
		cgadd.addActionListener(this);
		cgselect.addActionListener(this);
		
		whadd.addActionListener(this);
		whselect.addActionListener(this);
		
		inselect.addActionListener(this);
		
		saleadd.addActionListener(this);
		saleselect.addActionListener(this);
		
		phadd.addActionListener(this);
		phselect.addActionListener(this);
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
			
		}else if (e.getSource()==emadd) {
			
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
		
		}else if (e.getSource()==ctadd) {
			
			this.remove(panel);
			this.panel = new CustomerAddView(this,new Customer());
			this.add(panel);
			this.validate();
		
		}else if (e.getSource()==ctselect) {
			
			List<Customer> list = new ArrayList<Customer>();
			this.remove(panel);
			panel = new CustomerView(this,list);
			this.add(panel);
			this.validate();
			
		}else if (e.getSource()==cgadd) {
			
			this.remove(panel);
			this.panel = new CargoAddView(this,new Cargo());
			this.add(panel);
			this.validate();
			
		}else if (e.getSource()==cgselect) {
			
			List<Cargo> list = new ArrayList<Cargo>();
			this.remove(panel);
			panel = new CargoView(this,list);
			this.add(panel);
			this.validate();
			
		}else if (e.getSource()==whadd) {
			
			this.remove(panel);
			this.panel = new WarehouseAddView(this,new Warehouse());
			this.add(panel);
			this.validate();
			
		}else if (e.getSource()==whselect){
			
			List<Warehouse> list = new ArrayList<Warehouse>();
			this.remove(panel);
			panel = new WarehouseView(this,list);
			this.add(panel);
			this.validate();
			
		}else if (e.getSource()==inselect) {
			
			List<Inventory> list = new ArrayList<Inventory>();
			this.remove(panel);
			panel = new InventoryView(this,list);
			this.add(panel);
			this.validate();
			
		}else if (e.getSource()==saleadd) {
			
			this.remove(panel);
			this.panel = new SaleorderAddView(this,new SaleOrder());
			this.add(panel);
			this.validate();
			
		}else if (e.getSource()==saleselect) {
			
			List<SaleOrder> list = new ArrayList<SaleOrder>();
			this.remove(panel);
			panel = new SaleorderView(this,list);
			this.add(panel);
			this.validate();
			
		}else if (e.getSource()==phadd) {
			
			this.remove(panel);
			this.panel = new PurchaseAddView(this,new Purchaseorder());
			this.add(panel);
			this.validate();
			
		}else if (e.getSource()==phselect) {
			
			List<Purchaseorder> list = new ArrayList<Purchaseorder>();
			this.remove(panel);
			panel = new PurchaseView(this,list);
			this.add(panel);
			this.validate();
			
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
