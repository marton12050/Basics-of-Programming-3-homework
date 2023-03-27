package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Board extends JPanel implements KeyListener{

	Window window;
	
	private final int BlockSize = 30;
	final int boardBlocksWidth = 10;
	final int boardBlocksHeight = 22;

	private int[][] board = new int [boardBlocksHeight][boardBlocksWidth];
	
	private int[][][] alakzatok = {{{1,1,1,1}},
	{{1,1},{1,1}},
	{{1,1,1},{0,1,0}},
	{{0,1,1},{1,1,0}},
	{{1,1,1},{1,0,0}},
	{{1,1,0},{0,1,1}},
	{{1,1,1},{0,0,1}}
	};
	private Alakzat currentAlakzat;
	private Alakzat nextAlakzat;
	
	private Timer timer;
	private final int speed = 25;
	
	private boolean paused = false;
	
	Color colors[] = {new Color(0, 0, 0), new Color(204, 102, 102),
	            new Color(102, 204, 102), new Color(102, 102, 204),
	            new Color(204, 204, 102), new Color(204, 102, 204),
	            new Color(102, 204, 204), new Color(218, 170, 0)
	   };
	 
	private boolean GameOver;
	private int score;
	JLabel scoreLabel;
	
	public Board(Window  wind) {
		
		window = wind;
		
		
		timer = new Timer(speed, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
				repaint();
			} 
		});
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(GameOver)
			return;
		currentAlakzat.drawUpdate(g);
		
		for(int y = 0; y < board.length;y++) {
			for(int x = 0;x < board[y].length;x++) {
				if(board[y][x] != 0) {
					g.setColor(colors[board[y][x]-1]);
					g.fillRect(x*BlockSize, y*BlockSize, BlockSize, BlockSize);
				}
			}
		}
		g.setColor(new Color(255, 255, 255));
		g.fillRect(310 , 25, 150, 150);
		
		
		for(int y = 0 ; y < nextAlakzat.getKordin().length; y++) {
			for(int x = 0; x < nextAlakzat.getKordin()[y].length; x++) {
				if(nextAlakzat.getKordin()[y][x] != 0) {
					g.setColor(nextAlakzat.getColor());
					g.fillRect(310 + x * BlockSize + (150 - nextAlakzat.getKordin()[0].length*BlockSize) / 2 , 70 + y * BlockSize, BlockSize, BlockSize);
					
				}
			}
		}
		
		g.setColor(new Color(0, 0, 0));
		
		for(int i = 0;i < boardBlocksWidth;i++) {
			g.drawLine(i*BlockSize,0 ,i*BlockSize, boardBlocksHeight*BlockSize);
		}
		for(int i = 1;i < boardBlocksHeight+1;i++) {
			g.drawLine(0, i*BlockSize, boardBlocksWidth*BlockSize, i*BlockSize);
		}
		
	}
	
	public void update() {
		currentAlakzat.update();
		if(GameOver) {
			timer.stop();
			GameOverForm();
		}
	}
	
	public int getBlockSize() {
		return BlockSize;
	}

	public int getBoardBlocksWidth() {
		return boardBlocksWidth;
	}
	public int getboardBlocksHeight() {
		return boardBlocksHeight;
	}
	
	public void generateNewShape() {
		
		Random r = new Random();
		int temp = r.nextInt(8);
		

		currentAlakzat = nextAlakzat; // jelenlegi az új alakzat
		
		for(int y = 0 ; y < currentAlakzat.getKordin().length; y++) {
			for(int x = 0; x < currentAlakzat.getKordin()[y].length; x++) {
					if(currentAlakzat.getKordin()[y][x] != 0) { // ha nem tudja letenni az új alakzatot akkor vége a játéknak
						
						if(board[y][x + 3] != 0) {
							GameOver = true;
						}
					}
						
				}
			}
		
		nextAlakzat = new Alakzat(alakzatok[(int)(Math.random()*alakzatok.length)],this,colors[temp],temp+1);
	}
	
	public int[][] getBoard(){
		return board;
	}
	
	public void checkLine(int yStart) { //A lerakott alakzat aljától felfelé kezdi nézzni a sorokat mivel alatt nem lehet teli sor 
		yStart--;	
		
		for(int y = yStart; y > 0; y--) {
			int szaml = 0;
			for(int x = 0; x < boardBlocksWidth; x++) {
				
				if(board[y][x] != 0)
					szaml++;
				
				board[yStart][x] = board[y][x]; // ha nem volt teli sor akkor ugyan az marad ha pedig volt akkor yStart nem csökken ezért ezért felülrõl lejebb kerülnek a blockok
			}
			if(szaml < boardBlocksWidth)
				yStart--;
		}
		
		score += yStart * yStart;							//A pontokat a töröl sor számának a négyzetével adjuk
		scoreLabel.setText(String.valueOf("Ennyi pontod van: " + score));
		
	}
	public void pause() {
		timer.stop();
		paused = true;
	}
	public void unpause() {
		timer.start();
		paused = false;
	}
	
	public void startGame(){
		this.removeAll();
		stopGame();
		GameOver = false;
		paused = false;
		
		nextAlakzat = new Alakzat(alakzatok[(int)(Math.random()*alakzatok.length)],this,colors[1],2);
		generateNewShape();
		
		this.setLayout(null);
		
		JLabel labelEzkov  = new JLabel();
		labelEzkov.setText("Ez a következõ alakzat");
		Dimension size = labelEzkov.getPreferredSize();
		labelEzkov.setBounds(315, 6, size.width, size.height);
		
		
		scoreLabel  = new JLabel();
		scoreLabel.setText("Ennyi pontod van: " + String.valueOf(score));
		size = scoreLabel.getPreferredSize();
		scoreLabel.setBounds(310, 200, size.width + 30, size.height + 30);
		
	    this.add(labelEzkov);
		this.add(scoreLabel);
		
		timer.start();
		
	}
	public void stopGame(){
			timer.stop();
			score = 0;
			
			for(int y = 0; y < board.length; y++)
			{
				for(int x = 0; x < board[y].length; x ++)
				{
					board[y][x] = 0;
				}
			}
		
	}
	
	public void GameOverForm() {
		this.removeAll();
		this.setLayout(null);
		JLabel user_label = new JLabel("Add meg a neved ,ha van elég pontod megfogsz jeleni a dicsõségtáblán:");
		Dimension size = user_label.getPreferredSize();
		user_label.setBounds(50, 30, size.width, size.height);
		
		JTextField nameJTF  = new JTextField(15);
		size = nameJTF.getPreferredSize();
		nameJTF.setBounds(150, 50, size.width, size.height);
		
		JButton submit = new JButton("Bead");
		size = submit.getPreferredSize();
		submit.setBounds(200, 70, size.width, size.height);
		
		this.add(submit);
		this.add(user_label);
		this.add(nameJTF);
		
		submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				window.addToScoreboard(nameJTF.getText(),score);
			}
        });
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			currentAlakzat.setDeltaX(-1);
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			currentAlakzat.setDeltaX(1);
		if(e.getKeyCode() == KeyEvent.VK_UP)
			currentAlakzat.rotate();
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			currentAlakzat.dropdown();
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(paused)
				unpause();
			else
				pause();
		}
			
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			currentAlakzat.stopdropdown();
		
	}
	
	
	
	//TESZTEK MIATT
	public boolean ispaused() {
		return !timer.isRunning();
	}
	public int getScore() {
		return score;
	}
	public Alakzat getcurrAlak() {
		return currentAlakzat;
	}
	public Alakzat  getnextAlak() {
		return nextAlakzat;
	}
	
}
