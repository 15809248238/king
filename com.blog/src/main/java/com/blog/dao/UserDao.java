package com.blog.dao;

import org.apache.ibatis.annotations.Param;

import com.blog.entity.User;

public interface UserDao {
	User select(String userid);
	User selectByUsername(@Param("username")String username);
	
	User UserLogin(@Param("username")String username,@Param("password")String password);
	
	boolean InserUser(@Param("username")String username,@Param("password")String password);
}
