package com.revature.controllers;

import io.javalin.http.Context;


public interface UserController {

	void login(Context ctx);
	
	void getFunds(Context ctx);
	
	void logout(Context ctx);
	
	void register(Context ctx);
	
	void deposit(Context ctx);
	
	void withdraw(Context ctx);
}
