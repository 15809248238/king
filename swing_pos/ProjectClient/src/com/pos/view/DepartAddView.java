package com.pos.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.pos.mode.Department;
import com.pos.tool.GetSocket;
import com.pos.tool.SingleUser;

@SuppressWarnings("serial")
public class DepartAddView extends JPanel implements ActionListener{
	
	public MainPosFrame frame;
	public Department department;
	public JPanel labelPanel,panel0,panel1,panel2,panel3,panel4,panel5;
	public JLabel label1,label2,label3,label4,label5,title;
	public JTextField textField1,textField2,textField3,textField4,textField5;
	public JButton saveButton;
	
	public DepartAddView(MainPosFrame mainPosFrame,Department department)
	{
		this.frame= mainPosFrame;
		this.department = department;
		
		init();
	}

	private void init() {
		
		this.setLayout(null);
		
		labelPanel = new JPanel();
		labelPanel.setBounds(0,0,960,50);
		title = new JLabel("添加新部门");
		labelPanel.add(title);
        
		panel0 = new JPanel();
		panel0.setBounds(0,50,960,80);
		label1 = new JLabel("上级部门");
		textField1 = new JTextField(15);
		textField1.setText(department.getParentdepartname());
		panel0.add(label1);panel0.add(textField1);
		textField1.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!"".equals(textField1.getText().toString()))
				{
					String str = textField1.getText().toString();
					String reg = "^[\\u4e00-\\u9fa5]{0,}$";
					Pattern pattern = Pattern.compile(reg);
					Matcher matcher = pattern.matcher(str);
					boolean rs = matcher.matches();
					if(rs!=true)
					{
						JOptionPane.showMessageDialog(null, "格式错误,输入中文", "警告",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
	
			}
		});
		
		panel1 = new JPanel();
		panel1.setBounds(0,130,960,80);
		label2 = new JLabel("该部门     ");
		textField2 = new JTextField(15);
		textField2.setText(department.getName());
		panel1.add(label2);panel1.add(textField2);
		textField2.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				String str = textField1.getText().toString();
				String reg = "^[\\u4e00-\\u9fa5]{0,}$";
				Pattern pattern = Pattern.compile(reg);
				Matcher matcher = pattern.matcher(str);
				boolean rs = matcher.matches();
				if(rs!=true)
				{
					JOptionPane.showMessageDialog(null, "格式错误,输入中文", "警告",JOptionPane.WARNING_MESSAGE);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {

			}
		});
		
		panel2 = new JPanel();
		panel2.setBounds(0,210,960,80);
		label3 = new JLabel("部门经理");
		textField3 = new JTextField(15);
		textField3.setText(department.getManager());
		panel2.add(label3);panel2.add(textField3);
		
		panel3 = new JPanel();
		panel3.setBounds(0,370,960,80);
		label4 = new JLabel("部门人数");
		textField4 = new JTextField(15);
		textField4.setText(department.getEmployeecount()+"");
		panel3.add(label4);panel3.add(textField4);
		
		panel4 = new JPanel();
		panel4.setBounds(0,290,960,80);
		label5 = new JLabel("部门电话");
		textField5 = new JTextField(15);
		textField5.setText(department.getDepartmentphone());
		panel4.add(label5);panel4.add(textField5);
		textField5.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String str = textField5.getText().toString();
				String reg = "[0][1-9]{2,3}-[0-9]{5,10}$";
				Pattern pattern = Pattern.compile(reg);
				Matcher matcher = pattern.matcher(str);
				boolean rs = matcher.matches();
				if(rs!=true)
				{
					JOptionPane.showMessageDialog(null, "格式错误,例如029-00000", "警告",JOptionPane.WARNING_MESSAGE);
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				
			}
		});
		
		panel5 = new JPanel();
		panel5.setBounds(0,450,960,80);
		saveButton = new JButton("save");
		panel5.add(saveButton);
		saveButton.addActionListener(this);
		
		this.add(labelPanel);
		this.add(panel0);
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.add(panel5);
		
		this.validate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String msg = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		department.setParentdepartname(textField1.getText());
		department.setName(textField2.getText());
		department.setManager(textField3.getText());
		department.setEmployeecount(Integer.parseInt(textField4.getText()));
		department.setDepartmentphone(textField5.getText());
		
		if(department.getDepartmentID()==0)	{
			department.setCreator(SingleUser.getUser().getUsername());
			department.setCreattime(df.format(new Date()));
			department.setModifier("null");
			department.setModifytime("null");
			msg	= "depart+save+"+department.toString();
		}
		else {
			department.setModifier(SingleUser.getUser().getUsername());
			department.setModifytime(df.format(new Date()));
			msg	= "depart+update+"+department.toString();
		}
		
		if(!"".equals(department.getName()))
		{
			try {
				Socket socket = GetSocket.getSocke();
				OutputStream outputStream = socket.getOutputStream();
				PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream,"utf-8"));
				printWriter.println(msg);
				printWriter.flush();
				
				InputStream inputStream = socket.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				List<Department> list = (List<Department>) objectInputStream.readObject();
				
				frame.remove(frame.panel);
				frame.panel = new DepartView(frame,list);
				frame.add(frame.panel);
				frame.validate();
				
			} catch (Exception e2) {
			
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "字段为空", "警告",JOptionPane.WARNING_MESSAGE);
		}
	}
}








