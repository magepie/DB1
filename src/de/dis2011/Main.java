package de.dis2011;

import de.dis2011.data.Makler;

/**
 * Main class
 */
public class Main {
	/**
	 * Starts the application
	 */
	public static void main(String[] args) {
		showMainMenu();
	}
	
	/**
	 * Shows main menu
	 */
	public static void showMainMenu() {
		//Main menu options
		final int MENU_MAKLER = 0;
		final int MENU_ESTATE = 1;
		final int MENU_CONTRACT = 2;
		final int QUIT = 3;
		final int MAKLER_MGMT_PW = 98765;
		
		//Creating Main menu
		Menu mainMenu = new Menu("Main Menu");
		mainMenu.addEntry("Agent account management", MENU_MAKLER);
		mainMenu.addEntry("Estate management", MENU_ESTATE);
		mainMenu.addEntry("Contract management", MENU_CONTRACT);
		mainMenu.addEntry("Exit", QUIT);
		
		//Processing Input
		while(true) {
			int response = mainMenu.show();
			
			switch(response) {
				case MENU_MAKLER:
					int pw = Integer.parseInt((FormUtil.readString("Enter agent management password")));
					if (pw == MAKLER_MGMT_PW)
					{
						showMaklerMenu();
					}
					else
					{
						System.out.println("Password incorrect");
						break;
					}
					break;
				case QUIT:
					return;
			}
		}
	}
	
	/**
	 * Shows the Makler administration
	 */
	public static void showMaklerMenu() {
		//Main menu options
		final int NEW_MAKLER = 0;
		final int EDIT_MAKLER = 1;
		final int DELETE_MAKLER = 2;
		final int BACK = 3;
		
		//Makler administration menu
		Menu maklerMenu = new Menu("Agent account management");
		maklerMenu.addEntry("New agent", NEW_MAKLER);
		maklerMenu.addEntry("Edit agent", EDIT_MAKLER);
		maklerMenu.addEntry("Remove agent", DELETE_MAKLER);
		maklerMenu.addEntry("Return to the main menu", BACK);
		
		//Processing input
		while(true) {
			int response = maklerMenu.show();
			
			switch(response) {
				case NEW_MAKLER:
					newMakler();
					break;
				case DELETE_MAKLER:
					deleteMakler();
					break;
				case BACK:
					return;
			}
		}
	}
	
	/**
	 * Sets a new makler after the user inserts the correct data
	 */
	public static void newMakler() {
		Makler m = new Makler();
		
		m.setName(FormUtil.readString("Name"));
		m.setAddress(FormUtil.readString("Address"));
		m.setLogin(FormUtil.readString("Login"));
		m.setPassword(FormUtil.readString("Password"));
		m.save();
		
		System.out.println("Makler with ID "+m.getId()+" was created.");
	}
	
	/**
	 * Sets a new makler after the user inserts the correct data
	 */
	public static void deleteMakler() {
		Makler m = new Makler();
		
		int id  = Integer.parseInt((FormUtil.readString("Enter agent ID")));
		m.remove(id);
		
		System.out.println("Makler with ID "+ id +" was removed.");
	}
}
