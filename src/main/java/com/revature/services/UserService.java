package com.revature.services;

import com.revature.beans.User;

public interface UserService {

	User login(String name);
	
	User jsonRegister(String username, String password, String email, String phone);
	
	void register(String username, String password, String email, String phone);
	
	boolean checkForDuplicates(String newName);
	
	boolean checkIfExists(String newName);	
	
	void Deposit(User user, Long funds);
	
	void Withdraw(User user, Long funds);
	
	
	
	
	
	
}
