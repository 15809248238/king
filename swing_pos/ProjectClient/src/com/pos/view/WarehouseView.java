package com.pos.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.pos.mode.Cargo;
import com.pos.mode.Inventory;
import com.pos.mode.Warehouse;
import com.pos.tool.GetSocket;

@SuppressWarnings("serial")
public class WarehouseView extends JPanel implements ActionListener{

	public MainPosFrame frame;
	public List<Warehouse> list;
	public Warehouse warehouse;
	public JPanel jPanel;
	public JTextField jTextField;
	public JScrollPane jsPane;
	public JButton selbutton,delbutton,upbutton,initButton;
	public JTable jTable;
	
	public WarehouseView(MainPosFrame mainPosFrame,List<Warehouse> list) {
		this.frame = mainPosFrame;
		this.list = list;
		init();
	}

	private void init() {
		this.setLayout(new BorderLayout());
		// 搜索区
		jPanel = new JPanel();

		jTextField = new JTextField(20);
		selbutton = new JButton("搜索 ");
		delbutton = new JButton("删除");
		upbutton = new JButton("更新");
		initButton = new JButton("初始化库存");
		
		selbutton.addActionListener(this);
		delbutton.addActionListener(this);
		upbutton.addActionListener(this);
		initButton.addActionListener(this);
		
		jPanel.add(jTextField);
		jPanel.add(selbutton);
		jPanel.add(upbutton);
		jPanel.add(delbutton);
		jPanel.add(initButton);
		
		this.add(jPanel, BorderLayout.NORTH);
		
		jTable = new JTable();
		//表头
		String[] tableHeads = new String[]{"编号","仓库名称","仓库经理","地址","状态",
				  "创建人","创建时间","修改人","修改时间"};
		
		DefaultTableModel dtm = (DefaultTableModel)jTable.getModel();
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		jTable.setDefaultRenderer(Object.class, r);
		
		dtm.setColumnIdentifiers(tableHeads);
		
		for(int i=0;i<list.size();i++)
		{
			Object[] rowData={  list.get(i).getWarehouseID(),
							  	list.get(i).getName(),
							  	list.get(i).getManager(),
							  	list.get(i).getAddress(),
							  	list.get(i).getStatus(),
							  	list.get(i).getCreator(),
							  	list.get(i).getCreattime(),
							  	list.get(i).getModifier(),
							  	list.get(i).getModifytime()
			};
			dtm.addRow(rowData);
		}
		
		jsPane = new JScrollPane(jTable);
		
		jTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = jTable.getSelectedRow();
				warehouse = new Warehouse();
				warehouse.setWarehouseID((int)dtm.getValueAt(row,0));
				warehouse.setName((String)dtm.getValueAt(row,1));
				warehouse.setManager((String)dtm.getValueAt(row,2));
				warehouse.setAddress((String)dtm.getValueAt(row,3));
				warehouse.setStatus((String)dtm.getValueAt(row,4));
				warehouse.setCreator((String)dtm.getValueAt(row,5));
				warehouse.setCreattime((String)dtm.getValueAt(row,6));
				warehouse.setModifier((String)dtm.getValueAt(row,7));
				warehouse.setModifytime((String)dtm.getValueAt(row,8));
			}
		});
		
		this.add(jsPane,BorderLayout.CENTER);
		//重新加载容器内的组件
		this.validate();
		this.setVisible(true);
	    this.setSize(950, 600);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		Socket socket = GetSocket.getSocke();
		if(e.getSource() == selbutton)
		{
			try {
					OutputStream outputStream = socket.getOutputStream();
					PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream,"utf-8"));
					String msg	= "ware+select+"+jTextField.getText();
					printWriter.println(msg);
					printWriter.flush();
					
					InputStream inputStream = socket.getInputStream();
					ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
					List<Warehouse> list = (List<Warehouse>) objectInputStream.readObject();
					
					this.list = list;
					this.remove(jsPane);
					this.remove(jPanel);
					init();
			} catch (Exception e2) {
				
			}
			
		}else if(e.getSource()==delbutton){
			
			try {
				OutputStream outputStream = socket.getOutputStream();
				PrintWriter printWriter = new PrintWriter(outputStream);
				String msg	= "ware+delete+"+warehouse.getWarehouseID();
				printWriter.println(msg);
				printWriter.flush();
				
				InputStream inputStream = socket.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				List<Warehouse> list = (List<Warehouse>) objectInputStream.readObject();
				
				this.list = list;
				this.remove(jsPane);
				this.remove(jPanel);
				init();
			} catch (Exception e2) {
			
		 }
			
		}else if(e.getSource()==upbutton) {
			
			frame.remove(frame.panel);
			frame.panel = new WarehouseAddView(frame, warehouse);
			frame.add(frame.panel);
			frame.validate();
		
		}else if (e.getSource()==initButton) {
			try {
				OutputStream outputStream = socket.getOutputStream();
				PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream,"utf-8"));
				String msg	= "cargo+select+"+jTextField.getText();
				printWriter.println(msg);
				printWriter.flush();
				
				InputStream inputStream = socket.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				List<Cargo> list = (List<Cargo>) objectInputStream.readObject();
				String[] temp = new String[list.size()];
				for(int i=0;i<list.size();i++)
				{
					temp[i] = list.get(i).getCargoID()+"";
				}
				int warehouseID = warehouse.getWarehouseID();
				
				frame.remove(frame.panel);
				frame.panel = new InventoryInitView(frame,warehouseID, temp,new Inventory());
				frame.add(frame.panel);
				frame.validate();
				
		} catch (Exception e2) {
			
		}
			
		}
	}
	
}
