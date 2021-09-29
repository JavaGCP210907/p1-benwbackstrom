package com.revature.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.revature.models.User;
import com.revature.services.UserService;

import io.javalin.http.Handler;

public class UserController {
	
	UserService us = new UserService();
	Logger log = LogManager.getLogger(UserController.class);

	public Handler addUserHandler = (ctx) -> {
		
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		User user = gson.fromJson(body, User.class);
		
		boolean s = us.addUser(user);
		
		if(s) {
			ctx.status(201);
			
			log.info("NEW USER ACCOUNT CREATED");
		} else {
			ctx.status(400);
		}
		
	};
	
}
