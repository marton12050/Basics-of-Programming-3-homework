package tetris;

import java.io.Serializable;

public class PlayerData implements Serializable{
	
	private String name;
	
	int score;

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	
	public PlayerData(String name, int score) {
        this.name = name;
        this.score = score;
    }
	
	
	
}
