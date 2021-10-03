package com.revature.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

import io.javalin.http.Handler;

public class ReimbursementController {

	ReimbursementService rs = new ReimbursementService();
	UserService us = new UserService();
	Logger log = LogManager.getLogger(ReimbursementController.class);
	
	public Handler getAllReimbursementsHandler = (ctx) -> {
		
		if(ctx.req.getSession(false) != null) { //if a session exists...
			
			List<Reimbursement> allReimbursements = rs.getAllReimbursements();
			
			//instantiate Gson object to make JSON Java Object conversions
			Gson gson = new Gson();
			
			String JSONReimbursements = gson.toJson(allReimbursements);
			
			ctx.result(JSONReimbursements);
			
			ctx.status(200);
			
			log.info("USER RETRIEVED LIST OF ALL REIMBURSEMENT TICKETS");
		} else {
			ctx.status(402); //forbidden status code
		}
		
	};
	
	public Handler getReimbursementsByEmployeeIdHandler = (ctx) -> {
		
		if(ctx.req.getSession(false) != null) {
				
			String body = ctx.body(); //body has user credentials
			
			Gson gson = new Gson();
			
			User u = gson.fromJson(body, User.class);
			
			u = us.getUserByCredentials(u.getUsername(), u.getPassword());
			//Get full user information from the body
			
			int id = u.getUser_id();
			
			u = null; //remove user details from server
			
			List<Reimbursement> pastReimbursements = rs.getReimbursementsByEmployeeId(id);
			//Employee's past tickets
			
			String JSONReimbursements = gson.toJson(pastReimbursements);
			
			ctx.result(JSONReimbursements);
			
			ctx.status(200);
			
			log.info("USER RETRIEVED LIST OF TICKETS SUBMITTED BY USER #" + id);
			
		} else {
			ctx.status(402);
		}
	};

	public Handler getReimbursementsByStatusHandler = (ctx) -> {
		
		if(ctx.req.getSession(false) != null) {
				
			//take path paramter
			String status = ctx.pathParam("status");
			
			List<Reimbursement> filteredReimbursements = rs.getReimbursementsByStatus(status);
			
			Gson gson = new Gson();
			
			String JSONReimbursements = gson.toJson(filteredReimbursements);
			
			ctx.result(JSONReimbursements);
			ctx.status(200);
			
		} else {
			ctx.status(402);
		}
	};

	public Handler addReimbursementHandler = (ctx) -> {
		
		if(ctx.req.getSession(false) != null) {
		
			String body = ctx.body(); //body contains JSON with the Reimbursement information
			
			Gson gson = new Gson();
			
			Reimbursement reimb = gson.fromJson(body, Reimbursement.class);
			
			User u = reimb.getReimb_author(); //gets username/password out of the reimb
			u = us.getUserByCredentials(u.getUsername(), u.getPassword());
			reimb.setReimb_author(u); //set full user to the Reimbursement's author field
			u = null;
			
			boolean s = rs.addReimbursement(reimb);
			
			if(s) {
				ctx.status(201); //created
				
				log.info("EMPLOYEE SUBMITTED A REIMBURSEMENT TICKET");
				
			} else {
				ctx.status(400); //bad request
			}
			
		} else {
			ctx.status(402);
		}
		
	};

	public Handler approveReimbursementHandler = (ctx) -> {
		
		if(ctx.req.getSession(false) != null) {
			
			int rId = Integer.parseInt(ctx.pathParam("rid"));
			//get Reimbursement Id from the path parameter
			
			String body = ctx.body();
			
			Gson gson = new Gson();
			
			User u = gson.fromJson(body, User.class);
			u = us.getUserByCredentials(u.getUsername(), u.getPassword());
			int mId = u.getUser_id();
			u = null;
			
			boolean a = rs.approveReimbursement(rId, mId);
			
			if(a) {
				ctx.status(202); //accepted
				
				log.warn("MANAGER (USER #" + mId +" ) APPROVED REIMBURSEMENT TICKET #" + rId);
			} else {
				ctx.status(400);
			}
			
		} else {
			ctx.status(402);
		}
		
	};

	public Handler rejectReimbursementHandler = (ctx) -> {
		
		if(ctx.req.getSession(false) != null) {
		
			int rId = Integer.parseInt(ctx.pathParam("rid"));
			
			String body = ctx.body();
			
			Gson gson = new Gson();
			
			User u = gson.fromJson(body, User.class);
			u = us.getUserByCredentials(u.getUsername(), u.getPassword());
			int mId = u.getUser_id();
			u = null;
			
			boolean r = rs.rejectReimbursement(rId, mId);
			
			if(r) {
				ctx.status(202);
				
				log.warn("MANAGER (USER #" + mId +" ) DENIED REIMBURSEMENT TICKET #" + rId);
			} else {
				ctx.status(400);
			}
			
		} else {
			ctx.status(402);
		}
	};
	
}
