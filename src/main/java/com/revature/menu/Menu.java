package com.revature.menu;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.revature.beans.User;
import com.revature.services.UserServiceImpl;
import com.revature.util.SingletonScanner;

// Encapsulate the user interface methods
public class Menu {

	private static final Logger log = LogManager.getLogger(Menu.class);
	
	private UserServiceImpl us = new UserServiceImpl();
	private User loggedUser = null;
	private Scanner scan = SingletonScanner.getScanner().getScan();
	private Long newFunds = null;
	
	public void start() {
		log.trace("Start BankApp. start()");
		mainLoop: while(true) {
			switch(startMenu()) {
			case 1:
				// login
				System.out.println("Please enter your username: ");
				String username = scan.nextLine();
				log.debug(username);
				// Call the user service to find the user we want.
				User u = us.login(username);
				if(u == null) {
					log.warn("Unsuccessful login attempt: "+ username);
					System.out.println("Incorrect username. Please try again.");
				} else {
					
					System.out.println("Enter password: ");
					String password = scan.nextLine();
					log.debug(password);
					
					if(!u.getPassword().equals(password)) {
						log.warn("Unsuccessful login attempt for "+username+" using password: "+password);
						System.out.println("Incorrect password.");
						continue mainLoop;
					}else {
					
						loggedUser = u;
						System.out.println("Welcome back: "+u.getUsername());
					
						
							switch(loggedUser.getType()) {
							case MEMBER:
								member();
								break;
							case ASSOCIATE:
								associate();
								break;
							}
					}
				}
				break;
			case 2:
				System.out.println("Choose your username: ");
				String newName = scan.nextLine();
				if(!us.checkForDuplicates(newName)) {
					System.out.println("Username not available, please try again.");
					continue mainLoop;
				}
				System.out.println("Enter your password: ");
				String password = scan.nextLine();
				
				System.out.println("Enter your email address: ");
				String email = scan.nextLine();
				
				System.out.println("enter your Phone Number (XXX-XXX-XXXX): ");
				String phone = scan.nextLine();
				
				
				
				break;
			case 3:
				// quit
				System.out.println("Goodbye!");
				break mainLoop;
			default:
				// invalid selection
				System.out.println("Not a valid selection, please try again.");
			}
		}
		log.trace("Ending start()");
	}
	
	private int startMenu() {
		log.trace("called startMenu()");
		System.out.println("Welcome to BankApp!");
		System.out.println("What would you like to do?");
		System.out.println("\t1. Login");
		System.out.println("\t2. Register");
		System.out.println("\t3. Quit");
		int selection = select();
		log.trace("Start menu returning selection: "+selection);
		return selection;
	}
	private int memberMenu() {
		System.out.println("What would you like to do?");
		System.out.println("\t1. Check Balance");
		System.out.println("\t2. Deposit");
		System.out.println("\t3. Make Withdrawal");
		System.out.println("\t4. Logout");
		return select();
	}
	private int associateMenu() {
		log.trace("called associate()");
		System.out.println("What would you like to do?");
		System.out.println("\t1. View Own Account Balance");
		System.out.println("\t2. Deposit Funds");
		System.out.println("\t3. Withdraw Funds");
		System.out.println("");
		System.out.println("\t4. View Members' Account Balance");
		System.out.println("\t5. Log Out");
		int selection = select();
		log.trace("Banker menu returning selection: "+selection);
		return selection;
	}
	
	private void member() {
		log.trace("called member()");
		memberLoop: while(true) {
			switch(memberMenu()) {
			case 1:
				// view balance
				System.out.println("You have " + loggedUser.getFunds() + " in your account.");				
				break;
			case 2:
				// make a deposit
				System.out.println("Enter amount you would like to deposit?");
				
				newFunds = fundsChange();
				us.Deposit(loggedUser, newFunds);
				log.trace("Deposited "+newFunds+" to balance");
				System.out.println("Your new balance is " + loggedUser.getFunds() + ".");
				break;
			
			case 3:
				// make a withdrawal
				System.out.println("Enter amount you would like to withdraw?");
				
				newFunds = fundsChange();
				
				us.Withdraw(loggedUser, newFunds);
				log.trace("Withdrew "+newFunds+" from balance");
				System.out.println("Your new balance is " + loggedUser.getFunds() + ".");
				break;	
			case 4:
				//Log out
				loggedUser = null;
				break memberLoop;
			
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}
	
	
	private void associate() {
		associateLoop: while(true) {
			switch(associateMenu()) {
			case 1:
				// view associate account
				System.out.println("You have " + loggedUser.getFunds() + " available.");
				break;
			case 2:
				// deposit
				System.out.println("Enter the amount you would like to deposit: ");
				
				newFunds = fundsChange();
				
				us.Deposit(loggedUser, newFunds);
				log.trace("Deposited "+newFunds+" to balance");
				System.out.println("Your new available balance is " + loggedUser.getFunds() + ".");
				break;
			case 3:
				// withdraw
				System.out.println("Enter the amount you would like to withdraw: ");
				
				newFunds = fundsChange();
				
				us.Withdraw(loggedUser, newFunds);
				log.trace("Withdrew "+newFunds+" from balance");
				System.out.println("Your new balance is " + loggedUser.getFunds() + ".");
				break;
			case 4:
				// view members' balances
				System.out.println("Enter the member username whose account you'd like to view: ");
				
				String inputUser = scan.nextLine();
				
				boolean exists = us.checkIfExists(inputUser); 
				if(!exists) {
					System.out.println("User does not exist.");
				}else {
					User u = us.login(inputUser);
					System.out.println("User "+inputUser+" has "+u.getFunds()+" in their account.");
					log.trace("Viewed user balance: "+inputUser);
				}
				break;
			
			case 5:
				// log out
				loggedUser = null;
				break associateLoop;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
		
			
		}
	
	
	
	private int select() {
		int selection;
		try {
			selection = Integer.parseInt(scan.nextLine());
		} catch(Exception e) {
			selection = -1;
		}
		//log
		return selection;
	}
	
	// reading funds from deposit/withdrawal
			private long fundsChange() {
				long newFunds;
				try {
					newFunds = Long.parseLong(scan.nextLine());
				} catch(Exception e) {
					newFunds = 0;
				}
				
				return newFunds;
			}
		

}