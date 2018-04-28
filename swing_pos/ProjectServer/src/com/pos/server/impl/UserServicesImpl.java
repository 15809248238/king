package com.pos.server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.pos.dao.UserDao;
import com.pos.mode.User;
import com.pos.server.UserServices;

@Transactional
@Component
public class UserServicesImpl implements UserServices{
	@Autowired
	private UserDao userDao;
	
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
	public boolean update(String username,String password,String newpassword) {
		boolean flag = false;
		
		if(userDao.updateUserByUser(username,password,newpassword))
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
