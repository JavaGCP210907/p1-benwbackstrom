package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.services.LoginService;
import com.revature.utils.JwtUtil;

import io.javalin.http.Handler;

public class LoginController {

	LoginService ls = new LoginService();
	
	public Handler managerLoginHandler = (ctx) -> {
		
		String body = ctx.body(); //turn body of POST request into Java String
		
		Gson gson = new Gson();
		
		LoginDTO LDTO = gson.fromJson(body, LoginDTO.class);// Turn that JSON String into a LoginDTO object
		
		//control flow to determine what happens in the event of a successful/unsuccessful login
		
		if(ls.managerLogin(LDTO.getUsername(), LDTO.getPassword())) { //successful login
			
			//Generate JSON Web Token to uniquely identify the user
			String jwt = JwtUtil.generate(LDTO.getUsername(), LDTO.getPassword());
			
			//create a user session
			ctx.req.getSession();
			
			ctx.status(200); //successfu status code
			
			ctx.result("Login Success! JWT is: " + jwt);
		} else {
			ctx.status(401); //unauthorized
			ctx.result("Login Failed!");
		}
		
		
	};
	
	public Handler employeeLoginHandler = (ctx) -> {
		
		String body = ctx.body(); //turn body of POST request into Java String
		
		Gson gson = new Gson();
		
		LoginDTO LDTO = gson.fromJson(body, LoginDTO.class);// Turn that JSON String into a LoginDTO object
		
		//control flow to determine what happens in the event of a successful/unsuccessful login
		
		if(ls.employeeLogin(LDTO.getUsername(), LDTO.getPassword())) { //successful login
			
			//Generate JSON Web Token to uniquely identify the user
			String jwt = JwtUtil.generate(LDTO.getUsername(), LDTO.getPassword());
			
			//create a user session
			ctx.req.getSession();
			
			ctx.status(200); //successfu status code
			
			ctx.result("Login Success! JWT is: " + jwt);
		} else {
			ctx.status(401); //unauthorized
			ctx.result("Login Failed!");
		}
		
		
	};
	
}
