package gameObjects;

import java.lang.Math.*;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;

public class Projectile extends GameObject{
	double direction;
	double speed;
	public Projectile(int posX, int posY, int width, int height) { // dette input angiver spillerens placering og skal m�ske ikke kaldes i constructoren
		super(posX, posY, width, height);
		direction = 5; //skal hentes fra StartWindow og angives i grader
		direction = Math.toRadians(direction); // konverteres til radianer
		speed = 5; // skal hentes fra StartWindow
	}
	public void drawShape() {
		
		Circle circle = new Circle(vectorPos.get(0),vectorPos.get(1),5);

        // root.getChildren().add(circle);
		// skal tilf�jes til vores main-scene
		//
		
	}
	public void step() {
		 //vectorPos.set(0,vectorPos.get(0) + Math.cos(direction)*speed);
		// posX+= matematik til skr�t kast
		//x+= hastighed i x-retning - cos(direction)*speed
		// posY+= matematik til skr�t kast
	}
	public void collision() {
		
	}
	
	
}
