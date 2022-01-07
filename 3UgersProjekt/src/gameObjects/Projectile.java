package gameObjects;

import framework.*;

import java.lang.Math.*;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;

public class Projectile extends GameObject{
	double direction;
	double speed;
	double xSpeed;
	double ySpeed;
	double yConst;
	double g = 9.82;

	public Projectile(int posX, int posY) {
		super(posX, posY, 2, 2);
		direction = 5; 
		direction = Math.toRadians(direction);
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
		 yConst += g;
		 vectorPos.set(0,(int)(vectorPos.get(0) + xSpeed));
		 vectorPos.set(1,(int)(vectorPos.get(1) + ySpeed-yConst));
	}
}
