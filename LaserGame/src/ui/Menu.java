package ui;

import java.util.Scanner;

public class Menu {
	
	private Scanner sc = new Scanner(System.in);
	
	public Menu() {
		
	}
	
	public void start() {
		int option;
		option = Integer.parseInt(sc.nextLine());
		manageMenu(option);
		if(option != 3) {
			start();
		}		
	}	
	
	public void manageMenu(int o) {
		switch(o) {
		case 1:
			play();
			break;
		case 2:
			laderboard();
			break;
		case 3:
			goodbye();
			break;
		
		default:
			System.out.println("Insert a valid option");
		}
	}
	
	public void play() {
		
	}

	
	
	
	public void showMenu() {
		String msg = "\n";
		msg += " 1) Play. \n";
		msg += " 2) Laderboard. \n";
		msg += " 3) Exit.\n";
		System.out.println(msg);
	}
	
	
	

}
