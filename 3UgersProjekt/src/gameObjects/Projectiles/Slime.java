package gameObjects.Projectiles;

import gameObjects.Gorilla;
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
	
	public void playerHit(Gorilla p) {
		// TODO Auto-generated method stub
		System.out.println("Damage Dealt");
		p.gorillaImg = new Image("SlimedGorilla.png");
		p.slimed = 2;
		nextPlayer();
	}
}
