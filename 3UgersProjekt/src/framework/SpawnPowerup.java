package framework;

import gameObjects.GameObject;
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

		//Find ud af at få den til at kun placere den hvis punktet er udenfor bygninger (og gorillaer).

	}

}
