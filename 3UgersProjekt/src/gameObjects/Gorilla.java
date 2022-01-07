package gameObjects;

import framework.Main;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
/**
 * William har lavet denne class
 * The function of this class is:
 * 		Create the gorilla 
 * 		Draw the gorilla
 * 		Giv it the abilliti to throw a bananan
 *
 */

public class Gorilla extends GameObject {
	public static int width = 30;
	public static int height = 30;
	
	// Constructor
	public Gorilla(int posX, int posY) {
		super(posX, posY, width, height);
	}

	@Override
	public void drawShape(Group root) {
		Rectangle rect = new Rectangle(vectorPos.get(0)-height/2, vectorPos.get(1)-height/2,width,height);  // Creates our circle
		root.getChildren().add(rect);    // adding the circle to the group
	}

	@Override
	public void step() {
		/*
		vectorPos.set(0,vectorPos.get(0)+1);
		vectorPos.set(1,vectorPos.get(1)-1);
		*/
	} // This class is not used here 

	private void throwBanana(int angle, int throwStrangth) {
		Projectile banana = new Projectile(vectorPos.get(0), vectorPos.get(1), angle, throwStrangth); 
		banana.step();
	}

}
