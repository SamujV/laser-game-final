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
			String[] parts = data.split(" "); // unica estructura de datos en el programa

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
			lm = new LinkedMatrix(rows, columns, mirrors);
			lm.createMirrors();
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
		System.out.println("\nThere can be no more mirrors than cells");
		System.out.println("Insert number of mirrors");
		mirrors = Integer.parseInt(sc.nextLine());
		if (mirrors > rows*columns) {
			mirrorRectifier();
		}		
	}

	public void manageMatrix() {
		
		System.out.println( "\n" + nickname + ":" + " " + mirrors + " mirrors remaining");		
		System.out.println(lm.toString1());
		
		String line = sc.nextLine();

		if (line.equalsIgnoreCase("menu")) {
			calculateScore();
			start();
		}else if (line.charAt(0) == 'L') {
			int row = line.charAt(1) - '0';
			String column =   Character.toString(line.charAt(2));
			String dir = Character.toString(line.charAt(3));
			locate(row, column, dir);
			
		}else {
			int row = line.charAt(0) - '0';
			String col =  Character.toString(line.charAt(1));
			if (line.length() > 2) {
				String dir = Character.toString(line.charAt(2));
				shootCorner(row, col, dir);
					
			}else {
				shoot(row, col);	
			}			
		}
	}
	
	private void locate(int row, String column, String dir) {
		
		char c = Character.toUpperCase(column.charAt(0));
		
		int col = 'A' - c;
		System.out.println(col);
		
		lm.locate(row, col, dir);
		
		
		manageMatrix();
	}

	private void shoot(int row, String col) {
		if (row == 1 || row == rows) { 
			
			
			
		}	
		
		manageMatrix();
	}

	private void shootCorner(int row, String col, String dir) {
	
		
		
		manageMatrix();
	}

	public void calculateScore() {		
		if (lm.getFoundedMirrors() == mirrors) {
			score = 100;
		}else {
			score = (100*lm.getFoundedMirrors())/mirrors; // se saca por regla de 3
		}
		addScorePlayer();
	}

	public void addScorePlayer() {
		Player newP = new Player(nickname, score);
		//		Player newP1 = new Player("A", 4);
		//		Player newP2 = new Player("B", 2);
		//		Player newP3 = new Player("C", 6);
		//		Player newP4 = new Player("D", 1);	
		//		Player newP5 = new Player("E", 3);
		//		Player newP6 = new Player("F", 5);
		//		Player newP7 = new Player("G", 7);
		addScorePlayer2(newP, root);
		//		addScorePlayer2(newP1, root);
		//		addScorePlayer2(newP2, root);
		//		addScorePlayer2(newP3, root);
		//		addScorePlayer2(newP4, root);
		//		addScorePlayer2(newP5, root);
		//		addScorePlayer2(newP6, root);
		//		addScorePlayer2(newP7, root);
	}

	public void addScorePlayer2(Player newP, Player root1) {

		if (root1 == null) {
			root = newP;
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
		laderboardM();
		if (root != null) {
			inOrden(root);	
		}else {
			System.out.println("Play a game first");
		}
	}



	public void inOrden(Player root1) {
		if (root1 != null) {
			inOrden(root1.getIzq());
			System.out.println(root1.getNickname() + ": " + root1.getScore());
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
		msg += "======================================================================\n";
		System.out.println(msg);
	}

	public void goodbye() {
		String msg = "";
		msg += "======================================================================\n";
		msg += "============================== GOODBYE ===============================\n";
		msg += "======================================================================";
		System.out.println(msg);
	}
	public void laderboardM() {
		String msg = "";
		msg += "======================================================================\n";
		msg += "============================= LADERBOARD =============================\n";
		msg += "======================================================================\n";
		System.out.println(msg);
	}
}
