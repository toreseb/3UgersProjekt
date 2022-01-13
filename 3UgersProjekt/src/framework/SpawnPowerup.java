package framework;

import gameObjects.TestPower;

public class SpawnPowerup {
	private int antal = 1;
	

	public int whichPower() {
		return (int) (Math.random()*antal);
	}
	
	public static void spawnPower() {
		TestPower test = new TestPower(Main.n/2,Main.m/2,25,25);
		
	}
	
}
