package com.pos.view.root;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import org.springframework.context.ApplicationContext;

import com.pos.duitl.Dutil;
import com.pos.mode.User;
import com.pos.server.UserServices;
import com.pos.server.impl.UserServicesImpl;

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
	private static ApplicationContext ct = Dutil.getApplicationContext();
	
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == selbutton)
		{
			System.out.println("asdhkasdads");
			if(jTextField.getText().length()!=0)	{
				UserServices  userServices =(UserServicesImpl)ct.getBean("userServicesImpl");
				List<User> list = userServices.findRootByUsername(jTextField.getText());
				this.list = list;
				this.remove(jsPane);
				this.remove(jPanel);
				init();
				
			}else {
				UserServices userServices =(UserServicesImpl)ct.getBean("userServicesImpl");
				List<User> list = userServices.findAllRoot();
				System.out.println("root"+list.size());
				this.list = list;
				this.remove(jsPane);
				this.remove(jPanel);
				init();
			} 
			
		}else if(e.getSource()==delbutton){
			
			if(userID!=0)
			{
				UserServices  userServices =(UserServicesImpl)ct.getBean("userServicesImpl");
				
				List<User> list = userServices.deleteByUserID(userID);
					
				this.list = list;
				this.remove(jsPane);
				this.remove(jPanel);
				init();
				
			}
			else {
				JOptionPane.showMessageDialog(null, "删除对象为空", "警告",JOptionPane.WARNING_MESSAGE);
			}
			
		}
	}

}
