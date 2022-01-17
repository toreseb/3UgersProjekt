package gameObjects.PowerUps;

import framework.Main;
import gameObjects.PowerUp;
import javafx.scene.image.Image;

public class AnvilPower extends PowerUp {

	public AnvilPower(double posX, double posY) {
		super(posX, posY);
	}

	protected void initShape() {
		image = new Image("Anvil.png");
		
		super.initShape();
	}

	public void collected() {
		// The first powerup hit on a throw is the one that is collected
		if (Main.pList.get(Main.cPlayer).hasPow.equals("no")) {
			Main.pList.get(Main.cPlayer).hasPow = "anvil";
		}

		super.collected();
	}
}
