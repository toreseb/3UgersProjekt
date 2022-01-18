package framework;

import gameObjects.GameObject;
import gameObjects.LevelPart;
import gameObjects.PowerUp;
import gameObjects.PowerUps.*;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SpawnPowerup {
	private static int antal = 4;
	private static int x;
	private static int y;

	public static boolean newPowerUp = false;

	public static int rnd(int num) {
		return (int) (Math.random() * num);
	}

	public static void spawnPower() {

		boolean isBad = true;

		while (isBad) {
			isBad = false;
			x = rnd(Main.n);
			y = rnd(Main.m);
			Rectangle rect = new Rectangle(x, Main.m - y, PowerUp.width, PowerUp.height);
			for (LevelPart lp : Main.cLevel.parts) {
				Shape sh = Shape.intersect(rect, lp.hitBox);
				if (sh.getBoundsInParent().getWidth() != -1) {

					isBad = true;
				}
			}
		}

		PowerUp powerUp;
		int rnd = rnd(4);
		if (rnd < 1) {
			powerUp = new SlimePower(x, y);
		} else if (rnd < 2) {
			powerUp = new IcePower(x, y);
		} else if (rnd < 3) {
			powerUp = new Pie(x, y);
		} else {
			powerUp = new AnvilPower(x, y);
		}

		Main.cLevel.powerUps.add(powerUp);
	}
}
