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

		if (rnd(antal) < 1) {
			powerUp = new Pie(x, y, 25, 25);
		} else if (rnd(antal) < 2) {
			powerUp = new SlimePower(x, y, 25, 25);
		} else if (rnd(antal) < 3) {
			powerUp = new IcePower(x, y, 25, 25);
		} else if (rnd(antal) <= 4) {
			powerUp = new AnvilPower(x, y, 25, 25);
		}
		
		
		
		TestPower test = new TestPower(Main.n / 2, Main.m / 2, 25, 25);
		System.out.println("bub");

	}

}
