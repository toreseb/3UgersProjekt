package gameObjects;

import framework.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * PowerUp
 * 
 * This class is an abstract class that extends GameObject.
 * 
 * It contains all the fields and methods a basic powerup needs.
 * 
 * By: Helene
 */

public abstract class PowerUp extends GameObject {
	public Image image;
	protected ImageView imageView;
	private int counter = 0;
	public static int width = 25;
	public static int height = 25;

	/*
	 * PowerUp()
	 * 
	 * Calls the constructor in GameObject and adds the power up to cLevel.
	 */
	public PowerUp(double posX, double posY) {
		super(posX, posY, width, height);
		Main.cLevel.powerUps.add(this);
	}

	/*
	 * initShape()
	 * 
	 * Takes the image defined in the sub class' initShape, formats it and adds it to groupShape. Then calls initShape() in GameObject.
	 */
	protected void initShape() {
		//Sub class defines its own image
		
		imageView = new ImageView(image);

		imageView.setFitWidth(width);
		imageView.setFitHeight(height);

		imageView.setPreserveRatio(true);

		groupShape.getChildren().add(imageView);
		super.initShape();
	}

	/*
	 * collected()
	 * 
	 * Deletes power up.
	 */
	public void collected() {
		Main.gameRoot.getChildren().remove(groupShape);
		Main.cLevel.powerUps.remove(this);
		this.deleteObject();
	}

	/*
	 * step()
	 * 
	 * Calls step() in GameObject.
	 * 
	 * Animates power up by shifting position up and down.
	 */
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

		// Spaces out movement
		if (counter == 80) {
			counter = 0;
		}
	}
}
