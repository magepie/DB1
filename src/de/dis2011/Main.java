package de.dis2011;

import de.dis2011.data.Makler;
import de.dis2011.data.Contract;

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
		final int MENU_OWNER = 6;
		final int QUIT = 3;
		final int MAKLER_MGMT_PW = 98765;
		
		//Creating Main menu
		Menu mainMenu = new Menu("Main Menu");
		mainMenu.addEntry("Agent account management", MENU_MAKLER);
		mainMenu.addEntry("Estate management", MENU_ESTATE);
		mainMenu.addEntry("Contract management", MENU_CONTRACT);
		mainMenu.addEntry("Create new owner",MENU_OWNER);
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
				case MENU_OWNER:
					newOwner();
					break;
				case MENU_CONTRACT:
					showContractMenu();
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
				case EDIT_MAKLER:
					editMakler();
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
	 * Removes a makler after the user inserts the correct data
	 */
	public static void deleteMakler() {
		Makler m = new Makler();
		
		int id  = Integer.parseInt((FormUtil.readString("Enter agent ID")));
		m.remove(id);
		
		System.out.println("Makler with ID "+ id +" was removed.");
	}
	
	public static void showContractMenu(){

		final int NEW_RENT=0;
		final int NEW_SALE=1;
		final int BACK=2;


		Menu contractMenu= new Menu("Create new Contract");
		contractMenu.addEntry("New Rent Contract",NEW_RENT);
		contractMenu.addEntry("New Sale Contract",NEW_SALE);
		contractMenu.addEntry("Return to the main menu", BACK);


		while(true){
			int response = contractMenu.show();

			switch(response){
				case NEW_RENT:
					newRentContract();
					break;
				case NEW_SALE:
					newSaleContract();
					break;
				case BACK:
					return;

			}
		}

	}

	public static void newOwner() {
		Contract c = new Contract();

		c.setFirstname(FormUtil.readString("First name"));
		c.setLastName(FormUtil.readString("Last name"));
		c.setAddress(FormUtil.readString("Address"));
		c.createOwner();

	}

	public static void newRentContract(){

		Contract c= new Contract();

		c.setContractdate(FormUtil.readString("Date"));
		c.setSettlemtnPlace(FormUtil.readString("Settlement Place"));
		c.setApartmentid(FormUtil.readInt("Apartment id"));
		c.setOwnerID(FormUtil.readInt("Owner ID"));
		c.setDuration(FormUtil.readInt("Duration"));
		c.setExtracosts(FormUtil.readInt("Extra costs"));
		c.setStartDate(FormUtil.readString("Start Date"));
		c.createContract();
		c.createTenancy();

	}

	public static void newSaleContract(){
		Contract c= new Contract();

		c.setContractdate(FormUtil.readString("Date"));
		c.setSettlemtnPlace(FormUtil.readString("Settlement Place"));
		c.setHouseid(FormUtil.readInt("House ID"));
		c.setOwnerID(FormUtil.readInt("Owner ID"));
		c.setInstallments(FormUtil.readInt("Installments"));
		c.setInterestrate(FormUtil.readInt("Interest Rate"));
		c.createContract();
		c.createPurchase();
	}
	
	/**
	 * Edits the makler data after the user inserts the correct data
	 */
	public static void editMakler() {
		
		final int CHG_NAME = 1;
		final int CHG_ADDR = 2;
		final int CHG_LOGIN = 3;
		final int CHG_PW = 4;
		
		Makler m = new Makler();
		
		int id  = Integer.parseInt((FormUtil.readString("Enter agent ID")));
		m.read(id);
		System.out.println("ID: "+ id +"|| Name: " + m.getName() + "|| Address: " + m.getAddress() + "\r\n|| Login: " + m.getLogin() + "|| Password: " + m.getPassword());
		System.out.println("Enter " + CHG_NAME +" to change name, " + CHG_ADDR +" to change address, " + CHG_LOGIN +" to change login, "  + CHG_PW +" to change password.");
		int choice = Integer.parseInt((FormUtil.readString("")));
		switch(choice)
		{
		case CHG_NAME:
			m.setName(FormUtil.readString("Enter new name: "));
			break;
		case CHG_ADDR:
			m.setAddress(FormUtil.readString("Enter new address: "));
			break;
		case CHG_LOGIN:
			m.setLogin(FormUtil.readString("Enter new login: "));
			break;
		case CHG_PW:
			m.setPassword(FormUtil.readString("Enter new password: "));
			break;
		default:
			System.out.println("Failed to reconfigure.");
			return;
		}
		m.save();
		System.out.println("Makler with ID "+ id +" was reconfigured.");
	}
}
