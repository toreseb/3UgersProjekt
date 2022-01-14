package gameObjects.Projectiles;

import framework.Main;
import gameObjects.Gorilla;
import gameObjects.Projectile;
import javafx.scene.image.Image;

/*
 * By: Helene
 */
public class Ice extends Projectile {

	public Ice(double posX, double posY, double xSpeed, double ySpeed) {
		super(posX, posY, xSpeed, ySpeed);
	}

	protected void initShape() {
		banana = new Image("Iceball.png");
		super.initShape();
	}

	public void playerHit(Gorilla p) {
		System.out.println("Damage Dealt");
		p.gorillaImg = new Image("FrozenGorilla.png");
		p.normalImage = false;
		p.frozen = 2;
		Main.pList.get(Main.cPlayer).hasPow = "no";
	}
}
