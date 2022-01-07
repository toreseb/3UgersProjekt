package gameObjects;

import framework.Main;
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

	public int point = 0;

	// Constructor
	public Gorilla(int posX, int posY) {
		super(posX, posY, width, height);
	}

	@Override
	public void drawShape(Group root) {
		Rectangle rect2 = new Rectangle(vectorPos.get(0)-width/2, vectorPos.get(1)-height/2,width,height);  // Creates our circle
		rect2.setFill(Color.BLACK);
		root.getChildren().addAll(rect2);    // adding the circle to the group
	}

	@Override
	public void step() {} // This class is not used here

	public void throwBanana(int angle, int throwStrangth) {
		if(Main.cPlayer == 0) {
			Projectile banana = new Projectile(vectorPos.get(0), vectorPos.get(1), angle, throwStrangth);
		}else {
			Projectile banana = new Projectile(vectorPos.get(0), vectorPos.get(1), 180-angle, throwStrangth);
		}
	}
	
}
