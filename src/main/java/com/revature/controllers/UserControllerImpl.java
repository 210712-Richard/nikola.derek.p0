package com.revature.controllers;

import java.util.logging.Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.*;
import com.revature.data.*;
import com.revature.factory.*;
import com.revature.services.*;

import io.javalin.http.Context;

public class UserControllerImpl implements UserController {
	
	private static Logger log = LogManager.getLogger(UserController.class);
	private UserServiceImpl us = (UserServiceImpl) BeanFactory.getFactory().get(UserService.class, UserServiceImpl.class);
	private UserDAOFile userDAO = new UserDAOFile();
	
	
	@Override
	public void login(Context ctx) {
		// TODO Auto-generated method stub
		
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
