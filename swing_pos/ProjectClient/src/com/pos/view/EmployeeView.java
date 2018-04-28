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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.pos.mode.Employee;
import com.pos.tool.GetSocket;

@SuppressWarnings("serial")
public class EmployeeView extends JPanel implements ActionListener{

	public MainPosFrame frame;
	public List<Employee> list;
	public JPanel jPanel;
	public JTextField jTextField;
	public JScrollPane jsPane;
	public JButton selbutton,delbutton,upbutton;
	public JTable jTable;
	public Employee employee = new Employee();
	
	public EmployeeView(MainPosFrame mainPosFrame,List<Employee> list)
	{
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
		
		// 表头
		String[] tableHeads = new String[] {"编号","部门名称","姓名","性别",
				"地址","手机","员工类型","邮箱","生日","创建人","创建时间","修改人","修改时间"};
		
		DefaultTableModel dtm = (DefaultTableModel)jTable.getModel();
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		jTable.setDefaultRenderer(Object.class, r);
		
		dtm.setColumnIdentifiers(tableHeads);
		
		for(int i=0;i<list.size();i++)
		{
			Object[] rowData={  list.get(i).getEmployeeID(),
							  	list.get(i).getDepartmentName(),
							  	list.get(i).getEmployeeName(),
							  	list.get(i).getSex(),
							  	list.get(i).getAddress(),
							  	list.get(i).getPhone(),
							  	list.get(i).getType(),
							  	list.get(i).getEmail(),
							  	list.get(i).getBirthday(),
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
				employee.setEmployeeID((int)dtm.getValueAt(row,0));
				employee.setDepartmentName((String)dtm.getValueAt(row,1));
				employee.setEmployeeName((String)dtm.getValueAt(row,2));
				employee.setSex((String)dtm.getValueAt(row,3));
				employee.setAddress((String)dtm.getValueAt(row,4));
				employee.setPhone((String)dtm.getValueAt(row,5));
				employee.setType((String)dtm.getValueAt(row,6));
				employee.setEmail((String)dtm.getValueAt(row,7));
				employee.setBirthday((String)dtm.getValueAt(row,8));
				employee.setCreator((String)dtm.getValueAt(row,9));
				employee.setCreattime((String)dtm.getValueAt(row,10));
				employee.setModifier((String)dtm.getValueAt(row,11));
				employee.setModifytime((String)dtm.getValueAt(row,12));
			}
		});
		
		this.add(jsPane,BorderLayout.CENTER);
		//重新加载容器内的组件
		this.validate();
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
					String msg	= "employee+select+"+jTextField.getText();
					printWriter.println(msg);
					printWriter.flush();
					
					InputStream inputStream = socket.getInputStream();
					ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
					
					List<Employee> list = (List<Employee>) objectInputStream.readObject();
					this.list = list;
					this.remove(jsPane);
					this.remove(jPanel);
					init();
			
			} catch (Exception e2) {
				
			}
			
		}else if(e.getSource()==delbutton){
			
			if(employee.getEmployeeID()!=0)
			{
				try {
					OutputStream outputStream = socket.getOutputStream();
					PrintWriter printWriter = new PrintWriter(outputStream);
					String msg	= "employee+delete+"+employee.getEmployeeID()+"+"+employee.getDepartmentName()+"+"+employee.getPhone();
					printWriter.println(msg);
					printWriter.flush();
					
					InputStream inputStream = socket.getInputStream();
					ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
					
					List<Employee> list = (List<Employee>) objectInputStream.readObject();
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
			
		}else if(e.getSource()==upbutton) {
			if (employee.getEmployeeID()!=0) {
				frame.remove(frame.panel);
				frame.panel = new EmployAddView(frame, employee);
				frame.add(frame.panel);
				frame.validate();
			} else {
				JOptionPane.showMessageDialog(null, "修改对象为空", "警告",JOptionPane.WARNING_MESSAGE);
			}
		}
	}

}
