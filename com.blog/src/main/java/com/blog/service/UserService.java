package com.blog.service;

public interface UserService {
	
	public int UserLogin(String username,String password);
	
	public int InserUser(String userid,String username,String password);
}
