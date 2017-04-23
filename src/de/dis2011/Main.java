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
	 * Zeigt das Hauptmenü
	 */
	public static void showMainMenu() {
		//Main menu options
		final int MENU_MAKLER = 0;
		final int QUIT = 1;
		
		//Creating Main menu
		Menu mainMenu = new Menu("Main Menu");
		mainMenu.addEntry("Makler-Administration", MENU_MAKLER);
		mainMenu.addEntry("Exit", QUIT);
		
		//Processing Input
		while(true) {
			int response = mainMenu.show();
			
			switch(response) {
				case MENU_MAKLER:
					showMaklerMenu();
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
		final int BACK = 1;
		
		//Makler administration menu
		Menu maklerMenu = new Menu("Makler-Administration");
		maklerMenu.addEntry("New Makler", NEW_MAKLER);
		maklerMenu.addEntry("Return to the main menu", BACK);
		
		//Processing input
		while(true) {
			int response = maklerMenu.show();
			
			switch(response) {
				case NEW_MAKLER:
					newMakler();
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
}
