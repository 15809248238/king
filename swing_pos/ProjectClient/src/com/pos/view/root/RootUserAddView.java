package com.pos.view.root;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.pos.mode.User;
import com.pos.tool.GetSocket;

@SuppressWarnings("serial")
public class RootUserAddView extends JPanel implements ActionListener{

	public MainPosFrameRoot frame;
	public JPanel panel1,panel2,panel3,panel4,panel5;
	public JButton saveButton;
	public JLabel label1,label2,label3,label4;
	public JTextField textField1,textField2;
	public JComboBox<String> jComboBox;
	public User user;
	
	public RootUserAddView(MainPosFrameRoot mainPosFrame) {
		this.frame = mainPosFrame;
		init();
	}
	
	private void init() {
		this.setLayout(null);
		
		panel1 = new JPanel();
		panel1.setBounds(0, 0, 960, 50);
		label1 = new JLabel("添加管理员");
		panel1.add(label1);
		
		panel2 = new JPanel();
		panel2.setBounds(0, 80, 960, 50);
		label2 = new JLabel("用户名");
		textField1 = new JTextField(13);
		panel2.add(label2);panel2.add(textField1);
		
		panel3 = new JPanel();
		panel3.setBounds(0, 160, 960, 50);
		label3 = new JLabel("密码");
		textField2 = new JTextField(13);
		panel3.add(label3);panel3.add(textField2);
		
		panel4 = new JPanel();
		panel4.setBounds(0, 240, 960, 50);
		label4 = new JLabel("权限");
		String[] string = {"root","superroot"};
		jComboBox = new JComboBox<>(string);
		panel4.add(label4);panel4.add(jComboBox);
		
		panel5 = new JPanel();
		panel5.setBounds(0, 320, 960, 50);
		saveButton = new JButton("save");
		panel5.add(saveButton);
		saveButton.addActionListener(this);
		
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.add(panel5);
		
		this.validate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		user = new User();
		user.setUsername(textField1.getText());
		user.setPassword(textField2.getText());
		user.setType(jComboBox.getSelectedItem().toString());
		String msg = "user+save+"+user.toString();
		try {
			Socket socket = GetSocket.getSocke();
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream,"utf-8"));
			printWriter.println(msg);
			printWriter.flush();
			
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			List<User> list = (List<User>) objectInputStream.readObject();
			
			frame.remove(frame.panel);
			frame.panel = new RootUserView(frame,list);
			frame.add(frame.panel);
			frame.validate();
			
		} catch (Exception e2) {
			
		}
		
	}

}
