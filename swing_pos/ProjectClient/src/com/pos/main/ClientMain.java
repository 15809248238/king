package com.pos.main;

import java.net.Socket;

import com.pos.tool.GetSocket;
import com.pos.view.LoginView;

public class ClientMain {
	
	public static Socket socket;
	
	public static void main(String[] args) {
		
		socket = GetSocket.getSocke();
		
		new LoginView();
		
	}
}
