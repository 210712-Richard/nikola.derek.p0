package com.revature;

import com.revature.menu.Menu;

import io.javalin.Javalin;

public class Driver {
	public static void main(String[] args) {
		//Menu m = new Menu();
		//m.start();
		
		 //Starts the Javalin framework
		Javalin app = Javalin.create().start(8080);
		
		app.get("/",(ctx)->ctx.html("Hello World"));
		
		//As a user I can Login
		app.post("/users", uc::login);
	}
}
