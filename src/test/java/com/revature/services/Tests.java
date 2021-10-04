package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.models.User;

public class Tests {

	//Service objects to test with
	public static ReimbursementService rs;
	public static UserService us;
	public static LoginService ls;
	
	//variables/objects to use in my tests
	public User man = new User();
	public User emp = new User();
	public boolean result;
	
	@BeforeAll
	public static void createServices() { //Need to instantiate our services before any test starts
		ls = new LoginService();
		rs = new ReimbursementService();
		us = new UserService();
	}
	
	@AfterAll
	public static void clearServices() { //after tests are done, clear the objects
		ls = null;
		rs = null;
		us = null;
	}
	
	//Unit tests --------------------------------------------------------------
	@Test
	public void testManLogin() {
		man.setUsername("bwb");
		man.setPassword("password");
		//These are correct credentials for a manager
		result = ls.managerLogin(man.getUsername(), man.getPassword());
		assertTrue(result);
		//test passes if credentials match a manager
	}
	
	@Test
	public void testEmpLogin() {
		emp.setUsername("coolweb12");
		emp.setPassword("password");
		//These are corrent credentials for an employee
		result = ls.employeeLogin(emp.getUsername(), emp.getPassword());
		assertTrue(result);
		//test passes if credentials match a general employee
	}
	
	@Test
	public void testFalseManLogin() {
		//I want this to be a negative test, making sure a non-manager cannot login as one
		man.setUsername("coolweb12");
		man.setPassword("password");
		//This user does not have valid manager credentials
		result = ls.managerLogin(man.getUsername(), man.getPassword());
		assertFalse(result);
		//This test passes if login fails
	}
	
}
