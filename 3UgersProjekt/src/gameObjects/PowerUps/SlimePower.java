package gameObjects.PowerUps;

import framework.Main;
import gameObjects.PowerUp;
import javafx.scene.image.Image;

public class SlimePower extends PowerUp {

	public SlimePower(double posX, double posY, int width, int height) {
		super(posX, posY, width, height);
	}

	protected void initShape() {
		image = new Image("Slime.png");

		super.initShape();
	}

	public void collected() {
		Main.pList.get(Main.cPlayer).hasPow = "slime";

		super.collected();
	}

}
