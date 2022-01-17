package framework;

import gameObjects.GameObject;
import gameObjects.LevelPart;
import gameObjects.PowerUp;
import gameObjects.TestPower;
import gameObjects.PowerUps.*;
import javafx.geometry.Point2D;

public class SpawnPowerup {
	private static int antal = 4;
	private  static int x;
	private  static int y;

	public static int rnd(int num) {
		return (int) (Math.random() * num);
	}

	public static void spawnPower() {
		x = rnd(Main.n);
		y = rnd(Main.m);
		
		PowerUp powerUp = null;
		//Make spawning powerups work

		//Find ud af at f� den til at kun placere den hvis punktet er udenfor bygninger (og gorillaer).
		
		

	}

}
