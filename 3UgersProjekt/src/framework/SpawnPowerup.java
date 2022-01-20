package framework;

import gameObjects.LevelPart;
import gameObjects.PowerUp;
import gameObjects.PowerUps.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/*
 * This class spawns a random powerup in a random position.
 * 
 * By: Helene & Tore
 */
public class SpawnPowerup {
	private static int x;
	private static int y;

	public static boolean newPowerUp = false;

	/*
	 * rnd()
	 * 
	 * Returns a random int between 0 and the number given as parameter
	 */
	public static int rnd(int num) {
		return (int) (Math.random() * num);
	}

	/*
	 * spawnPower()
	 * 
	 * Spawns a random powerup in a random position.
	 * 
	 * By: mainly Tore
	 */
	public static void spawnPower() {

		boolean isBad = true;

		// Finds a random position. If it is within another object, it finds a new one
		// until it isn't.
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

		// Makes a random powerup out of four options with the position found earlier
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

		// Adds powerup to game
		Main.cLevel.powerUps.add(powerUp);
	}
}
