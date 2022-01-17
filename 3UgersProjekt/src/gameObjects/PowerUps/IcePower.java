package gameObjects.PowerUps;

import framework.Main;
import gameObjects.PowerUp;
import javafx.scene.image.Image;

public class IcePower extends PowerUp {

	public IcePower(double posX, double posY) {
		super(posX, posY);
	}

	protected void initShape() {
		image = new Image("Iceball.png");
		super.initShape();
	}

	@Override
	public void collected() {
		if (Main.pList.get(Main.cPlayer).hasPow.equals("no")) {
			Main.pList.get(Main.cPlayer).hasPow = "ice";
		}

		super.collected();
	}
}
