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
	public String name;
	public Image image;
	private ImageView imageView;
	private int counter = 0;

	public PowerUp(double posX, double posY, int width, int height) {
		super(posX, posY, width, height);
	}

	@Override
	void initShape() {
		// TODO Auto-generated method stub
		image = new Image("2.png");

		imageView = new ImageView(image);

		imageView.setFitWidth(width);
		imageView.setFitHeight(height);

		imageView.setPreserveRatio(true);

		groupShape.getChildren().add(imageView);

	}

	@Override
	protected void step() {
		super.step();
		// TODO Auto-generated method stub
		counter++;

		if (counter ==20) {
			vectorPos.set(1, vectorPos.get(1)+3);
		} else if (counter == 40) {
			vectorPos.set(1, vectorPos.get(1)-3);
		} else if (counter == 60) {
			vectorPos.set(1, vectorPos.get(1)-3);
		} else if (counter == 80) {
			vectorPos.set(1, vectorPos.get(1)+3);
		}

		if (counter == 80) {
			counter = 0;
		}
	}

	public abstract void usePower();

}
