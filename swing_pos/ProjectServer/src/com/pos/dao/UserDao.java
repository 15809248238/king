package com.pos.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pos.mode.User;

public interface UserDao {
	
	public User finduserByUser(User user);
	
	public List<User> findRootByUsername(@Param("username")String username);
	
	public List<User> findAllRoot();
	
	public boolean insertUserByUser(User user);
	
	public boolean upodatePassword(@Param("username")String username,@Param("password")String password,@Param("newpassword")String newpassword);
	
	public boolean updateUserByUser(@Param("username")String username,@Param("password")String password);
	
	public boolean updateUserTypeByUser(User user);
	
	public boolean deleteByUsername(@Param("username")String username);
	
	public boolean deleteByUserID(@Param("userID")int userID);
	
}
