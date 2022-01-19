package gameObjects.Projectiles;

import framework.Main;
import gameObjects.Gorilla;
import gameObjects.Projectile;
import javafx.scene.image.Image;

/*
 * Anvil extends the abstract class Projectile.
 * 
 * This class is the anvil projectile and has the effect of an anvil when a player is hit.
 * 
 * By: Helene
 */
public class Anvil extends Projectile {
	/*
	 * Anvil()
	 * 
	 * Calls the constructor in Projectile.
	 */
	public Anvil(double posX, double posY, double xSpeed, double ySpeed) {
		super(posX, posY, xSpeed, ySpeed);
	}

	/*
	 * initShape()
	 * 
	 * Sets the image of the projectile and calls initShape() from Projectile.
	 */
	protected void initShape() {
		banana = new Image("Anvil.png");
		super.initShape();
	}

	/*
	 * playerHit()
	 * 
	 * Removes two lives from the player that gets hit.
	 */
	public void playerHit(Gorilla p) {
		p.curNumLife--;
		p.curNumLife--;
		p.drawHearts();
		
		// Marks that the using player no longer has a power up.
		Main.pList.get(Main.cPlayer).hasPow = "no";
	}
}
