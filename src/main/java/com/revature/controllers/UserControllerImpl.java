package com.revature.controllers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.data.UserDAOFile;
import com.revature.factory.BeanFactory;
import com.revature.factory.Log;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

import io.javalin.http.Context;

@Log
public class UserControllerImpl implements UserController {
	
	private static Logger log = LogManager.getLogger(UserControllerImpl.class);
	private UserServiceImpl us = new UserServiceImpl();
	
	private UserDAOFile userDAO = new UserDAOFile();
	
	
	@Override
	public void login(Context ctx) {
		log.trace("Login method called");
		log.debug(ctx.body());
		
		
	}
	@Override
	public void getFunds(Context ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void logout(Context ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void register(Context ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deposit(Context ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void withdraw(Context ctx) {
		// TODO Auto-generated method stub
		
	}
 
}
