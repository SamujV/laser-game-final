package model;

public class Node {
	
	private int row;
	private int col;
	
	private Node next;
	private Node prev;
	private Node up;
	private Node down;
	private boolean isMirror;
	private String direction; // (/ ó \)
	
	public Node(int r, int c) {
		row = r;
		col = c;
	}
	public void setMirror(boolean isMirr) {
		isMirror = isMirr;
	}
	public boolean getIsMirror() {
		return isMirror;
	}
	
	public void setDirection(String dir) {
		direction = dir;
	}
	public String getDirection() {
		return direction;
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