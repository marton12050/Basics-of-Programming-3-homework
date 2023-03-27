package junittest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import tetris.Board;
import tetris.Window;

public class BoardTest {

	Board board;
	Window window;
	
	@Before
	public void init() {
		window = new Window();
		board = new Board(window);
		board.startGame();
		
		
		
		board.generateNewShape();
		
	}
	
	@Test
	public void testPause() { // Teszteli hogy megállítja-e a játékot
		board.pause();
		Assert.assertEquals(board.ispaused(), true);
		board.unpause();
		Assert.assertEquals(board.ispaused(), false);
		board.unpause();
		Assert.assertEquals(board.ispaused(), false);
	}
	
	@Test
	public void teststopGame() {//Teszteli hogy kinulláza-e a táblát
		board.stopGame();
		int[][] empty = new int [board.getboardBlocksHeight()][board.getBoardBlocksWidth()];
		Assert.assertArrayEquals(board.getBoard(), empty);
		
	}
	@Test
	public void testcheckLine() {
		
		for(int y = 0;y < board.getboardBlocksHeight();y++) {
			for(int x = 0;x < board.getBoardBlocksWidth(); x++) {
				board.getBoard()[y][x] = 2;
			}
		}
		
		board.checkLine(board.getboardBlocksHeight());
		Assert.assertEquals((board.getboardBlocksHeight()-1)*(board.getboardBlocksHeight()-1), board.getScore());
	}
	
	@Test
	public void testgenerateNewShape() {
		Assert.assertNotEquals(board.getcurrAlak(), board.getnextAlak());
	}
	
	@Test
	public void testUpdate() {
		int curY = board.getcurrAlak().getcurY();
		
		for(int i = 0;i < 16;i++) {//minden 15 után esik egyet lejjebb
			board.update();
		}
		
		Assert.assertNotEquals(curY, board.getcurrAlak().getcurY());
	}
	
	@Test
	public void testMozgatasValid() {
		board.update();
		int currX = board.getcurrAlak().getcurX();
		board.getcurrAlak().setDeltaX(20);
		board.update();
		Assert.assertEquals(currX, board.getcurrAlak().getcurX());
		
		board.getcurrAlak().setDeltaX(2);
		board.update();
		Assert.assertEquals(currX + 2, board.getcurrAlak().getcurX());
	}
}
