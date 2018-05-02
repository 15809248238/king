package com.pos.mode;

import java.io.Serializable;//io对象流传输必须实现该接口

@SuppressWarnings("serial")
public class User implements Serializable{

	private int userID;
	
	private String username;
	
	private String password;
	
	private String type;

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
		return userID+"+"+username+"+"+password+"+"+type;	
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
}
