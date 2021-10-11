package com.revature.daos;

import com.revature.models.User;

public interface UserInterface {
	
	public User getUserById(int id);
	
	public User getUserByCredentials(String username, String password);
	
	public User getUserByUsername(String username);
	
	public boolean addUser(User user);
	//I would like to have a method to allow someone to create an account
	
	public void removeUser(String username);
	//^^Just for testing purposes

}
