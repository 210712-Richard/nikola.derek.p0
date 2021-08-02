package com.revature.data;

import java.util.ArrayList;
import java.util.List;

import com.revature.beans.User;
import com.revature.beans.UserType;

public class UserDAOFile implements UserDAO {
	// DAO = Database Access Object
	// This is a class that is dedicated to accessing data from persistence.
	private static String filename = "users.dat";
	private static List<User> users;
	
	static {
		DataSerializer<User> ds = new DataSerializer<User>();
		users = ds.readObjectsFromFile(filename);
		
		
		if(users == null) {
			users = new ArrayList<User>();
			users.add(new User(users.size(), "Nik", "nik@nik.nik", "111-222-3333", 3000l, "password", 1l));
			users.add(new User(users.size(), "Bob", "bob@bob.bob", "222-333-4444", 2000l, "password", 2l ));
			User u = new User(users.size(), "richard", "richard.orr@revature.com", "333-444-5555", 1000l, "password", 3l);
			//setting associate manually
			u.setType(UserType.ASSOCIATE);
			users.add(u);
			ds.writeFundsToFile(users, filename);
		}
	}
	
	@Override
	public void addUser(User u) {
		u.setId(users.size());
		users.add(u);
	}
	
	@Override
	public List<User> getUsers(){
		return users;
	}
	
	@Override
	public User getUser(String username) {

		return users.stream()
			.filter((u) -> u.getUsername().equals(username))
			.findFirst()
			.orElse(null);
	}
	
	
	public void writeToFile() {
		new DataSerializer<User>().writeFundsToFile(users, filename);
	}

}
