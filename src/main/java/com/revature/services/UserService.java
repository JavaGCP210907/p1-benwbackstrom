package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.models.User;

public class UserService {
	
	UserDao uDao = new UserDao();

	public boolean addUser(User user) {
		return uDao.addUser(user);
	}

	
	
}
