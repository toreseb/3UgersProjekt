package gameObjects.PowerUps;

import framework.Main;
import gameObjects.PowerUp;
import javafx.scene.image.Image;

public class Pie extends PowerUp {

	public Pie(double posX, double posY, int width, int height) {
		super(posX, posY, width, height);
	}
	
	protected void initShape() {
		image = new Image("Pie.png");
		super.initShape();
	}

	public void collected() {
		Main.pList.get(Main.cPlayer).curNumLife++;
		Main.pList.get(Main.cPlayer).hearts.remove(Main.pList.get(Main.cPlayer).curNumLife);
		Main.pList.get(Main.cPlayer).drawHearts();
		super.collected();
	}
}
