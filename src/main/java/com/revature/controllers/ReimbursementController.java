package com.revature.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

import io.javalin.http.Handler;

public class ReimbursementController {

	ReimbursementService rs = new ReimbursementService();
	
	public Handler getAllReimbursementsHandler = (ctx) -> {
		
		List<Reimbursement> allReimbursements = rs.getAllReimbursements();
		
		//instantiate Gson object to make JSON Java Object conversions
		Gson gson = new Gson();
		
		String JSONReimbursements = gson.toJson(allReimbursements);
		
		ctx.result(JSONReimbursements);
		
		ctx.status(200);
		
	};
	
	public Handler getReimbursementsByEmployeeIdHandler = (ctx) -> {
		
		//take the given path parameter and parse it into an int
		int id = Integer.parseInt(ctx.pathParam("id"));
		
		List<Reimbursement> pastReimbursements = rs.getReimbursementsByEmployeeId(id);
		//Employee's past tickets
		
		Gson gson = new Gson();
		
		String JSONReimbursements = gson.toJson(pastReimbursements);
		
		ctx.result(JSONReimbursements);
		
		ctx.status(200);
		
	};

	public Handler getReimbursementsByStatusHandler = (ctx) -> {
		
		//take path paramter
		String status = ctx.pathParam("status");
		
		List<Reimbursement> filteredReimbursements = rs.getReimbursementsByStatus(status);
		
		Gson gson = new Gson();
		
		String JSONReimbursements = gson.toJson(filteredReimbursements);
		
		ctx.result(JSONReimbursements);
		ctx.status(200);
		
	};

	public Handler addReimbursementHandler = (ctx) -> {
		
		String body = ctx.body(); //body contains JSON with the dino information
		
		Gson gson = new Gson();
		
		Reimbursement reimb = gson.fromJson(body, Reimbursement.class);
		
		boolean s = rs.addReimbursement(reimb);
		
		if(s) {
			ctx.status(201); //created
		} else {
			ctx.status(400); //bad request
		}
		
	};
}
