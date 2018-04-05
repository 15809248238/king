package com.pos.server;

import com.pos.mode.User;

public interface UserServices {
	
	public boolean login(User user);
	
	public boolean insert(User user);
	
	public boolean update(User user);
	
}
