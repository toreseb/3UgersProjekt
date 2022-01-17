package gameObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import framework.Main;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * PowerUp
 * 
 * This class is an abstract class that extends GameObject.
 * 
 * It contains all the fields and methods a basic powerup needs.
 * 
 * By: Helene Moesgaard
 */

public abstract class PowerUp extends GameObject {
	public Image image;
	protected ImageView imageView;
	private int counter = 0;
	public static int width = 25;
	public static int height = 25;

	public PowerUp(double posX, double posY) {
		super(posX, posY, width, height);
		Main.cLevel.powerUps.add(this);
	}

	protected void initShape() {
		//Sub class defines its own image
		
		imageView = new ImageView(image);

		imageView.setFitWidth(width);
		imageView.setFitHeight(height);

		imageView.setPreserveRatio(true);

		groupShape.getChildren().add(imageView);
		super.initShape();
	}

	public void collected() {
		Main.gameRoot.getChildren().remove(groupShape);
		Main.cLevel.powerUps.remove(this);
		this.deleteObject();
	}

	@Override
	protected void step() {
		super.step();
		counter++;

		if (counter == 20) {
			vectorPos.set(1, vectorPos.get(1) + 3);
		} else if (counter == 40) {
			vectorPos.set(1, vectorPos.get(1) - 3);
		} else if (counter == 60) {
			vectorPos.set(1, vectorPos.get(1) - 3);
		} else if (counter == 80) {
			vectorPos.set(1, vectorPos.get(1) + 3);
		}

		if (counter == 80) {
			counter = 0;
		}
	}
	public void newCoords() {
		vectorPos.set(0,Math.random()*(Main.n));
		vectorPos.set(1,Math.random()*(Main.m));
		super.step();
	}
}
