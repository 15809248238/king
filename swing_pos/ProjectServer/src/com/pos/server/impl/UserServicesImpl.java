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
	public boolean login(User temp) {
		
		boolean flag = false;
		
		List<User> user = userDao.finduserByUser(temp);
		
		if(user!=null)
		{
			flag = true;
		}	
		
		return flag;
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
	public boolean update(User user) {
		boolean flag = false;
		
		if(userDao.updateUserByUser(user))
		{
			flag = true;
		}
		
		return flag;
	}

}
