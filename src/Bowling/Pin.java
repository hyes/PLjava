package Bowling;

public class Pin {

	static int pins = 10;
	static int temppins;
	
	public static int afterFirstRoll(int i) {
		temppins = pins-i;
		return temppins;
	}
	
}
