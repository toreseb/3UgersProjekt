package gameObjects.Projectiles;

import gameObjects.Gorilla;
import gameObjects.Projectile;
import javafx.scene.image.Image;

/*
 * By: Tore 
 */
public class Banana extends Projectile {

	public Banana(double posX, double posY, double xSpeed, double ySpeed) {
		super(posX, posY, xSpeed, ySpeed);
		// TODO Auto-generated constructor stub
	}

	protected void initShape() {
		banana = new Image("BananaNew.png");
		super.initShape();
	}

	
	// William
	@Override
	public void playerHit(Gorilla p) {
		System.out.println("Damage Dealt");
		p.curNumLife--;
		p.hearts.remove(p.curNumLife);
		p.drawHearts();
	}

}
