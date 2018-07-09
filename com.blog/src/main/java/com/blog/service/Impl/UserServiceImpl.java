package com.blog.service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.UserDao;
import com.blog.entity.User;
import com.blog.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@SuppressWarnings("null")
	@Override
	public int UserLogin(String username, String password) {

		User user = userDao.selectByUsername(username);
		
		if(user==null) {
			return 0;
		}
		else if (user!=null&&!user.getPassword().equals(password)) {
			return 2;
		}
		else
		{
			User user1=userDao.UserLogin(username, password);
			if(user1==null) {
				return 1;//
			}else {
				return 3;
			}
			
		}
		
		
	}

	@Override
	public int InserUser(String userID,String username, String password) {
		int flag = 1;
		
		User user = userDao.selectByUsername(username); 

		if(user!=null)
		{
			return 0;
		}
		
		else 
		{
			userDao.InserUser(username, password);
			flag = 2;
		}
		return flag;
	}

}
