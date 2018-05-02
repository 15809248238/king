package com.pos.tool;

import java.io.IOException;
import java.net.Socket;

public class GetSocket {

	private static Socket socket;
	
	private static String host = "127.0.0.1";
	private static int port = 8888;
	
	public static synchronized Socket getSocke(){
		if(socket==null)
		{
			try {
				socket = new Socket(host, port);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return socket;
	}
}
