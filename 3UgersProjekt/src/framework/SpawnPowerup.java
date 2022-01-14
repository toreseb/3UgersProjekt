package framework;

import gameObjects.GameObject;
import gameObjects.TestPower;
import gameObjects.PowerUps.*;
import javafx.geometry.Point2D;

public class SpawnPowerup {
	private static int antal = 4;
	private static int x;
	private static int y;

	public static int rnd(int num) {
		return (int) (Math.random() * num);
	}

	public static void spawnPower() {
		x = rnd(Main.n);
		y = rnd(Main.m);



			for (GameObject gO : Main.objList) {
				if (gO.groupShape.getBoundsInParent().contains(x, y)) {
					spawnPower();
				}
			}
			System.out.println("bib");
			
		
		if (rnd(antal) < 1) {
			Pie powerUp = new Pie(x, y, 25, 25);
		} else if (rnd(antal) < 2) {
			SlimePower powerUp = new SlimePower(x, y, 25, 25);
		} else if (rnd(antal) < 3) {
			IcePower powerUp = new IcePower(x, y, 25, 25);
		} else if (rnd(antal) < 4) {
			AnvilPower powerUp = new AnvilPower(x, y, 25, 25);
		}
		TestPower test = new TestPower(Main.n / 2, Main.m / 2, 25, 25);
		System.out.println("bub");

	}

}
