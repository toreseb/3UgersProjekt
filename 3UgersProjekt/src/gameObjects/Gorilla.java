package gameObjects;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * William har lavet denne class
 * The function of this class is:
 * 		Create the gorilla 
 * 		Draw the gorilla
 * 		Giv it the abilliti to throw a bananan
 *
 */

public class Gorilla extends GameObject {
	public static int width = 50;
	public static int height = 50;
	// Constructor
	public Gorilla(int posX, int posY) {
		super(posX, posY, width, height);
	}

	@Override
	public void drawShape(Group root) {
		Rectangle rect1 = new Rectangle(vectorPos.get(0), vectorPos.get(1),2,2);
		rect1.setFill(Color.BLUE);
		Rectangle rect2 = new Rectangle(vectorPos.get(0)-width/2, vectorPos.get(1)-height/2,width,height);  // Creates our circle
		rect2.setFill(Color.BLACK);
		root.getChildren().addAll(rect1,rect2);    // adding the circle to the group
	}

	@Override
	public void step() {
		/*
		vectorPos.set(0,vectorPos.get(0)+1);
		vectorPos.set(1,vectorPos.get(1)-1);
		*/
	} // This class is not used here 

	public void throwBanana(int angle, int throwStrangth) {
		Projectile banana = new Projectile(vectorPos.get(0), vectorPos.get(1), angle, throwStrangth); 
		banana.step();
	}

}
