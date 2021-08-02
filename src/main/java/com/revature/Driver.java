package com.revature;

import com.revature.controllers.UserController;
import com.revature.controllers.UserControllerImpl;
import com.revature.factory.BeanFactory;
//import com.revature.menu.Menu;
import com.revature.menu.Menu;

import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;


public class Driver {
	public static void main(String[] args) {
		//Menu m = new Menu();
		//m.start();
		
		 //Starts the Javalin framework
		Javalin app = Javalin.create().start(8080);
		
		UserController uc = new UserControllerImpl();
				
		app.get("/",(ctx)->ctx.html("Hello World"));
		
		
		
		//register for account
		app.put("/users/:username", uc::register);
		
		//Login
		app.post("/users", uc::login);
		
		//Logout
		app.delete("/users", uc::logout);
		
		//deposit
		app.put("users/:username/deposit", uc::deposit);
		
		//withdraw
		app.put("users/:username/withdraw", uc::withdraw);
		
		//view balance
		app.get("users/:username/funds", uc::getFunds);
	
				

	}
}
