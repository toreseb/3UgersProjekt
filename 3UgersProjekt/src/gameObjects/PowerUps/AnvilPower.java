package gameObjects.PowerUps;

import framework.Main;
import gameObjects.PowerUp;
import javafx.scene.image.Image;

public class AnvilPower extends PowerUp {

	public AnvilPower(double posX, double posY, int width, int height) {
		super(posX, posY, width, height);
	}
	
	protected void initShape() {
		image = new Image("Anvil.png");

		super.initShape();
	}
	
	public void collected() {
		Main.pList.get(Main.cPlayer).hasPow = "anvil";

		super.collected();
	}
}
