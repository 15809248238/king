package com.pos.view.warehouse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.pos.mode.Warehouse;
import com.pos.tool.GetSocket;
import com.pos.tool.SingleUser;

@SuppressWarnings("serial")
public class WarehouseAddView extends JPanel implements ActionListener{

	public MainPosFrameWarehouse frame;
	public String[] status = {"未使用","使用中"};
	public Warehouse warehouse;
	public JPanel panel0,panel1,panel2,panel3,panel4,panel5;
	public JLabel mainLabel,label1,label2,label3,label4;
	public JButton saveButton;
	public JTextField textField1,textField2,textField3;
	public JComboBox<String> jComboBox;
	
	public WarehouseAddView(MainPosFrameWarehouse mainPosFrame,Warehouse warehouse) {
		this.warehouse = warehouse;
		this.frame = mainPosFrame;
		init();
	}

	private void init() {
		this.setLayout(null);
		
		panel0 = new JPanel();
		panel0.setBounds(0, 0, 960, 50);
		mainLabel = new JLabel("添加新仓库");
		panel0.add(mainLabel);
		
		panel1 = new JPanel();
		panel1.setBounds(0, 80, 960, 50);
		label1 = new JLabel("仓库名称");
		textField1 = new JTextField(13);
		textField1.setText(warehouse.getName());
		panel1.add(label1);panel1.add(textField1);
		
		panel2 = new JPanel();
		panel2.setBounds(0, 160, 960, 50);
		label2 = new JLabel("仓库经理");
		textField2 = new JTextField(13);
		textField2.setText(warehouse.getManager());
		panel2.add(label2);panel2.add(textField2);
		
		panel3 = new JPanel();
		panel3.setBounds(0, 240, 960, 50);
		label3 = new JLabel("仓库地址");
		textField3 = new JTextField(13);
		textField3.setText(warehouse.getAddress());
		panel3.add(label3);panel3.add(textField3);
		
		panel4 = new JPanel();
		panel4.setBounds(0, 320, 960, 50);
		label4 = new JLabel("仓库状态                      ");
		jComboBox = new JComboBox<>(status);
		panel4.add(label4);panel4.add(jComboBox);
		
		panel5 = new JPanel();
		panel5.setBounds(0, 400, 960, 50);
		saveButton = new JButton("save");
		saveButton.addActionListener(this);
		panel5.add(saveButton);
		
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
		
		warehouse.setName(textField1.getText());
		warehouse.setManager(textField2.getText());
		warehouse.setAddress(textField3.getText());
		warehouse.setStatus((String)jComboBox.getSelectedItem());
		
		if(warehouse.getWarehouseID()==0)
		{
			warehouse.setCreator(SingleUser.getUser().getUsername());
			warehouse.setCreattime(df.format(new Date()));
			warehouse.setModifier("null");
			warehouse.setModifytime("null");
			msg = "ware+save+"+warehouse.toString();
		}
		else {
			warehouse.setModifier(SingleUser.getUser().getUsername());
			warehouse.setModifytime(df.format(new Date()));
			msg = "ware+update+"+warehouse.toString();
		}
		
		try {
			Socket socket = GetSocket.getSocke();
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream,"utf-8"));
			printWriter.println(msg);
			printWriter.flush();
			
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			List<Warehouse> list = (List<Warehouse>) objectInputStream.readObject();
			
			frame.remove(frame.panel);
			frame.panel = new WarehouseView(frame,list);
			frame.add(frame.panel);
			frame.validate();
			
		} catch (Exception e2) {
			
		}
	}
	
}
