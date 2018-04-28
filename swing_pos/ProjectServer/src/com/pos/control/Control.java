package com.pos.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

import org.springframework.context.ApplicationContext;


public class Control extends Thread{
	
	public Socket socket;
	public ApplicationContext ct;
	public Map<String,String> map;
	public Control(Socket socket,ApplicationContext ct)
	{
		this.socket = socket;
		this.ct = ct;
	}
	
	public void run(){
		
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
			
		String msg = null;
		try {
			
			inputStream = socket.getInputStream();
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
			
			while((msg = bufferedReader.readLine())!=null)
			{	
				System.out.println(msg);
				
				if("end".equals(msg))
				{
					try {
						OutputStream outputStream = socket.getOutputStream();
						PrintWriter printWriter = new PrintWriter(outputStream);
						String nMsg	= "end";
						printWriter.println(nMsg);
						printWriter.flush();
						socket.close();
						break;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				else {
					String[] args = msg.split("\\+");
					if("login".equals(args[0]))
					{
						new LoginControl(socket,msg,ct); 
					}
					else if("depart".equals(args[0]))	{
						new DepartControl(socket,msg,ct);
					}
					else if("employee".equals(args[0]))	{
						new EmployeeControl(socket,msg,ct);
					}
					else if("customer".equals(args[0]))	{
						new CustomerControl(socket,msg,ct);
					}
					else if("cargo".equals(args[0]))	{
						new CargoControl(socket,msg,ct);
					}
					else if("ware".equals(args[0]))	{
						new WareControl(socket,msg,ct);
					}
					else if("invent".equals(args[0]))	{
						new InventoryControl(socket,msg,ct);
					}
					else if("sale".equals(args[0]))	{
						new SaleControl(socket,msg,ct);
					}
					else if("purchase".equals(args[0]))	{
						new PurchaseControl(socket,msg,ct);
					}
					else if("user".equals(args[0]))	{
						new UserControl(socket, msg, ct);
					}
					else if("database".equals(args[0]))	{
						new DataControl(socket, msg, ct);
					}
				}
			}			
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
}	
	
