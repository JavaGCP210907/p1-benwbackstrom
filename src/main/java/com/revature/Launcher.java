package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.controllers.LoginController;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import com.revature.utils.ConnectionUtil;

import io.javalin.Javalin;

public class Launcher {

	public static void main(String[] args) {
		
		ReimbursementController rc = new ReimbursementController();
		UserController uc = new UserController();
		LoginController lc = new LoginController();
		
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
		
		//REFACTOR THIS TO MAKE MORE SENSE AS SOME ARE FOR FINANCIAL MANAGERS AND SOME ARE FOR NORMAL EMPLOYEES
		
		//GET all reimbursements
		app.get("/tickets", rc.getAllReimbursementsHandler);
		
		//GET employee's reimbursements
		app.get("/tickets/:id", rc.getReimbursementsByEmployeeIdHandler);
		
		//GET reimbursements filtered by status
		app.get("/tickets/filter/:status", rc.getReimbursementsByStatusHandler);
		
		//POST request sent to /tickets => create a new ticket
		app.post("/tickets", rc.addReimbursementHandler);
		
		//Sending a POST request to /users => adds a new user
		app.post("/users", uc.addUserHandler);
		
		//PATCH requests to approve/deny the tickets
		app.patch("/manager/:mid/approve/:rid", rc.approveReimbursementHandler);
		
		app.patch("manager/:mid/reject/:rid", rc.rejectReimbursementHandler);
		
		//Send a POST request to validate user login credentials
		app.post("/manlogin", lc.managerLoginHandler);
		
		app.post("/emplogin", lc.employeeLoginHandler);
		
	}

}
