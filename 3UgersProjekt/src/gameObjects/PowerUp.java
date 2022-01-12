package gameObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import framework.Main;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class PowerUp extends GameObject {
	public String image1;
	public String image2;
	public String image3;
	public Image icon1;
	public Image icon2;
	public Image icon3;
	private ImageView imageView;
	private int counter = 0;

	public PowerUp(double posX, double posY, int width, int height) {
		super(posX, posY, width, height);
	}

	@Override
	void initShape() {
		// TODO Auto-generated method stub

		icon1 = new Image("1.png");
		icon2 = new Image("2.png");
		icon3 = new Image("3.png");

		imageView = new ImageView(icon2);

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

		if (counter < 30) {
			imageView.setImage(icon2);
		} else if (counter >= 30 && counter < 60) {
			imageView.setImage(icon1);
		} else if (counter >= 60 && counter < 90) {
			imageView.setImage(icon2);
		} else if (counter >= 90 && counter < 120) {
			imageView.setImage(icon3);
			counter = 0;
		}
	}

	public abstract void usePower();

}
