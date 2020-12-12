package ui;
import java.util.Scanner;

public class Menu {

	private Scanner sc = new Scanner(System.in);
	private int rows;
	private int columns;
	private int mirrors;
	public Menu() {

	}

	public void start() {
		try {
			int option;
			showMenu();
			option = Integer.parseInt(sc.nextLine());
			manageMenu(option);
			if(option != 3) {
				start();
			}
		}catch(NumberFormatException e) {
			System.out.println("Insert a valid format");
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
		playMessage();
		
		try {
			System.out.println("Insert number of rows");
			rows = Integer.parseInt(sc.nextLine());
			System.out.println("Insert number of columns");
			columns = Integer.parseInt(sc.nextLine());
			System.out.println("Insert number of mirrors");
			mirrors = Integer.parseInt(sc.nextLine());
			if(mirrors > rows*columns) {				
				mirrors = mirrorRectifier();
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Insert a valid option");
			play();
		}

	}
	public int mirrorRectifier() {
		int mirrors = 0;
		System.out.println("There can be no more mirrors than cells");
		System.out.println("Insert number of mirrors");
		mirrors = Integer.parseInt(sc.nextLine());
		if (mirrors > rows*columns) {
			mirrorRectifier();
		}		
		return mirrors;
	}
	
	
	public void laderboard() {

	}


	public void showMenu() {
		String msg = "\n";
		msg += " 1) Play. \n";
		msg += " 2) Laderboard. \n";
		msg += " 3) Exit.\n";
		System.out.println(msg);
	}

	public void playMessage() {
		String msg = "";
		msg += "======================================================================\n";
		msg += "=========================== LET'S PLAY ===============================\n";
		msg += "======================================================================\n";
		System.out.println(msg);
	}
	public void showWelcome() {
		String msg = "";
		msg += "======================================================================\n";
		msg += "==================== WELCOME TO THE LASER GAME =======================\n";
		msg += "======================================================================";
		System.out.println(msg);
	}

	public void goodbye() {
		String msg = "";
		msg += "======================================================================\n";
		msg += "============================== GOODBYE ===============================\n";
		msg += "======================================================================";
		System.out.println(msg);
	}
}
