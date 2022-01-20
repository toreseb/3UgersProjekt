package gameObjects.PowerUps;

import framework.Main;
import gameObjects.PowerUp;
import javafx.scene.image.Image;

/*
 * IcePower extends the abstract class PowerUp.
 * 
 * This class marks that a specific gorilla has the Ice power up.
 * 
 * By: Helene
 */
public class IcePower extends PowerUp {

	/*
	 * IcePower()
	 * 
	 * Calls the constructor in PowerUp.
	 */
	public IcePower(double posX, double posY) {
		super(posX, posY);
	}

	/*
	 * initShape()
	 * 
	 * Sets the image of the power up and calls initShape() from PowerUp.
	 */
	protected void initShape() {
		image = new Image("Iceball.png");
		
		super.initShape();
	}

	/*
	 * collected()
	 * 
	 * If the player has no power up, its hasPow is changed to "ice" and collected() in PowerUp is called.
	 */
	public void collected() {
		// The first powerup hit on a throw is the one that is collected
		if (Main.pList.get(Main.cPlayer).hasPow.equals("no")) {
			Main.pList.get(Main.cPlayer).hasPow = "ice";
		}

		super.collected();
	}
}
