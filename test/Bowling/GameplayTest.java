package Bowling;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameplayTest {
	
	@Test
	public void 플레이어_추가가_가능하다() {
		Game ppp = new Game(new Player("주혜연"));
		String result = ppp.name;
		assertEquals("주혜연", result);
		
	}
}
