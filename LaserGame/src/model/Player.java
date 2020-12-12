package model;

public class Player {
	
	
	
	private String nickname;
	private int score;

	public Player(String nick, int sc) {
		nickname = nick;
		score = sc;
		
	}

	public String getNickname() {
		return nickname;
	}

	public int getScore() {
		return score;
	}

	
}
