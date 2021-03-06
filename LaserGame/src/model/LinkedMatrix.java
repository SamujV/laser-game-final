package model;

import java.util.Random;

//credit to seyerman to given the structure to do the matrix using linked list
public class LinkedMatrix {

	private Node first;
	private int rows;
	private int cols;
	private int mirrors;
	private int mirrorsAdded; // to insert mirrors on matrix
	private int mirrFounded;   // to calculate score
	public final String LEFT_TILTED_MIRROR = "\\";
	public final String RIGHT_TILTED_MIRROR = "/";

	public LinkedMatrix(int m, int n, int mirr) {
		rows = m;
		cols = n;
		mirrors = mirr;
		mirrorsAdded = 0;
		mirrFounded = 0;
		generateMatrix();
	}

	private void generateMatrix() {
		//System.out.println("vamos a crear la matriz");
		first = new Node(0,0);
		//System.out.println("se crea el first");
		createRow(0,0,first); 
	}

	private void createRow(int i, int j, Node currentFirstRow) { // crea la fila
		//System.out.println("en createRow con la fila " + i);
		createCol(i, j+1, currentFirstRow, currentFirstRow.getUp());
		if(i+1 < rows) {
			Node downFirstRow = new Node(i+1, j);
			downFirstRow.setUp(currentFirstRow);
			currentFirstRow.setDown(downFirstRow);
			createRow(i+1, j, downFirstRow);
		}
	}

	private void createCol(int i, int j, Node prev, Node rowPrev) { // llamado recursivo para crear parte de la fila
		if(j<cols) {
			//System.out.println(" 	en createCol con la columna "+ j  );
			Node current = new Node(i,j);
			current.setPrev(prev);
			prev.setNext(current);

			if(rowPrev != null) {
				rowPrev = rowPrev.getNext();
				current.setUp(rowPrev);
				rowPrev.setDown(current);				
			}
			createCol(i,j+1, current, rowPrev);
		}
	}

	public void createMirrors() { // random taked from: https://es.stackoverflow.com/questions/5390/como-generar-n%C3%BAmeros-aleatorios-dentro-de-un-rango-de-valores
		Random random = new Random(System.currentTimeMillis());

		int randomRow = random.nextInt(rows);// number between 0 and row - 1
		int randomCol = random.nextInt(cols);
		int randomm = random.nextInt(100);
		String dire;
		if (randomm < 50) {
			dire =LEFT_TILTED_MIRROR;
		}else {
			dire = RIGHT_TILTED_MIRROR;
		}
		moveCol(randomRow, randomCol, dire, first);
	}

	private void moveCol(int randomRow, int randomCol, String dire, Node current) {

		if (current.getNext() != null && current.getCol() < randomCol) {
			current = current.getNext();
			moveCol(randomRow, randomCol, dire, current);			
		}
		if (current.getCol() == randomCol) {
			moveRow(randomRow, dire, current);
		}
	}

	private void moveRow(int randomRow, String dire, Node current) {
		if (current.getDown() != null && current.getRow() < randomRow) {
			current = current.getDown();
			moveRow(randomRow, dire, current);
		}
		if (current.getRow() == randomRow) {
			if (current.getIsMirror() && mirrorsAdded < mirrors) {
				createMirrors();
			}
			if (mirrorsAdded < mirrors) {
				current.setSecretDirection(dire);
				current.setMirror(true);
				current.setFounded(true);
				mirrorsAdded++;
				createMirrors();
			}
		}
	}

	public void locate(int row, int column, String dir) {		

		moveColLocate(row, column, dir, first);



	}

	private void moveColLocate(int row, int col, String dire, Node current) {

		if (current.getNext() != null && current.getCol() < col) {
			current = current.getNext();
			moveCol(row, col, dire, current);			
		}else if (current.getCol() == col) {
			moveRowLocate(row, dire, current);
		}
	}

	private void moveRowLocate(int row, String dire, Node current) {
		if (current.getDown() != null && current.getRow() < row) {
			current = current.getDown();
			moveRow(row, dire, current);
		}
		if (current.getDown() != null && current.getRow() == row) {
			if (current.getIsMirror() && dire.equals(current.getSecretDirection())) {
				foundMirror();
				current.setDirection(current.getSecretDirection());
			}else {
				current.setDirection("h");
			}
		}
	}



	public String toString1() {
		String msg = "";
		msg = toStringRow(first);
		return msg;
	}

	private String toStringRow(Node firstRow) {
		String msg = "";
		if (firstRow != null) {
			msg = toStringCol(firstRow) + "\n";
			msg += toStringRow(firstRow.getDown());
		}
		return msg;
	}

	private String toStringCol(Node current) {
		String msg = "";
		if (current != null) {
			if (current.getIsMirror() && current.isFounded()) {
				msg = current.getSecretDirection();
			}else {
				msg = current.toString();
			}
			msg += toStringCol(current.getNext());
		}
		return msg;
	}

	public void foundMirror() {
		mirrFounded++;
	}

	public int getFoundedMirrors() {
		return mirrFounded;
	}



}