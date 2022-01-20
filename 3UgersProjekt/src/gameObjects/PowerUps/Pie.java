package gameObjects.PowerUps;

import framework.Main;
import gameObjects.PowerUp;
import javafx.scene.image.Image;

/*
 * Pie extends the abstract class PowerUp.
 * 
 * This class marks that a specific gorilla gets an extra life.
 * 
 * By: Helene
 */
public class Pie extends PowerUp {

	/*
	 * Pie()
	 * 
	 * Calls the constructor in PowerUp.
	 */
	public Pie(double posX, double posY) {
		super(posX, posY);
	}
	
	/*
	 * initShape()
	 * 
	 * Sets the image of the power up and calls initShape() from PowerUp.
	 */
	protected void initShape() {
		image = new Image(ClassLoader.getSystemResource("Pie.png").toString());
		super.initShape();
	}

	/*
	 * collected()
	 * 
	 * The player gets one more life and collected() in PowerUp is called.
	 */
	public void collected() {
		// If the players health is not full, they get another life
		if (Main.pList.get(Main.cPlayer).curNumLife < Main.pList.get(Main.cPlayer).numLife) {
			Main.pList.get(Main.cPlayer).curNumLife++;
			Main.pList.get(Main.cPlayer).drawHearts();
		}
		super.collected();
	}
}
