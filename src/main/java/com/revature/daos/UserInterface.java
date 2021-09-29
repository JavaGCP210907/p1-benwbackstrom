package com.revature.daos;

import com.revature.models.User;

public interface UserInterface {
	
	public User getUserById(int id);
	
	public User getUserByCredentials(String username, String password);

}
