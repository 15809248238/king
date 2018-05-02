package com.pos.control;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import org.springframework.context.ApplicationContext;
import com.pos.mode.User;
import com.pos.server.UserServices;
import com.pos.server.impl.UserServicesImpl;

public class UserControl {
	
	public String msg;
	public Socket socket;
	public ApplicationContext ct;
	
	public UserControl(Socket socket,String msg,ApplicationContext ct) {
		this.msg = msg;
		this.socket = socket;
		this.ct = ct;
		analysis();
	}

	private void analysis() {
		String[] args = msg.split("\\+");
		if ("select".equals(args[1])&&args.length==2)
		{
			findAll();
		}
		else if	("select".equals(args[1])&&args.length!=2){
			findByCustomername(args);
		}
		else if ("delete".equals(args[1])){
			delete(args);
		}
		else if ("save".equals(args[1])) {
			save(args);
		}
	}

	private void save(String[] args) {
		
		User user = new User();
		user.setUserID(Integer.parseInt(args[2]));
		user.setUsername(args[3]);
		user.setPassword(args[4]);
		user.setType(args[5]);
		
		UserServices  userServices =(UserServicesImpl)ct.getBean("userServicesImpl");
		
		List<User> list = userServices.save(user);
		OutputStream outputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			
			outputStream = socket.getOutputStream();
			objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(list);
			objectOutputStream.flush();
			
		} catch (Exception e) {
			
		}
		
	}

	private void delete(String[] args) {
		UserServices  userServices =(UserServicesImpl)ct.getBean("userServicesImpl");
		
		List<User> list = userServices.deleteByUserID(Integer.parseInt(args[2]));
		
		OutputStream outputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			
			outputStream = socket.getOutputStream();
			objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(list);
			objectOutputStream.flush();
			
		} catch (Exception e) {
			
		}
	}

	private void findByCustomername(String[] args) {
		UserServices  userServices =(UserServicesImpl)ct.getBean("userServicesImpl");
		
		List<User> list = userServices.findRootByUsername(args[2]);
		
		OutputStream outputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			
			outputStream = socket.getOutputStream();
			objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(list);
			objectOutputStream.flush();
			
		} catch (Exception e) {
			
		}
		
	}

	private void findAll() {
		UserServices  userServices =(UserServicesImpl)ct.getBean("userServicesImpl");
		
		List<User> list = userServices.findAllRoot();
		
		OutputStream outputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			
			outputStream = socket.getOutputStream();
			objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(list);
			objectOutputStream.flush();
			
		} catch (Exception e) {
			
		}
	}
	
}
