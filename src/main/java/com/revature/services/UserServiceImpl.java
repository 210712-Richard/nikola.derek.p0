package com.revature.services;

import java.time.LocalDate;
import java.time.Period;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.revature.beans.User;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOFile;
import com.revature.factory.BeanFactory;
import com.revature.factory.Log;

@Log
public class UserServiceImpl implements UserService {
	//
	private Logger log = LogManager.getLogger(UserServiceImpl.class);
	public UserDAO ud = new UserDAOFile();
	
	
	
	
	
	@Override
	public User login(String name) {
		User u = ud.getUser(name);
		(new UserDAOFile()).writeToFile();
		return u;
	}
	
	@Override
	public void Deposit(User user, Long funds) {
		user.setFunds(user.getFunds() + funds);
		(new UserDAOFile()).writeToFile();
	}
	
	@Override
	public void Withdraw(User user, Long funds) {
		user.setFunds(user.getFunds() - funds);
		(new UserDAOFile()).writeToFile();
	}
	
	@Override
	public void register(String username, String password, String email, String phone) {
		User u = new User();
		u.setUsername(username);
		u.setEmail(email);
		u.setPhone(phone);
		u.setPassword(password);
		u.setFunds(0l);
		ud.addUser(u);
		(new UserDAOFile()).writeToFile();
	}

	@Override
	public boolean checkForDuplicates(String newName) {
		return ud.getUsers()
				.stream()
				.noneMatch((u)->u.getUsername().equals(newName));
	}
	
	@Override
	public boolean checkIfExists(String newName) {
		return ud.getUsers()
				.stream()
				.noneMatch((u)->u.getUsername().equals(newName));
	}
	
	@Override
	public User jsonRegister(String username, String password, String email, String phone) {
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPhone(phone);
		user.setPassword(password);
		user.setFunds(0l);
		
		ud.addUser(user);
		(new UserDAOFile()).writeToFile();
		
		return user;
	}

	
	

}
