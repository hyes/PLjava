package Bowling;

import java.util.Random;

public class Player {

	String name;
	Random random = new Random();
	
	public Player(String name){
		this.name = name;
	}
	
	String getName(){
		return this.name;
	}
	
	
	public int rolling(){
		int temp = random.nextInt(Pin.pins + 1);
		return temp;
	 
	}
	
	public int rolling(int i) {
		int temp = random.nextInt(i+1);
		return temp;
		
	}
	
}
