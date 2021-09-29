package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.User;
import com.revature.services.UserService;

import io.javalin.http.Handler;

public class UserController {
	
	UserService us = new UserService();

	public Handler addUserHandler = (ctx) -> {
		
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		User user = gson.fromJson(body, User.class);
		
		boolean s = us.addUser(user);
		
		if(s) {
			ctx.status(201);
		} else {
			ctx.status(400);
		}
		
	};
	
}
