package gameObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import framework.Main;

public class TestPower extends PowerUp {
	public TestPower(double posX, double posY, int width, int height) {
		super(posX, posY, width, height);

	}

	public void initShape() {
		image = new Image("2.png");

		super.initShape();
	}

	@Override
	public void usePower() {
		// TODO Auto-generated method stub

		Main.pList.get(Main.cPlayer).hasPow = false;
		this.deleteObject();
	}

}
