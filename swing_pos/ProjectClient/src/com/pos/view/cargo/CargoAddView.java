package com.pos.view.cargo;

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
import com.pos.mode.Cargo;
import com.pos.tool.GetSocket;
import com.pos.tool.SingleUser;

@SuppressWarnings("serial")
public class CargoAddView extends JPanel implements ActionListener{

	public MainPosFrameCargo frame;
	public Cargo cargo;
	public String[] cargoType = {"主机","显示器","键盘","鼠标"};
	public JPanel panel0,panel1,panel2,panel3,panel4,panel5;
	public JLabel mainLabel,label1,label2,label3,label4;
	public JTextField textField1,textField2,textField3;
	public JButton saveButton;
	public JComboBox<String> jComboBox;
	
	public CargoAddView(MainPosFrameCargo mainPosFrame,Cargo cargo) {
		this.cargo = cargo;
		this.frame = mainPosFrame;
		init();
	}

	private void init() {
		this.setLayout(null);
		
		panel0 = new JPanel();
		panel0.setBounds(0, 0, 960, 50);
		mainLabel = new JLabel("添加新货物");
		panel0.add(mainLabel);
		
		panel1 = new JPanel();
		panel1.setBounds(0, 80, 960, 50);
		label1 = new JLabel("货物名称");
		textField1 = new JTextField(13);
		textField1.setText(cargo.getCargoname());
		panel1.add(label1);panel1.add(textField1);
		
		panel2 = new JPanel();
		panel2.setBounds(0, 160, 960, 50);
		label2 = new JLabel("货物种类                         ");
		jComboBox = new JComboBox<String>(cargoType);
		panel2.add(label2);panel2.add(jComboBox);
		
		panel3 = new JPanel();
		panel3.setBounds(0, 240, 960, 50);
		label3 = new JLabel("货物进价");
		textField2 = new JTextField(13);
		textField2.setText(cargo.getInprice()+"");
		panel3.add(label3);panel3.add(textField2);
		
		panel4 = new JPanel();
		panel4.setBounds(0, 320, 960, 50);
		label4 = new JLabel("货物售价");
		textField3 = new JTextField(13);
		textField3.setText(cargo.getOutprice()+"");
		panel4.add(label4);panel4.add(textField3);
		
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
		
		cargo.setCargoname(textField1.getText());
		cargo.setCargotypename((String)jComboBox.getSelectedItem());
		cargo.setInprice(Integer.parseInt(textField2.getText()));
		cargo.setOutprice(Integer.parseInt(textField3.getText()));
		
		if(cargo.getCargoID()==0)
		{
			cargo.setCreator(SingleUser.getUser().getUsername());
			cargo.setCreattime(df.format(new Date()));
			cargo.setModifier("null");
			cargo.setModifytime("null");
			msg = "cargo+save+"+cargo.toString();
		}
		else {
			cargo.setModifier(SingleUser.getUser().getUsername());
			cargo.setModifytime(df.format(new Date()));
			msg = "cargo+update+"+cargo.toString();
		}
		if(!"".equals(cargo.getCargoname()))	{
			try {
				Socket socket = GetSocket.getSocke();
				OutputStream outputStream = socket.getOutputStream();
				PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream,"utf-8"));
				printWriter.println(msg);
				printWriter.flush();
			
				InputStream inputStream = socket.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				List<Cargo> list = (List<Cargo>) objectInputStream.readObject();
				frame.remove(frame.panel);
				frame.panel = new CargoView(frame,list);
				frame.add(frame.panel);
				frame.validate();
			
			} catch (Exception e2) {
			
			}
		}
	}
	
}
