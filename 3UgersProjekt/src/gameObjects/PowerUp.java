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

	public PowerUp(double posX, double posY, int width, int height, String im1, String im2, String im3) {
		super(posX, posY, width, height);
		image1 = im1;
		image2 = im2;
		image3 = im3;
	}

	@Override
	void initShape() {
		// TODO Auto-generated method stub
		try {
			icon1 = new Image(new FileInputStream(image1));
			icon2 = new Image(new FileInputStream(image2));
			icon3 = new Image(new FileInputStream(image3));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		imageView = new ImageView(icon2);

		imageView.setX(vectorPos.get(0));
		imageView.setY(vectorPos.get(1));

		imageView.setFitWidth(width);
		imageView.setFitHeight(height);

		imageView.setPreserveRatio(true);
	}

	@Override
	protected void step() {
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

		Main.gameRoot.getChildren().add(imageView);
	}

	public abstract void usePower();

}
