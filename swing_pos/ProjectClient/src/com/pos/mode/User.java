package com.pos.mode;

import java.io.Serializable;//io对象流传输必须实现该接口

@SuppressWarnings("serial")
public class User implements Serializable{

	private String username;
	
	private String password;
	
	public User()
	{
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString()
	{
		return username+"+"+password;	
	}
	
}
