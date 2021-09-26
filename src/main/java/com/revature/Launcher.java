package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.controllers.ReimbursementController;
import com.revature.utils.ConnectionUtil;

import io.javalin.Javalin;

public class Launcher {

	public static void main(String[] args) {
		
		ReimbursementController rc = new ReimbursementController();
		
		//testing whether our connection works...
		try(Connection conn = ConnectionUtil.getConnection()){
			System.out.println("Connection to Database Successful!");
		} catch (SQLException e) {
			System.out.println("Connection Failed");
			e.printStackTrace();
		}
		
		Javalin app = Javalin.create(
				config -> {
					config.enableCorsForAllOrigins(); //allows the server to process JS requests from anywhere
				}
				).start(8090);
		
		//GET all reimbursements
		app.get("/tickets", rc.getAllReimbursementsHandler);

	}

}
