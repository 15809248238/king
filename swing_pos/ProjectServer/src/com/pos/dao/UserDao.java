package com.pos.dao;

import java.util.List;

import com.pos.mode.User;

public interface UserDao {
	
	public List<User> finduserByUser(User user);
	
	public boolean insertUserByUser(User user);
	
	public boolean updateUserByUser(User user);
}
