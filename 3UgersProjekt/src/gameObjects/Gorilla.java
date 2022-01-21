package gameObjects;

import framework.Main;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * The function of this class is:
 * 		Create the gorilla
 * 		Draw the gorilla
 *		Enables the gorilla to throw the banana
 *
 * by: William
 */

public class Gorilla extends GameObject {
	public static int width = Main.n/15;
	public static int height = Main.n/15;

	public int point = 0;

	// Constructor
	public Gorilla(int posX, int posY) {
		super(posX, posY, width, height);
	}

	//Draws the shape
	@Override
	public void drawShape(Group root) {
		Rectangle rect = new Rectangle(vectorPos.get(0)-width/2, vectorPos.get(1)-height/2,width,height);  // Creates our gorilla
		rect.setFill(Color.BROWN);
		
		root.getChildren().add(rect);    // adding the circle to the group
	}

	@Override
	public void step() {} // This function is not used here

	//Throws banana
	public void throwBanana(double angle, double throwStrangth) {
		if(Main.cPlayer == 0) {
			Projectile banana = new Projectile(vectorPos.get(0), vectorPos.get(1), angle, throwStrangth);
		}else {
			Projectile banana = new Projectile(vectorPos.get(0), vectorPos.get(1), 180-angle, throwStrangth);
		}
	}
	
}
