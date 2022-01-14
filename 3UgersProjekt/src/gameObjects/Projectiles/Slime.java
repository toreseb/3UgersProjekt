package gameObjects.Projectiles;

import gameObjects.Projectile;
import javafx.scene.image.Image;

/*
 * By: Helene
 */
public class Slime extends Projectile {

	public Slime(double posX, double posY, double xSpeed, double ySpeed) {
		super(posX, posY, xSpeed, ySpeed);
		// TODO Auto-generated constructor stub
	}

	protected void initShape() {
		banana = new Image("Slime.png");
		super.initShape();
	}
}
