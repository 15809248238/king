package com.pos.dao;

import com.pos.mode.User;

public interface UserDao {
	
	public User finduserByUser(User user);
	
	public boolean insertUserByUser(User user);
	
	public boolean updateUserByUser(User user);
}
