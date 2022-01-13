package gameObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import framework.Main;

public class TestPower extends PowerUp {
	public TestPower(double posX, double posY, int width, int height) {
		super(posX, posY, width, height, 1);
	}

	@Override
	public void usePower() {
		// TODO Auto-generated method stub

	}

	/*
	 * Idéer til powerups: 
	 * - Freeze (tager et liv og stopper ramtes næste tur) 
	 * - Metal (tager to liv) 
	 * - Big (rammer et større område) - evt. spread 
	 * - Shield (du mister ikke et liv næste gang du bliver ramt)
	 * - Slime (modstanderen kan ikke vælge "Move" på sin tur)
	 */
}
