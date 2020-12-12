package model;

public class Node {
	
	private int row;
	private int col;
	
	private Node next;
	private Node prev;
	private Node up;
	private Node down;
	
	public Node(int r, int c) {
		row = r;
		col = c;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public char getNameCol() {
		return (char)('A' + col);
	}

	public Node getNext() {
		return next;
	}

	public Node getPrev() {
		return prev;
	}

	public Node getUp() {
		return up;
	}

	public Node getDown() {
		return down;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public void setUp(Node up) {
		this.up = up;
	}

	public void setDown(Node down) {
		this.down = down;
	}
	
	public String toString() {
		return "[ ]"; //(" + row +","+col+")
	}
}