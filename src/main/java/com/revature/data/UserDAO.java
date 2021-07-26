package com.revature.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.User;
import com.revature.beans.UserType;

public class UserDAO {
	// DAO = Database Access Object
	// This is a class that is dedicated to accessing data from persistence.
	private static String filename = "users.dat";
	private static List<User> users;
	
	static {
		DataSerializer<User> ds = new DataSerializer<User>();
		users = ds.readObjectsFromFile(filename);
		
		// Helper for myself. If no users exist in the users.dat file (first startup) than I should create a few
		if(users == null) {
			users = new ArrayList<User>();
			users.add(new User(users.size(), "William", "will@will.will", "111-222-3333", 3000l, "password"));
			users.add(new User(users.size(), "Jaclyn", "jaclyn@jaclyn.jaclyn", "222-333-4444", 2000l, "password" ));
			User u = new User(users.size(), "richard", "richard.orr@revature.com", "333-444-5555", 1000l, "password");
			//setting associate manually
			u.setType(UserType.ASSOCIATE);
			users.add(u);
			ds.writeFundsToFile(users, filename);
		}
	}
	public void addUser(User u) {
		u.setId(users.size());
		users.add(u);
	}
	
	public List<User> getUsers(){
		return users;
	}
	
	public User getUser(String username) {
		
//		for(User user : users) {
//			if(user.getUsername().equals(username)) {
//				return user;
//			}
//		}
//		
//		return null;
		return users.stream()
			.filter((u) -> u.getUsername().equals(username))
			.findFirst()
			.orElse(null);
	}
	
	public void updateUser(User user) {
		// due to us holding the entire list in memory, we will actually automatically update the user
		// in the list anytime we change the fields of the user object.
		
	}
	
	public void writeToFile() {
		new DataSerializer<User>().writeFundsToFile(users, filename);
	}

}
