package com.myspringproject.springmvc.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspringproject.springmvc.dao.UserDao;
import com.myspringproject.springmvc.model.User;

@Service("userService")
public class UserService {
	@Autowired
	UserDao userDao;
	
	@Transactional
	public User findUserByUsername(String username){
		User user = new User();
		
		user = userDao.findByUserName(username);
		
		return user;
	}
}
