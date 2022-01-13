package gameObjects.Projectiles;

import gameObjects.Projectile;
import javafx.scene.image.Image;

public class Banana extends Projectile {

	public Banana(double posX, double posY, double xSpeed, double ySpeed) {
		super(posX, posY, xSpeed, ySpeed);
		// TODO Auto-generated constructor stub
	}
	
	protected void initShape() {
		banana = new Image("BananaNew.png");
		super.initShape();
		
	}
	
	

}
