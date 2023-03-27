package junittest;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tetris.Alakzat;
import tetris.Board;
import tetris.Window;

public class AlakzatTest {

	Board board;
	Window window;
	Alakzat alakzat;
	
	@Before
	public void init() {
		window = new Window();
		board = new Board(window);
		int[][] forma = {{1,1,1},{0,1,0}};
		alakzat = new Alakzat(forma, board, null, 0);

		board.startGame();
		alakzat.update();
		
		
	}
	
	
	@Test
	public void testRotate() {
		alakzat.rotate();
		Assert.assertArrayEquals(new int[][]{{1,0},{1,1},{1,0}} , alakzat.getKordin());// 1 1 1       1 0
																					   // 0 1 0  -->  1 1
																					   //			  1 0   90 fokos forgatást tesztel
	}
	@Test
	public void testUpdate() {
		int curX = alakzat.getcurX();
		Assert.assertEquals(curX, alakzat.getcurX());
		alakzat.setDeltaX(2);
		
		alakzat.update();
		
		Assert.assertEquals(curX, alakzat.getcurX() - 2 );
		
	}
	

}
