package gameObjects;

import framework.*;

import java.lang.Math.*;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
/*
public class Projectile extends GameObject{
	double direction;
	double speed;
	double xSpeed;
	double ySpeed;
	double yConst;
	double g = 9.82; // skal kunne �ndres via input?

	public Projectile(int posX, int posY) { // dette input angiver spillerens placering og skal m�ske ikke kaldes i constructoren
		super(posX, posY, 2, 2);
		direction = 5; //skal hentes fra StartWindow og angives i grader
		direction = Math.toRadians(direction); // konverteres til radianer
		speed = 5; // skal hentes fra StartWindow
		xSpeed = Math.cos(direction)*speed;
		ySpeed = Math.sin(direction)*speed;
	}

	@Override
	public void drawShape(Group root) {
		Circle circle = new Circle(vectorPos.get(0),vectorPos.get(1),5);
		root.getChildren().add(circle);
		Scene scene = new Scene(root);

		Main.mainStage.setScene(scene);
        // root.getChildren().add(circle);
		// skal tilf�jes til vores main-scene

	}
	public void step() { // mat tager udgangspunkt i et nulpunkt placeret nederst til venstre
		 yConst += g;
		 vectorPos.set(0,(int)(vectorPos.get(0) + xSpeed));
		 vectorPos.set(1,(int)(vectorPos.get(1) + ySpeed-yConst));

	}
}*/
