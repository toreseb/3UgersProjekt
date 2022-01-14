package gameObjects.PowerUps;

import framework.Main;
import gameObjects.PowerUp;
import javafx.scene.image.Image;

public class IcePower extends PowerUp {

	public IcePower(double posX, double posY, int width, int height) {
		super(posX, posY, width, height);
		// TODO Auto-generated constructor stub
	}

	protected void initShape() {
		image = new Image("Iceball.png");
		super.initShape();
	}

	@Override
	public void collected() {
		Main.pList.get(Main.cPlayer).hasPow = "ice";

		super.collected();
	}
}
