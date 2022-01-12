package gameObjects;

import framework.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * The function of this class is:
 * 		Create the gorilla
 * 		Draw the gorilla
 *		Enables the gorilla to throw the banana
 *
 * by: William Holberg
 */

public class Gorilla extends GameObject {
	public static int width = Main.n/15;
	public static int height = Main.n/15;

	public int point = 0;

	// Constructor
	public Gorilla(int posX, int posY) {
		super(posX, posY, width, height);
		this.vectorPos.set(1, (double) Main.m-(Main.cLevel.maxHeightAtLocation(((int)(double)this.vectorPos.get(0)),width)+height));
	}

	@Override
	public void drawShape(Group root) {
		Rectangle rect = new Rectangle(vectorPos.get(0), vectorPos.get(1),width,height);  // Creates our gorilla
		rect.setFill(Color.BROWN);
		
		root.getChildren().add(rect);
		//root.getChildren().add(groupShape);
	}

	@Override
	public void step() {} // This class is not used here

	public void throwBanana(double angle, double speed) {
		if(Main.cPlayer == 0) {
			Projectile banana = new Projectile(vectorPos.get(0)+width/2, vectorPos.get(1), angle, speed);
		}else {
			Projectile banana = new Projectile(vectorPos.get(0)+height/2, (double)vectorPos.get(1), 180-angle, speed);
		}
	}

	@Override
	void initShape() {
		Rectangle rect = new Rectangle(vectorPos.get(0), vectorPos.get(1),width,height);  // Creates our gorilla
		rect.setFill(Color.BROWN);
		
		groupShape.getChildren().add(rect);
		
	}
	
}
