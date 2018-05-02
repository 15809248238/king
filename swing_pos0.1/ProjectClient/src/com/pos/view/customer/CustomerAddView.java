package com.pos.view.customer;

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
import com.pos.mode.Customer;
import com.pos.tool.CityMap;
import com.pos.tool.GetSocket;
import com.pos.tool.SingleUser;

@SuppressWarnings("serial")
public class CustomerAddView extends JPanel implements ActionListener{

	public JButton saveButton;
	public JPanel panel0,panel1,panel2,panel3,panel4,panel5,panel6;
	public JLabel mainLabel,label0,label1,label2,label3,label4;
	public JTextField textField0,textField1,textField2;
	public JRadioButton radioButton0,radioButton1;
	@SuppressWarnings("rawtypes")
	public JComboBox jComboBoxP,jComboBoxC;
	public MainPosFrameCustomer frame;
	public Customer customer;
	
	public CustomerAddView(MainPosFrameCustomer mainPosFrame,Customer customer) {
		this.frame = mainPosFrame;
		this.customer = customer;
		init();
	}

	@SuppressWarnings("unchecked")
	private void init() {
		this.setLayout(null);
		
		panel0 = new JPanel();
		panel0.setBounds(0, 0, 960, 60);
		mainLabel = new JLabel("添加新客户");
		panel0.add(mainLabel);
		
		panel1 = new JPanel();
		panel1.setBounds(0, 60, 960, 60);
		label0 = new JLabel("姓名");
		textField0 = new JTextField(13);
		textField0.setText(customer.getName());
		panel1.add(label0);panel1.add(textField0);
		
		panel2 = new JPanel();
		panel2.setBounds(0, 120, 960, 60);
		label1 = new JLabel("性别        ");
		radioButton0 = new JRadioButton("男       ");
		radioButton1 = new JRadioButton("女       ");
		panel2.add(label1);panel2.add(radioButton0);panel2.add(radioButton1);
		
		panel3 = new JPanel();
		String province=(String)getProvince()[0];
		panel3.setBounds(0, 300, 960, 60);
		label2 = new JLabel("地址       ");
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
		panel3.add(label2);panel3.add(jComboBoxP);panel3.add(jComboBoxC);
		
		panel4 = new JPanel();
		panel4.setBounds(0, 180, 960, 60);
		label3 = new JLabel("手机");
		textField1 = new JTextField(13);
		textField1.setText(customer.getPhone());
		panel4.add(label3);panel4.add(textField1);
		textField1.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				String str = textField1.getText().toString();
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
		
		panel5 = new JPanel();
		panel5.setBounds(0, 240, 960, 60);
		label4 = new JLabel("邮箱");
		textField2 = new JTextField(13);
		textField2.setText(customer.getEmail());
		panel5.add(label4);panel5.add(textField2);
		textField2.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				String str = textField2.getText().toString();
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
				// TODO Auto-generated method stub
			}
		});
		
		panel6 = new JPanel();
		panel6.setBounds(0, 360, 960, 60);
		saveButton = new JButton("save");
		saveButton.addActionListener(this);
		panel6.add(saveButton);
		
		this.add(panel0);
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.add(panel5);
		this.add(panel6);
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
		
		customer.setName(textField0.getText());
		if(radioButton0.isSelected())
		{
			customer.setSex("男");
		}
		else if(radioButton1.isSelected())
		{
			customer.setSex("女");
		}
		String address = (String)jComboBoxP.getSelectedItem()+(String)jComboBoxC.getSelectedItem();
		customer.setAddress(address);
		customer.setPhone(textField1.getText());
		customer.setEmail(textField2.getText());
		
		if(customer.getCustomerID()==0)
		{
			customer.setCreator(SingleUser.getUser().getUsername());
			customer.setCreattime(df.format(new Date()));
			customer.setModifier("null");
			customer.setModifytime("null");
			msg = "customer+save+"+customer.toString();
		}
		else {
			customer.setModifier(SingleUser.getUser().getUsername());
			customer.setModifytime(df.format(new Date()));
			msg = "customer+update+"+customer.toString();
		}
		
		if(!"".equals(customer.getName()))
		{
			try {
				Socket socket = GetSocket.getSocke();
				OutputStream outputStream = socket.getOutputStream();
				PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream,"utf-8"));
				printWriter.println(msg);
				printWriter.flush();
				
				InputStream inputStream = socket.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				List<Customer> list = (List<Customer>) objectInputStream.readObject();
				
				frame.remove(frame.panel);
				frame.panel = new CustomerView(frame,list);
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
