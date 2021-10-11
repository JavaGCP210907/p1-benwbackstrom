package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.models.UserRole;

public class Tests {

	//Service objects to test with
	public static ReimbursementService rs;
	public static UserService us;
	public static LoginService ls;
	
	//variables/objects to use in my tests
	public User man = new User();
	public User emp = new User();
	public int validId = 2;
	public int validManId = 1;
	public int invalidId = 100;
	public boolean result;
	
	@BeforeAll
	public static void createServices() { //Need to instantiate our services before any test starts
		ls = new LoginService();
		rs = new ReimbursementService();
		us = new UserService();
	}
	
	@AfterAll
	public static void clearServices() { //after tests are done, clear the objects
		//Delete created DB records
		us.removeUser("testuser");
		
		//Clear the services
		ls = null;
		rs = null;
		us = null;
	}
	
	//Unit tests --------------------------------------------------------------
	//LoginService Tests -------------------------------------------
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
	
	//Login uses the same method as UserService's getUserByCredentials, so I shouldnt need to also test that
	//UserService Tests----------------------------------------------
	@Test
	public void testGetUserByUsername() {
		emp.setUsername("coolweb12");
		//This is a user that exists in the database
		User u = us.getUserByUsername(emp.getUsername());
		assertNotNull(u);
		//Test passes if a user exists
	}
	
	@Test
	public void testGetUserFalseUsername() {
		//Pretty much same as before, but I want to use credentials that do not match a user in the DB
		emp.setUsername("imafraud99");
		
		User u = us.getUserByUsername(emp.getUsername());
		assertNull(u);
		//Test passes if the user is null (no DB record exists)
		
	}
	
	@Test
	public void testAddUser() {
		emp.setUsername("testuser");
		emp.setPassword("testing");
		emp.setFirst_name("Tess");
		emp.setLast_name("Tear");
		emp.setEmail("tester@revature.net");
		UserRole ur = new UserRole(1, "Employee");
		emp.setUser_role_fk(ur);
		
		result = us.addUser(emp);
		
		assertTrue(result);
		//Test passes if result is true (user can be created)
	}
	//Actually creates a user in the DB, so be careful with this test
	
	
	//ReimbursementService Tests----------------------------------------
	@Test
	public void testGetAllReimbursements() {
		List<Reimbursement> rList = rs.getAllReimbursements();
		
		assertNotNull(rList);
		//Test passes if method retrieves a list of Reimbursements
	}
	
	@Test
	public void testGetReimbursementsByEmployeeId() {
		List<Reimbursement> rList = rs.getReimbursementsByEmployeeId(validId);
		//This should provide a list of reimbursements as 2 is a valid employee id
		
		assertNotNull(rList);
	}
	
	@Test
	public void testGetReimursementsByFakeId() {
		//Negative test associated with the previous one
		List<Reimbursement> rList = rs.getReimbursementsByEmployeeId(invalidId);
		
		//This doesnt really give a null object, but instead an empty list
		//So, instead, we can see if 0 is an out of bounds index => no entries in the list
		assertThrows(IndexOutOfBoundsException.class, () -> rList.get(0));
	}
	
	@Test
	public void testGetReimbursementByStatus() {
		
		String str = "Approved";
		List<Reimbursement> rList = rs.getReimbursementsByStatus(str);
		//This should give a list of Reimbursements
		
		assertNotNull(rList);
		//Test passes if list comes back
	}
	
	@Test
	public void testGetReimbursementByInvalidStatus() {
		
		String str = "HaHAaHa"; //not a status type
		List<Reimbursement> rList = rs.getReimbursementsByStatus(str);
		
		assertThrows(IndexOutOfBoundsException.class, () -> rList.get(0));
	}
	
}
