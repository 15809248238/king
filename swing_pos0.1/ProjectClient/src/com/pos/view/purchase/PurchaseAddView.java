package com.pos.view.purchase;

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
import com.pos.mode.Purchaseorder;
import com.pos.tool.GetSocket;

@SuppressWarnings("serial")
public class PurchaseAddView extends JPanel implements ActionListener{

	public MainPosFramePurchase frame;
	public String[] strings = {"uncommitted","commited"};
	public Purchaseorder purchaseorder;
	public JButton saveButton;
	public JPanel panel1,panel2,panel3,panel4,panel5,panel6,panel7;
	public JTextField textField1,textField2,textField3,textField4;
	public JLabel mainLabel,label1,label2,label3,label4,label5;
	public JComboBox<String> status;
	
	public PurchaseAddView(MainPosFramePurchase mainPosFrame,Purchaseorder purchaseorder) {
		this.frame = mainPosFrame;
		this.purchaseorder = purchaseorder;
		init();
	}

	private void init() {
		this.setLayout(null);
		
		panel1 = new JPanel();
		panel1.setBounds(0, 0, 960, 50);
		mainLabel = new JLabel("添加采购单");
		panel1.add(mainLabel);
		
		panel2 = new JPanel();
		panel2.setBounds(0, 80, 960, 50);
		label1 = new JLabel("客户编号");
		textField1 = new JTextField(13);
		textField1.setText(purchaseorder.getCustomerID()+"");
		if(purchaseorder.getPurchaseorderID()!=0)
		{
			textField1.setEditable(false);
		}
		panel2.add(label1);panel2.add(textField1);
		
		panel3 = new JPanel();
		panel3.setBounds(0, 160, 960, 50);
		label2 = new JLabel("仓库编号");
		textField2 = new JTextField(13);
		textField2.setText(purchaseorder.getWarehouseID()+"");
		if(purchaseorder.getPurchaseorderID()!=0)
		{
			textField2.setEditable(false);
		}
		panel3.add(label2);panel3.add(textField2);
		
		panel4 = new JPanel();
		panel4.setBounds(0, 240, 960, 50);
		label3 = new JLabel("商品编号");
		textField3 = new JTextField(13);
		textField3.setText(purchaseorder.getCargoID()+"");
		if(purchaseorder.getPurchaseorderID()!=0)
		{
			textField3.setEditable(false);
		}
		panel4.add(label3);panel4.add(textField3);
		
		panel5 = new JPanel();
		panel5.setBounds(0, 320, 960, 50);
		label4 = new JLabel("商品数量");
		textField4 = new JTextField(13);
		textField4.setText(purchaseorder.getAmount()+"");
		if(purchaseorder.getPurchaseorderID()!=0)
		{
			textField4.setEditable(false);
		}
		panel5.add(label4);panel5.add(textField4);
		
		panel6 = new JPanel();
		panel6.setBounds(0, 400, 960, 50);
		label5 = new JLabel("订单状态              ");
		status = new JComboBox<>(strings);
		panel6.add(label5);panel6.add(status);
		
		panel7 = new JPanel();
		panel7.setBounds(0, 480, 960, 50);
		saveButton = new JButton("save");
		panel7.add(saveButton);
		saveButton.addActionListener(this);
		
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.add(panel5);
		this.add(panel6);
		this.add(panel7);
		
		this.validate();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		purchaseorder.setCustomerID(Integer.parseInt(textField1.getText()));
		purchaseorder.setWarehouseID(Integer.parseInt(textField2.getText()));
		purchaseorder.setCargoID(Integer.parseInt(textField3.getText()));
		purchaseorder.setAmount(Integer.parseInt(textField4.getText()));
		purchaseorder.setDate(df.format(new Date()));
		if(purchaseorder.getPurchaseorderID()==0)
		{
			purchaseorder.setStatus(strings[0]);
			msg = "purchase+save+"+purchaseorder.toString();
		}
		else {
			purchaseorder.setStatus((String) status.getSelectedItem());
			msg = "purchase+update+"+purchaseorder.toString();
		}
		
		try {
			Socket socket = GetSocket.getSocke();
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream,"utf-8"));
			printWriter.println(msg);
			printWriter.flush();
			
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			List<Purchaseorder> list = (List<Purchaseorder>) objectInputStream.readObject();
			
			frame.remove(frame.panel);
			frame.panel = new PurchaseView(frame,list);
			frame.add(frame.panel);
			frame.validate();
			
		} catch (Exception e2) {
			
		}
		
	}
}
