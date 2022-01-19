package gameObjects.PowerUps;

import framework.Main;
import gameObjects.PowerUp;
import javafx.scene.image.Image;

public class Pie extends PowerUp {

	public Pie(double posX, double posY) {
		super(posX, posY);
	}
	
	protected void initShape() {
		image = new Image("Pie.png");
		super.initShape();
	}

	public void collected() {
		if (Main.pList.get(Main.cPlayer).curNumLife < Main.pList.get(Main.cPlayer).numLife) {
			Main.pList.get(Main.cPlayer).curNumLife++;
			Main.pList.get(Main.cPlayer).drawHearts();
		}
		super.collected();
	}
}
