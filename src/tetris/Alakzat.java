package tetris;

import java.awt.Color;
import java.awt.Graphics;

public class Alakzat {

	private int[][] kordin;
	private Board board;
	
	Color color;
	int colorId;
	
	private int curX,curY;
	private int deltaX;
	
	private int timer = 0;
	private int timerLimit = 15;
	private boolean collis;
	private boolean canMoveX;
	
	public Alakzat(int[][] kordin,Board board,Color c,int cId) {
		this.kordin = kordin;
		this.board = board;
		color = c;
		colorId = cId;
	    
		collis = false;
		canMoveX = false;
		deltaX = 0;
		
	    curX = 3;curY = 0;
	}
	
	public void update() {
		
		if(collis) {
			for(int y = 0; y < kordin.length;y++) {
				for(int x = 0;x < kordin[y].length;x++) {
					if(kordin[y][x] != 0) {
						board.getBoard()[curY + y][curX + x] = colorId;
					}
				}
			}
			board.checkLine(curY + kordin.length);
			board.generateNewShape();
		}
		
		if(!(curX + deltaX + kordin[0].length > board.getBoardBlocksWidth()) && !(curX + deltaX < 0)) { //balra akkor tudunk ha az poziciója és az éppen balra tolás nem lóg ki a képbõl szóval nem negatív
																										//jobbra ugyan ez és még az alakzat hossza mivel az alakzat bal szélétõl számoljuk ez legyen kisebb mint a pálya szélesége
			for(int y = 0 ; y < kordin.length; y++) {
				for(int x = 0; x < kordin[y].length; x++) {
					if(kordin[y][x] != 0)
						if(board.getBoard()[y + curY][x + curX + deltaX ] != 0)
							canMoveX = false;
				}
			}
			
			if(canMoveX)
				curX += deltaX;																				
		}
		
		if(!(curY + 1 + kordin.length > board.boardBlocksHeight) ) { // pálya aljánál ne essen lejebb
			
				if(timer >= timerLimit) { // igy nem csak akkor tudunk jobbra,balra menni ha egyet esik le az alakzatunk
					
					for(int y = 0 ; y < kordin.length; y++) {
						for(int x = 0; x < kordin[y].length; x++) {
							if(kordin[y][x] != 0)
								if(board.getBoard()[y + curY + 1][x + curX] != 0) // ha van alatta block akkor collsion bekapcsol és nem megy többet le
									collis = true;
						}
					}
					if(!collis)
						curY++; // egyet lejjebb esik
					timer = 0;
				}
				
			
			timer++;
		}else {
			collis = true;
		}
		
		deltaX = 0;
		canMoveX = true;
	}
	
	public void drawUpdate(Graphics g) {
		for(int y = 0 ; y < kordin.length; y++) {
			for(int x = 0; x < kordin[y].length; x++) {
				if(kordin[y][x] != 0) {
					g.setColor(color);
					g.fillRect(x*board.getBlockSize() + curX*board.getBlockSize(), y*board.getBlockSize() + curY*board.getBlockSize(), board.getBlockSize(), board.getBlockSize());
					
				}
			}
		}
		g.setColor(new Color(0, 0, 0));
	}
	
	
	public void setDeltaX(int delt) {
		deltaX = delt;
	}
	
	public int[][] getKordin(){
		return kordin;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void rotate() { //alazkat forgatása
		
		if(collis)
			return;
		
		if(curX + kordin.length > board.getBoardBlocksWidth() || curY + kordin[0].length > board.getboardBlocksHeight()) // ha forgatás hatására kimegy a pályáról akkor nem forgat
			return;
		
		int[][] kordinRotated = new int[kordin[0].length][kordin.length];
		for(int i = 0;i < kordinRotated.length;i++) {
			for(int j = 0;j < kordinRotated[i].length;j++) {
				kordinRotated[i][j] = kordin[j][Math.abs(i-kordinRotated.length)-1];
				
			}
		}
		
		for(int y = 0;y < kordinRotated.length;y++) {
			for(int x = 0;x < kordinRotated[0].length;x++) {
				if(board.getBoard()[y + curY][x + curX] != 0)  // ha elforgatás hatására egy foglalt helyre megy akkor nem forgatunk
					return;
				
			}
		}
		
		kordin = kordinRotated;
		
	}
	
	public void dropdown() { //gyorsabban esik lefelé
		timerLimit = 4;
	}
	public void stopdropdown() { //normál sebességgel esik lefelé
		timerLimit = 15;
	}
	
	
	//TESZT MIATT
	public int getcurY() {
		return curY;
	}
	public int getcurX() {
		return curX;
	}
}
