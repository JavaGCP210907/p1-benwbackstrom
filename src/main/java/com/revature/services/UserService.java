package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.models.User;

public class UserService {
	
	UserDao uDao = new UserDao();

	public boolean addUser(User user) {
		return uDao.addUser(user);
	}

	public User getUserByCredentials(String username, String password) {
		return uDao.getUserByCredentials(username, password);
		//Need to do this to be able to get user id for our POST/PATCH Methods
	}
	
}
