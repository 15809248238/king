package com.pos.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.springframework.context.ApplicationContext;
import com.pos.control.Control;
import com.pos.duitl.Dutil;

public class ServerMain{
	
	private static int port = 8888;//端口号
	private static ServerSocket serverSocket = null;//ServerSocket
	private static Socket socket = null;//socket
	
	private static ApplicationContext ct = Dutil.getApplicationContext();
	
	public static void main(String[] args) {
		
		try {
			serverSocket = new ServerSocket(port);
			
			while(true)
			{
				socket = serverSocket.accept();
				new Control(socket,ct).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
