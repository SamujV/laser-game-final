package model;

import java.util.Random;

//credit to seyerman to given the structure to do the matrix using linked list
public class LinkedMatrix {

	private Node first;
	private int rows;
	private int cols;
	private int mirrors;
	private int mirrorsAdded;
	private int mirrFounded;
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

		int randomRow = random.nextInt(rows + 1);
		int randomCol = random.nextInt(cols + 1);
		String dire = (random.nextInt(2) == 1) ? LEFT_TILTED_MIRROR : RIGHT_TILTED_MIRROR;
		moveRow(randomRow, randomCol, dire, first);
	}

	private void moveRow(int randomRow, int randomCol,String dire , Node current) {
		
		if (current != null && current.getRow() < randomRow) {
			current = current.getDown();
			moveRow(randomRow, randomCol, dire, current);
		}else if (current.getRow() == randomRow) {
			moveCol(randomCol, dire, current);	
		}
		

	}

	private void moveCol(int randomCol, String dire, Node current) {
		if (current.getCol() < randomCol) {
			current = current.getNext();
			moveCol(randomCol, dire, current);
		}else if (current.getIsMirror() && mirrorsAdded < mirrors) {
			createMirrors();
		}else if(mirrorsAdded < mirrors) {
			current.setDirection(dire);
			current.setMirror(true);
			mirrorsAdded++;
			createMirrors();
				
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
			msg = current.toString();
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