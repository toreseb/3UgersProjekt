package gameObjects.Projectiles;

import gameObjects.Gorilla;
import gameObjects.Projectile;
import javafx.scene.image.Image;

/*
 * Banana extends the abstract class Projectile.
 * 
 * This class is the banana projectile and has the effect of a normal banana.
 * 
 * By: Tore
 */
public class Banana extends Projectile {
	/*
	 * Banana()
	 * 
	 * Calls the constructor in Projectile.
	 */
	public Banana(double posX, double posY, double xSpeed, double ySpeed) {
		super(posX, posY, xSpeed, ySpeed);
	}

	/*
	 * initShape()
	 * 
	 * Sets the image of the projectile and calls initShape() from Projectile.
	 */
	protected void initShape() {
		banana = new Image("Banana.png");
		super.initShape();
	}

	/*
	 * playerHit()
	 * 
	 * Removes a life from the player.
	 * 
	 * By: William
	 */
	public void playerHit(Gorilla p) {
		p.curNumLife--;
		p.drawHearts();
	}

}
