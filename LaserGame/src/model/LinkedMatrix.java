package model;

public class LinkedMatrix {

	private Node first;
	private int rows;
	private int cols;

	public LinkedMatrix(int m, int n) {
		rows = m;
		cols = n;
		generateMatrix();
	}

	private void generateMatrix() {
		//System.out.println("vamos a crear la matriz");
		first = new Node(0,0);
		//System.out.println("se crea el first");
		createRow(0,0,first);
	}

	private void createRow(int i, int j, Node currentFirstRow) {
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

	public String toString() {
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

}