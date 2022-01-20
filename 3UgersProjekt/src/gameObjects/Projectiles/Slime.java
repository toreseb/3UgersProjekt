package gameObjects.Projectiles;

import framework.Main;
import gameObjects.Gorilla;
import gameObjects.Projectile;
import javafx.scene.image.Image;

/*
 * Slime extends the abstract class Projectile.
 * 
 * This class is the slime projectile and has the effect of a slime ball when a player is hit.
 * 
 * By: Helene
 */
public class Slime extends Projectile {
	/*
	 * Slime()
	 * 
	 * Calls the constructor in Projectile.
	 */
	public Slime(double posX, double posY, double xSpeed, double ySpeed) {
		super(posX, posY, xSpeed, ySpeed);
	}

	/*
	 * initShape()
	 * 
	 * Sets the image of the projectile and calls initShape() from Projectile.
	 */
	protected void initShape() {
		banana = new Image("SlimeBallTransparent.png");
		super.initShape();
	}
	
	/*
	 * playerHit()
	 * 
	 * Marks the player as slimed and updates images.
	 */
	public void playerHit(Gorilla p) {
		// Set images for debuffed gorilla
		p.gorillaImg = new Image("SlimedGorilla.png");
		p.gorillaThrowImg = new Image("SlimedGorillaThrow.png");
		p.normalImage = false;
		p.slimed = 1;
		p.gorilla.setImage(p.gorillaImg);
		
		// Marks that the using player no longer has a power up.
		Main.pList.get(Main.cPlayer).hasPow = "no";
	}
}
