package com.pos.control;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import org.springframework.context.ApplicationContext;
import com.pos.mode.User;
import com.pos.server.UserServices;
import com.pos.server.impl.UserServicesImpl;

public class LoginControl {

	public String msg;
	public Socket socket;
	public ApplicationContext ct;
	
	public LoginControl(Socket socket,String msg,ApplicationContext ct)
	{
		this.msg = msg;
		this.socket = socket;
		this.ct = ct;
		
		analysis();
	}

	public void analysis()
	{
		
		String[] args = msg.split("\\+");
		if("validate".equals(args[1]))
		{
			validate(args);
		}
		else {
			update(args);
		}
	}
	
	//登录验证
	public void validate(String[] args) {
		System.out.println("login+"+msg);
		User user = new User();
		user.setUsername(args[2]);
		user.setPassword(args[3]);
		
		UserServices userServices = (UserServicesImpl)ct.getBean("userServicesImpl");
		String temp = "no";
		if(true==userServices.login(user))
		{
			temp = "yes";
		}
		OutputStream outputStream = null;
		PrintWriter printWriter = null;
		try {
			
			outputStream = socket.getOutputStream();
			printWriter = new PrintWriter(outputStream);
			printWriter.println(temp);
			printWriter.flush();
		} catch (Exception e) {
			
		}
	}
	
	//修改密码
	public void update(String[] args) {
		System.out.println("update+"+msg);
		User user = new User();
		user.setUsername(args[2]);
		user.setPassword(args[3]);
		
		UserServices userServices = (UserServicesImpl)ct.getBean("userServicesImpl");
		
		if(true==userServices.update(user))
		{
			OutputStream outputStream = null;
			PrintWriter printWriter = null;
			try {
				String temp = "yes";
				outputStream = socket.getOutputStream();
				printWriter = new PrintWriter(outputStream);
				printWriter.println(temp);
				printWriter.flush();
			} catch (Exception e) {
				
			}
		}
	}
}
