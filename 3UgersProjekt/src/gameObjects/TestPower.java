package gameObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import framework.Main;

public class TestPower extends PowerUp {
	public TestPower(double posX, double posY) {
		super(posX, posY);

	}

	public void initShape() {
		image = new Image("2.png");

		super.initShape();
	}

}
