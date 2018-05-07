package com.pos.server;

import java.util.List;

import com.pos.mode.User;

public interface UserServices {
	
	public User login(User user);
	
	public List<User> findAllRoot();
	
	public List<User> findRootByUsername(String username);
	
	public boolean insert(User user);
	
	public List<User> save(User user);
	
	public boolean update(String username,String password);
	
	public boolean updatePassword(String username,String password,String newpassword);
	
	public List<User> deleteByUserID(int userID);
	
}
