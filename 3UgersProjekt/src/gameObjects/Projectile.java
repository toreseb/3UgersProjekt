package gameObjects;

import framework.*;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Projectile: creates the object which is thrown by the gorilla
 * 
 *By: Embla
 */
public class Projectile extends GameObject {
	private double xSpeed;
	private double ySpeed;
	private double direction;
	private double g = 9.82;

	//Constructor
	public Projectile(int posX, int posY, double direction, double speed) {

		super(posX, posY, 2, 2);
		this.direction = Math.toRadians(direction);
		xSpeed = Math.cos(this.direction) * speed; // calculating x speed
		ySpeed = -Math.sin(this.direction) * speed; // calculation y begin speed
	}

	//Draws the shape
	@Override
	public void drawShape(Group root) {
		Circle circle = new Circle(vectorPos.get(0), vectorPos.get(1), 5, Color.GOLD);
		root.getChildren().add(circle);
	}

	//Calculates the new position of the projectile in a parable
	public void step() {
		ySpeed += g / 60; // calc new ySpeed from acceleration - 60 frames per second
		vectorPos.set(0, (int) (vectorPos.get(0) + xSpeed));
		vectorPos.set(1, (int) (vectorPos.get(1) + ySpeed));
	}

	// Collision with walls (stop xSpeed) and floor (check distance from other gorilla and deletes object)
	@Override
	public void collision() {
		if (vectorPos.get(0) - width / 2 < 0)
			vectorPos.set(0, 0 + width / 2);
		if (vectorPos.get(0) + width / 2 > Main.n)
			vectorPos.set(0, Main.n - width / 2);
		if (vectorPos.get(1) + height / 2 > Main.m) {
			vectorPos.set(1, Main.m - height / 2);
			xSpeed = 0;
			Gorilla gorilla = Main.pList.get((Main.cPlayer+1) % 2);
			if (gorilla.vectorPos.get(0) - vectorPos.get(0) < Main.n / 50
					&& gorilla.vectorPos.get(0) - vectorPos.get(0) > - Main.n / 50) {
				Main.pList.get(Main.cPlayer).point ++;
			}
			Main.cPlayer++;//The player changes when the projectile hits the ground
			if(Main.cPlayer > Main.pList.size()-1) {
				Main.cPlayer = 0;
			}
			Main.promptPlayer();
			this.deleteObject();
		}
	}
}
