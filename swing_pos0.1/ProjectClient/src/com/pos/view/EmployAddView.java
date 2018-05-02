package com.pos.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import com.pos.mode.Employee;
import com.pos.tool.CityMap;
import com.pos.tool.GetSocket;
import com.pos.tool.SingleUser;

@SuppressWarnings("serial")
public class EmployAddView extends JPanel implements ActionListener{
	
	private String[] string = {"总经理","仓库管理员","销售管理员","采购管理员","人事管理员","客户管理员","货物管理员","部门管理员"};
	public MainPosFrame frame;
	public Employee employee;
	public JButton saveButton;
	public JPanel panel0,panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8,panel9;
	public JLabel mainlabel,label1,label2,label3,label4,label5,label6,label7,label8;
	public JTextField textField1,textField2,textField3,textField4,textField5,textField6;
	@SuppressWarnings("rawtypes")
	public JComboBox jComboBox,jComboBoxP,jComboBoxC;;
	public JRadioButton radioButton1,radioButton2;
	
	public EmployAddView(MainPosFrame mainPosFrame,Employee employee)
	{
		this.frame = mainPosFrame;
		this.employee = employee;
		init();
	}

	@SuppressWarnings("unchecked")
	private void init() {
		this.setLayout(null);
		
		panel0 = new JPanel();
		panel0.setBounds(0,0,960,50);
		mainlabel = new JLabel("添加新员工");
		panel0.add(mainlabel);
		
		panel1 = new JPanel();
		panel1.setBounds(0,50,960,50);
		label1 = new JLabel("部门");
		textField1 = new JTextField(15);
		textField1.setText(employee.getDepartmentName());
		if(employee.getEmployeeID()!=0)
		{
			textField1.setEditable(false);
		}
		panel1.add(label1);
		panel1.add(textField1);
		
		panel2 = new JPanel();
		panel2.setBounds(0,100,960,50);
		label2 = new JLabel("姓名");
		textField2 = new JTextField(15);
		textField2.setText(employee.getEmployeeName());
		panel2.add(label2);
		panel2.add(textField2);
		
		panel3 = new JPanel();
		panel3.setBounds(0,150,960,50);
		label3 = new JLabel("性别      ");
		radioButton1 = new JRadioButton("男           ");
		radioButton2 = new JRadioButton("女           ");
		panel3.add(label3);
		panel3.add(radioButton1);
		panel3.add(radioButton2);
		
		panel4 = new JPanel();
		panel4.setBounds(0,400,960,50);
		label4 = new JLabel("地址       ");
		String province=(String)getProvince()[0];
		jComboBoxP = new JComboBox<>();
		jComboBoxC = new JComboBox<>();
		jComboBoxP.setModel(new DefaultComboBoxModel<>(getProvince()));
		jComboBoxC.setModel(new DefaultComboBoxModel<>(getCity(province)));
		jComboBoxP.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				itemChange();
			}
        });
		panel4.add(label4);
		panel4.add(jComboBoxP);
		panel4.add(jComboBoxC);
		
		panel5 = new JPanel();
		panel5.setBounds(0,250,960,50);
		label5 = new JLabel("手机");
		textField4 = new JTextField(15);
		textField4.setText(employee.getPhone());
		if(employee.getEmployeeID()!=0)
		{
			textField4.setEditable(false);
		}
		panel5.add(label5);
		panel5.add(textField4);
		textField4.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				String str = textField4.getText().toString();
				String reg = "1[3|4|5|8][0-9]\\d{8}$";
				Pattern pattern = Pattern.compile(reg);
				Matcher matcher = pattern.matcher(str);
				boolean rs = matcher.matches();
				if(rs!=true)
				{
					JOptionPane.showMessageDialog(null, "格式错误", "警告",JOptionPane.WARNING_MESSAGE);
				}	
			}
			
			@Override
			public void focusGained(FocusEvent e) {

			}
		});
		
		
		panel6 = new JPanel();
		panel6.setBounds(0,300,960,50);
		label6 = new JLabel("员工类型            ");
		jComboBox = new JComboBox<>(string);
		panel6.add(label6);
		panel6.add(jComboBox);
		
		panel7 = new JPanel();
		panel7.setBounds(0,350,960,50);
		label7 = new JLabel("邮箱");
		textField5 = new JTextField(15);
		textField5.setText(employee.getEmail());
		panel7.add(label7);
		panel7.add(textField5);
		textField5.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				String str = textField5.getText().toString();
				
				String reg = "[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
				Pattern pattern = Pattern.compile(reg);
				Matcher matcher = pattern.matcher(str);
				boolean rs = matcher.matches();
				if(rs!=true)
				{
					JOptionPane.showMessageDialog(null, "格式错误", "警告",JOptionPane.WARNING_MESSAGE);
				}	
			}
			
			@Override
			public void focusGained(FocusEvent e) {

			}
		});
		
		panel8 = new JPanel();
		panel8.setBounds(0,200,960,50);
		label8 = new JLabel("生日");
		textField6 = new JTextField(15);
		textField6.setText(employee.getBirthday());
		panel8.add(label8);
		panel8.add(textField6);
		textField6.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				String str = textField6.getText().toString();
				
				String reg = "\\d{4}(\\-)\\d{1,2}\\1\\d{1,2}$";
				Pattern pattern = Pattern.compile(reg);
				Matcher matcher = pattern.matcher(str);
				boolean rs = matcher.matches();
				if(rs!=true)
				{
					JOptionPane.showMessageDialog(null, "格式错误例如：1990-01-01", "警告",JOptionPane.WARNING_MESSAGE);
				}	
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				
			}
		});
		
		
		panel9 = new JPanel();
		panel9.setBounds(0,450,960,50);
		saveButton = new JButton("save");
		saveButton.addActionListener(this);
		panel9.add(saveButton);
		
		this.add(panel0);
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.add(panel5);
		this.add(panel6);
		this.add(panel7);
		this.add(panel8);
		this.add(panel9);
		
		this.validate();
	}
	
	public Object[] getProvince() {
        Map<String, String[]> map = CityMap.model;// 获取省份信息保存到Map中
        Set<String> set = map.keySet(); // 获取Map集合中的键，并以Set集合返回
        Object[] province = set.toArray(); // 转换为数组
        return province; // 返回获取的省份信息
    }

   
    public String[] getCity(String selectProvince) {
        Map<String, String[]> map = CityMap.model; // 获取省份信息保存到Map中
        String[] arrCity = map.get(selectProvince); // 获取指定键的值
        return arrCity; // 返回获取的市/县
    }

    @SuppressWarnings({ "unchecked", "rawtypes"})
	private void itemChange() {
        String selectProvince = (String) jComboBoxP.getSelectedItem();
        jComboBoxC.removeAllItems(); // 清空市/县列表
        String[] arrCity = getCity(selectProvince); // 获取市/县
        jComboBoxC.setModel(new DefaultComboBoxModel(arrCity)); // 重新添加市/县列表的值
    }
	

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		employee.setDepartmentName(textField1.getText());
		employee.setEmployeeName(textField2.getText());
		if(radioButton1.isSelected())
		{
			employee.setSex("男");
		}else if(radioButton2.isSelected())
		{
			employee.setSex("女");
		}
		employee.setAddress((String)jComboBoxP.getSelectedItem()+(String)jComboBoxC.getSelectedItem());
		employee.setPhone(textField4.getText());
		employee.setType((String)jComboBox.getSelectedItem());
		employee.setEmail(textField5.getText());
		employee.setBirthday(textField6.getText());
		
		if(employee.getEmployeeID()==0)
		{
			employee.setCreator(SingleUser.getUser().getUsername());
			employee.setCreattime(df.format(new Date()));
			employee.setModifier("null");
			employee.setModifytime("null");
			msg = "employee+save+"+employee.toString();
		}
		else {
			employee.setModifier(SingleUser.getUser().getUsername());
			employee.setModifytime(df.format(new Date()));
			msg = "employee+update+"+employee.toString();
		}
		if(!"".equals(employee.getEmployeeName())&&!"".equals(employee.getDepartmentName())) {
			try {
				Socket socket = GetSocket.getSocke();
				OutputStream outputStream = socket.getOutputStream();
				PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream,"utf-8"));
				printWriter.println(msg);
				printWriter.flush();
				
				InputStream inputStream = socket.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				List<Employee> list = (List<Employee>) objectInputStream.readObject();
				frame.remove(frame.panel);
				frame.panel = new EmployeeView(frame,list);
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
