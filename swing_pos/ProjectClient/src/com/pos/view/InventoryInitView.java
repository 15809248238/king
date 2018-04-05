package com.pos.view;

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
import com.pos.mode.Inventory;
import com.pos.tool.GetSocket;
import com.pos.tool.SingleUser;

@SuppressWarnings("serial")
public class InventoryInitView extends JPanel implements ActionListener{
	
	public JButton saveButton;
	public JPanel panel0,panel1,panel2,panel3,panel4;
	public MainPosFrame frame;
	public int warehouseID;
	public JLabel mainLabel,label1,label2,label3;
	public JTextField textField1,textField2;
	public JComboBox<String> jComboBox;
	public String[] temp;
	public Inventory inventory;
	
	public InventoryInitView(MainPosFrame mainPosFrame,int warehouseID,String[] temp,Inventory inventory) {
		
		this.frame = mainPosFrame;
		this.warehouseID = warehouseID;
		this.temp = temp;
		this.inventory = inventory;
		init();
	}

	private void init() {
		this.setLayout(null);
		
		panel0 = new JPanel();
		panel0.setBounds(0, 0, 960, 60);
		mainLabel = new JLabel("初始化库存");
		panel0.add(mainLabel);
		
		panel1 = new JPanel();
		panel1.setBounds(0, 90, 960, 60);
		label1 = new JLabel("仓库编号");
		textField1 = new JTextField(13);
		textField1.setText(warehouseID+"");
		textField1.setEditable(false);
		panel1.add(label1);panel1.add(textField1);
		
		panel2 = new JPanel();
		panel2.setBounds(0, 180, 960, 60);
		label2 = new JLabel("货物编号                                    ");
		jComboBox = new JComboBox<>(temp);
		panel2.add(label2);panel2.add(jComboBox);
		
		panel3 = new JPanel();
		panel3.setBounds(0, 270, 960, 60);
		label3 = new JLabel("库存数目");
		textField2 = new JTextField(13);
		textField2.setText("0");
		panel3.add(label3);panel3.add(textField2);
		
		panel4 = new JPanel();
		panel4.setBounds(0, 360, 960, 60);
		saveButton = new JButton("save");
		saveButton.addActionListener(this);
		panel4.add(saveButton);
		
		this.add(panel0);
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		
		this.validate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		inventory.setWarehouseID(warehouseID);
		inventory.setCargoID(Integer.parseInt(jComboBox.getSelectedItem().toString()));
		inventory.setAmount(Integer.parseInt(textField2.getText()));
		if(inventory.getInventoryID()==0)
		{
			inventory.setCreator(SingleUser.getUser().getUsername());
			inventory.setCreattime(df.format(new Date()));
			inventory.setModifier("null");
			inventory.setModifytime("null");
			msg = "invent+save+"+inventory.toString();
		}
		else {
			inventory.setModifier(SingleUser.getUser().getUsername());
			inventory.setModifytime(df.format(new Date()));
			msg = "invent+update+"+inventory.toString();
		}
		
		try {
			Socket socket = GetSocket.getSocke();
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream,"utf-8"));
			printWriter.println(msg);
			printWriter.flush();
			
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			List<Inventory> list = (List<Inventory>) objectInputStream.readObject();
			frame.remove(frame.panel);
			frame.panel = new InventoryView(frame,list);
			frame.add(frame.panel);
			frame.validate();
			
		} catch (Exception e2) {
			
		}
		
	}

}
