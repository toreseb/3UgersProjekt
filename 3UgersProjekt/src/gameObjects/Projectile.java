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
	private double direction;
	double g = 9.82;

	public Projectile(int posX, int posY, double direction, double speed) {
		
		super(posX, posY, 2, 2);
		this.direction = Math.toRadians(direction);
		System.out.println(direction);
		xSpeed = Math.cos(this.direction)*speed;	//calculating x speed
		ySpeed = -Math.sin(this.direction)*speed;	//calculation y begin speed
	}

	@Override
	public void drawShape(Group root) {
		Circle circle = new Circle(vectorPos.get(0),vectorPos.get(1),5);
		root.getChildren().add(circle);
	}
	
	public void step() {
		 ySpeed+= g/60; // calc new ySpeed from acceleration - 60 frames per second
		 vectorPos.set(0,(int)(vectorPos.get(0) + xSpeed));
		 vectorPos.set(1,(int)(vectorPos.get(1) + ySpeed));
	}
}
