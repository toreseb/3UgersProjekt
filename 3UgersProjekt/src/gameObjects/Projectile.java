package gameObjects;

import framework.*;

import java.lang.Math.*;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
/**
 * Embla har skrevet denne class
 *
 */
public class Projectile extends GameObject{
	private double xSpeed;
	private double ySpeed;
	double g = 9.82;

	public Projectile(int posX, int posY, int direction, int speed) {
		super(posX, posY, 2, 2);
		direction = 5; 
		direction = (int)Math.toRadians(direction);
		speed = 5; 
		xSpeed = Math.cos(direction)*speed;
		ySpeed = Math.sin(direction)*speed;
	}

	@Override
	public void drawShape(Group root) {
		Circle circle = new Circle(vectorPos.get(0),vectorPos.get(1),5);
		root.getChildren().add(circle);
	}
	
	public void step() {
		 ySpeed-= g;
		 vectorPos.set(0,(int)(vectorPos.get(0) + xSpeed));
		 vectorPos.set(1,(int)(vectorPos.get(1) + ySpeed));
	}
}
