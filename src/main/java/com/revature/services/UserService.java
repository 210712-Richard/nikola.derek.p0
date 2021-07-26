package com.revature.services;

import java.time.LocalDate;
import java.time.Period;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.revature.beans.User;
import com.revature.data.UserDAO;

public class UserService {
	//
	//private Logger log = LogManager.getLogger(UserService.class);
	public UserDAO ud = new UserDAO();
	
	public User login(String name) {
		User u = ud.getUser(name);
		ud.writeToFile();
		return u;
	}
	
	public void Deposit(User user, Long funds) {
		user.setFunds(user.getFunds() + funds);
		ud.writeToFile();
	}
	public void Withdraw(User user, Long funds) {
		user.setFunds(user.getFunds() - funds);
		ud.writeToFile();
	}
	
	public void register(String username, String password, String email, String phone) {
		User u = new User();
		u.setUsername(username);
		u.setEmail(email);
		u.setPhone(phone);
		u.setPassword(password);
		u.setFunds(0l);
		ud.addUser(u);
		ud.writeToFile();
	}

	public boolean checkForDuplicates(String newName) {
		return ud.getUsers()
				.stream()
				.noneMatch((u)->u.getUsername().equals(newName));
	}
	
	public boolean checkIfExists(String newName) {
		return ud.getUsers()
				.stream()
				.noneMatch((u)->u.getUsername().equals(newName));
	}
	

}
