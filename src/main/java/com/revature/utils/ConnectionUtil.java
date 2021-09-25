package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection() throws SQLException {
		
		//For compatibility with other technologies/frameworks, we'll need to register our PostgreSQL driver
		//This process makes the application aware of what Driver class we're using
		try {
			Class.forName("org.postgresql.Driver"); //searching for the postgres driver, which we have as a dependency
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); //This tells in the console us what went wrong
			System.out.println("problem occurred locating driver");
		}
		
		
		//Use our database credentials to establish a database connection
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=employee_reimbursements";
		String username = "postgres";
		String password = "password";
		
		//returns our actual database Connection object
		return DriverManager.getConnection(url, username, password);
	}
}
