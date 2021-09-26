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
}
