package gameObjects;

import framework.Main;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
/**
 * The function of this class is:
 * 
 *
 */

public class Gorilla extends GameObject {

	public Gorilla(int posX, int posY) {
		super(posX, posY, 50, 50);
	}

	@Override
	public void drawShape(Group root) {
		
		Circle circle = new Circle(vectorPos.get(0), vectorPos.get(1),50);  // Creates our circle
		root.getChildren().add(circle);    // adding the circle to the group
		
	}

	@Override
	public void step() {} // This class is not used here 

	private void throwBanana() {
		Projectile banana = new Projectile(100, 100); // Create an instance of projectile with x and y set to 100

		
	}

}
