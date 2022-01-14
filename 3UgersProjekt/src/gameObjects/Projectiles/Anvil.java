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
		// TODO Auto-generated constructor stub
	}

	protected void initShape() {
		banana = new Image("Anvil.png");
		super.initShape();
	}

	public void playerHit(Gorilla p) {
		// TODO Auto-generated method stub
		System.out.println("Damage Dealt");
		p.curNumLife -= 2;
		p.hearts.remove(p.curNumLife);
		p.lifeBar.getChildren().clear();
		p.drawHearts();
		nextPlayer();
	}
}
