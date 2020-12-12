package model;

public class Player {
	
	private Player izq;
	private Player der;
	
	private String nickname;
	private int score;

	public Player(String nick, int sc) {
		nickname = nick;
		score = sc;
		izq = null;
		der = null;
		
	}

	public Player getIzq() {
		return izq;
	}

	public Player getDer() {
		return der;
	}

	public void setIzq(Player izq) {
		this.izq = izq;
	}

	public void setDer(Player der) {
		this.der = der;
	}

	public String getNickname() {
		return nickname;
	}

	public int getScore() {
		return score;
	}

	public String toString() {
		return nickname +" "+score;
	}
	
}
