package gameObjects.Projectiles;

import framework.Main;
import gameObjects.Gorilla;
import gameObjects.Projectile;
import javafx.scene.image.Image;

/*
 * Ice extends the abstract class Projectile.
 * 
 * This class is the ice projectile and has the effect of an ice ball when a player is hit.
 * 
 * By: Helene
 */
public class Ice extends Projectile {
	/*
	 * Ice()
	 * 
	 * Calls the constructor in Projectile.
	 */
	public Ice(double posX, double posY, double xSpeed, double ySpeed) {
		super(posX, posY, xSpeed, ySpeed);
	}

	/*
	 * initShape()
	 * 
	 * Sets the image of the projectile and calls initShape() from Projectile.
	 */
	protected void initShape() {
		banana = new Image(ClassLoader.getSystemResource("Iceball.png").toString());
		super.initShape();
	}

	/*
	 * playerHit()
	 * 
	 * Marks the player as iced and updates images.
	 */
	public void playerHit(Gorilla p) {
		// Set images for debuffed gorilla
		p.gorillaImg = new Image("FrozenGorilla.png");
		p.normalImage = false;
		p.frozen = 1;
		p.gorilla.setImage(p.gorillaImg);

		// Marks that the using player no longer has a power up.
		Main.pList.get(Main.cPlayer).hasPow = "no";
	}
}
