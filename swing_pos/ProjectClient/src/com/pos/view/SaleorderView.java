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
import com.pos.mode.SaleOrder;
import com.pos.tool.GetSocket;

@SuppressWarnings("serial")
public class SaleorderView extends JPanel implements ActionListener{
	
	public MainPosFrame frame;
	public List<SaleOrder> list;
	public JPanel jPanel;
	public JTextField jTextField;
	public JScrollPane jsPane;
	public JButton selbutton,delbutton,upbutton;
	public JTable jTable;
	public SaleOrder saleOrder;
	
	public SaleorderView(MainPosFrame mainPosFrame,List<SaleOrder> list) {
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

		selbutton.addActionListener(this);
		delbutton.addActionListener(this);
		upbutton.addActionListener(this);

		jPanel.add(jTextField);
		jPanel.add(selbutton);
		jPanel.add(upbutton);
		jPanel.add(delbutton);

		this.add(jPanel, BorderLayout.NORTH);
		
		jTable = new JTable();
		//表头
		String[] tableHeads = new String[]{"编号","客户编号","仓库编号","商品编号","数目",
				  "日期","状态"};
		
		DefaultTableModel dtm = (DefaultTableModel)jTable.getModel();
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		jTable.setDefaultRenderer(Object.class, r);
		
		dtm.setColumnIdentifiers(tableHeads);
		for(int i=0;i<list.size();i++)
		{
			Object[] rowData={  list.get(i).getSaleorderID(),
							  	list.get(i).getCustomerID(),
							  	list.get(i).getWarehouseID(),
							  	list.get(i).getCargoID(),
							  	list.get(i).getAmount(),
							  	list.get(i).getDate(),
							  	list.get(i).getStatus()
			};
			dtm.addRow(rowData);
		}
		
		jsPane = new JScrollPane(jTable);
		jTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = jTable.getSelectedRow();
				saleOrder = new SaleOrder();
				saleOrder.setSaleorderID((int)dtm.getValueAt(row,0));
				saleOrder.setCustomerID((int)dtm.getValueAt(row,1));
				saleOrder.setWarehouseID((int)dtm.getValueAt(row,2));
				saleOrder.setCargoID((int)dtm.getValueAt(row,3));
				saleOrder.setAmount((int)dtm.getValueAt(row,4));
				saleOrder.setDate((String)dtm.getValueAt(row,5));
				saleOrder.setStatus((String)dtm.getValueAt(row,6));
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
					String msg	= "sale+select+"+jTextField.getText();
					printWriter.println(msg);
					printWriter.flush();
					
					InputStream inputStream = socket.getInputStream();
					ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
					List<SaleOrder> list = (List<SaleOrder>) objectInputStream.readObject();
					
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
				String msg	= "sale+delete+"+saleOrder.toString();
				printWriter.println(msg);
				printWriter.flush();
				
				InputStream inputStream = socket.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				List<SaleOrder> list = (List<SaleOrder>) objectInputStream.readObject();
				
				this.list = list;
				this.remove(jsPane);
				this.remove(jPanel);
				init();
			} catch (Exception e2) {
			
		 }
			
		}else if(e.getSource()==upbutton) {
			
			frame.remove(frame.panel);
			frame.panel = new SaleorderAddView(frame, saleOrder);
			frame.add(frame.panel);
			frame.validate();
		}
	}
	
}
