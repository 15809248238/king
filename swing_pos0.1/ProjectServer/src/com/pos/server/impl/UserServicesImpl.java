package com.pos.server.impl;

import java.util.List;

import com.pos.dao.UserDao;
import com.pos.mode.User;
import com.pos.server.UserServices;

public class UserServicesImpl implements UserServices{
	
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User login(User temp) {
		
		User user = userDao.finduserByUser(temp);
		
		if(user!=null)
		{
			return user;
		}	
		
		return null;
	}
	
	@Override
	public boolean insert(User user) {
		
		boolean flag = false;
		
		if(userDao.insertUserByUser(user))
		{
			flag = true;
		}
		
		return flag;
	}
	
	@Override
	public boolean update(String username,String password) {
		boolean flag = false;
		System.out.println("hahah   "+username);
		if(userDao.updateUserByUser(username,password))
		{
			flag = true;
		}
		
		return flag;
	}

	@Override
	public List<User> findAllRoot() {
		List<User> list = null;
		list = userDao.findAllRoot();
		return list;
	}

	@Override
	public List<User> findRootByUsername(String username) {
		List<User> list = null;
		list = userDao.findRootByUsername(username);
		return list;
	}

	@Override
	public List<User> deleteByUserID(int userID) {
		List<User> list = null;
		if(userDao.deleteByUserID(userID))
		{
			list = findAllRoot();
		}
		return list;
	}

	@Override
	public List<User> save(User user) {
		List<User> list = null;
		
		if(userDao.insertUserByUser(user))
		{
			list = findAllRoot();
		}
		
		return list;
	}

}
