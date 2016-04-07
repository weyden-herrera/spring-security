package com.myspringproject.springmvc.dao;

import com.myspringproject.springmvc.model.User;

public interface UserDao {
	User findByUserName(String username);
	
}
