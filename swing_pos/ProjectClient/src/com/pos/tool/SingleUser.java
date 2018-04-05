package com.pos.tool;

import com.pos.mode.User;

public class SingleUser {

	private static User user;
	
	public static synchronized User getUser(){
		if(user==null)
		{
			user = new User();
		}
		return user;
	}
}
