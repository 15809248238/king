package com.pos.view.root;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.pos.mode.User;
import com.pos.tool.GetSocket;

@SuppressWarnings("serial")
public class RootUserView extends JPanel implements ActionListener{

	public MainPosFrameRoot frame;
	public List<User> list;
	public JPanel jPanel;
	public JTextField jTextField;
	public JButton selbutton,delbutton;
	public JScrollPane jsPane;
	public JTable jTable;
	public int userID;
	
	public RootUserView(MainPosFrameRoot mainPosFrame,List<User> list) {
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

		selbutton.addActionListener(this);
		delbutton.addActionListener(this);

		jPanel.add(jTextField);
		jPanel.add(selbutton);
		jPanel.add(delbutton);

		this.add(jPanel, BorderLayout.NORTH);
		
		jTable = new JTable();
		//表头
		String[] tableHeads = new String[]{"编号","账户","权限"};
		
		DefaultTableModel dtm = (DefaultTableModel)jTable.getModel();
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		jTable.setDefaultRenderer(Object.class, r);
		
		dtm.setColumnIdentifiers(tableHeads);
		for(int i=0;i<list.size();i++)
		{
			Object[] rowData={  list.get(i).getUserID(),
							  	list.get(i).getUsername(),
							  	list.get(i).getType()
			};
			dtm.addRow(rowData);
		}
		
		jsPane = new JScrollPane(jTable);
		jTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = jTable.getSelectedRow();
				userID = (int)dtm.getValueAt(row,0);
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
					String msg	= "user+select+"+jTextField.getText();
					printWriter.println(msg);
					printWriter.flush();
					
					InputStream inputStream = socket.getInputStream();
					ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
					List<User> list = (List<User>) objectInputStream.readObject();
					this.list = list;
					this.remove(jsPane);
					this.remove(jPanel);
					init();
			} catch (Exception e2) {
				
			}
			
		}else if(e.getSource()==delbutton){
			
			if(userID!=0)
			{
				try {
					OutputStream outputStream = socket.getOutputStream();
					PrintWriter printWriter = new PrintWriter(outputStream);
					String msg	= "user+delete+"+userID;
					printWriter.println(msg);
					printWriter.flush();
					
					InputStream inputStream = socket.getInputStream();
					ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
					List<User> list = (List<User>) objectInputStream.readObject();
					
					this.list = list;
					this.remove(jsPane);
					this.remove(jPanel);
					init();
				} catch (Exception e2) {
				
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "删除对象为空", "警告",JOptionPane.WARNING_MESSAGE);
			}
			
		}
	}

}
