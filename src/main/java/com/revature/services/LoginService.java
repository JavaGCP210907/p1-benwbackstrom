package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.models.User;

public class LoginService {

	UserDao uDao = new UserDao();
	
	public boolean managerLogin(String username, String password) {
		
		User u = uDao.getUserByCredentials(username, password); //find a user with given credentials
		//verify they exist
		if(u != null) {
			//verify they are a manager
			if(u.getUser_role_fk().getUser_role_id() == 2) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean employeeLogin(String username, String password) {
		
		User u = uDao.getUserByCredentials(username, password); //find a user with given credentials
		//verify they exist
		if(u != null) {
			//verify they are a general employee
			if(u.getUser_role_fk().getUser_role_id() == 1) {
				return true;
			}
		}
		
		return false;
	}
	
}
