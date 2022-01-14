package gameObjects.Projectiles;

import gameObjects.Gorilla;
import gameObjects.Projectile;
import javafx.scene.image.Image;

/*
 * By: Helene
 */
public class Anvil extends Projectile {

	public Anvil(double posX, double posY, double xSpeed, double ySpeed) {
		super(posX, posY, xSpeed, ySpeed);
	}

	protected void initShape() {
		banana = new Image("Anvil.png");
		super.initShape();
	}

	public void playerHit(Gorilla p) {
		System.out.println("Damage Dealt");
		p.curNumLife--;
		p.hearts.remove(p.curNumLife);
		p.curNumLife--;
		p.hearts.remove(p.curNumLife);
		p.drawHearts();
	}
}
