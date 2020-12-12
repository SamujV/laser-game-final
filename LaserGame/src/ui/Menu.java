package ui;
import java.util.Scanner;

import customExceptions.NegativeNumberException;
import model.LinkedMatrix;
import model.Player;

public class Menu {

	private Scanner sc = new Scanner(System.in);
	private String nickname;
	private int score;
	private int rows;
	private int columns;
	private int mirrors;
	private LinkedMatrix lm;
	private Player root;
	public Menu() {
		root = null;
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
			System.out.println("Insert your nickname, number of columns, number of rows and number of mirrors separated by spaces ");
			String data = sc.nextLine();			
			String[] parts = data.split(" ");

			nickname = parts[0];
			columns = Integer.parseInt(parts[1]);
			rows = Integer.parseInt(parts[2]);
			mirrors = Integer.parseInt(parts[3]);

			if(columns < 0 || rows < 0 || mirrors < 0) {
				throw new NegativeNumberException();
			}

			if(mirrors > rows*columns) {				
				mirrorRectifier();
			}			

			manageMatrix();

		} catch (ArrayIndexOutOfBoundsException a) {
			System.out.println("Enter all requested data ");
			play();
		} catch(NumberFormatException n) {
			System.out.println("Insert a valid format");
			play();
		} catch (NegativeNumberException neg) {
			System.out.println(neg.getMessage());
			play();
		}
	}

	public void mirrorRectifier() {
		System.out.println("There can be no more mirrors than cells");
		System.out.println("Insert number of mirrors");
		mirrors = Integer.parseInt(sc.nextLine());
		if (mirrors > rows*columns) {
			mirrorRectifier();
		}		
	}

	public void manageMatrix() {
		lm = new LinkedMatrix(rows, columns, mirrors);
		System.out.println( "\n" + nickname + ":" + " " + mirrors + " mirrors remaining");
		System.out.println(lm);

		String line = sc.nextLine();

		if (line.equalsIgnoreCase("menu")) {
			calculateScore();
			start();
		}
		if (line.charAt(0) == 'L') {
			int row = line.charAt(1) - '0'  ;
			String column =   Character.toString(line.charAt(2));
			String dir = Character.toString(line.charAt(3));
			System.out.println(row);
			System.out.println(column);
			System.out.println(dir);
		}



	}

	public void calculateScore() {		
		if (lm.getFoundedMirrors() == mirrors) {
			score = 100;

		}else {
			score = (100*lm.getFoundedMirrors())/mirrors; // se saca por regla de 3
		}	
	}

	public void addScorePlayer() {
		Player newP = new Player(nickname, score);		
		addScorePlayer2(newP, root);
	}

	public void addScorePlayer2(Player newP, Player root1) {

		if (root1 == null) {
			root1 = newP;
		}else {
			if (newP.getScore()<= root1.getScore()) {
				if (root1.getIzq() == null) {
					root1.setIzq(newP);
				}else {
					addScorePlayer2(newP, root1.getIzq());
				}
			}else {
				if (root1.getDer() == null) {
					root1.setDer(newP);
				}else {
					addScorePlayer2(newP, root1.getDer());
				}
			}
		}
	}




	public void laderboard() {

	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void inOrden(Player root1) {
		if (!isEmpty()) {
			inOrden(root1.getIzq());
			System.out.println(root1.getScore());
			inOrden(root1.getDer());
		}
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
