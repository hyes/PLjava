package Bowling;

import java.util.Scanner;

public class Gameplay {
 
	public static void main(String []args){
		
		System.out.print("플레이어 이름을 입력하세요: ");
		Scanner keyboard = new Scanner(System.in);
		String name = keyboard.nextLine();
		
		Game newgame = new Game(new Player(name));
		newgame.setScoreTable();
		newgame.showScoreTable();
		newgame.scoringPerFrame();
		newgame.totalScore();
		
	
	}

	
}
