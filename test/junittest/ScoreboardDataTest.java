package junittest;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tetris.ScoreboardData;

public class ScoreboardDataTest {
	
	ScoreboardData scdata;
	@Before
	public void init() {
		scdata = new ScoreboardData();
		
		
	}
	
	@Test
	public void testGetValue() {
		scdata.addStudent("Teszt1", 10);
		scdata.addStudent("Teszt2", 12);
		scdata.addStudent("Teszt3", 11);
		
		Assert.assertEquals(12, scdata.getValueAt(0, 3));
	}
	
	@Test
	public void testaddStudent() {
		scdata.addStudent("Teszt", 10);
		Assert.assertEquals(true, scdata.addStudent("Teszt1", 10));
		for(int i = 0;i < 8;i++) {
			scdata.addStudent("Teszt", 10);
		}
		Assert.assertEquals(false, scdata.addStudent("Teszt", 10));// tizedik után már nincs változás mivel a legkisebb pontszámnál nem nagyobbat adunk igy nincs benne a top 10-ben
	}

}
