package gameObjects;

import framework.Main;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * The function of this class is.
 * 
 *
 */

public class Gorilla extends GameObject {

	public Gorilla(int posX, int posY) {
		super(posX, posY, 50, 50);

	}

	@Override
	public void drawShape() {
		Circle circle = new Circle(vectorPos.get(0), vectorPos.get(1), 5);

		Group root = (Group) Main.mainScene.getRoot();
		root.getChildren().add(circle);
		Scene scene = new Scene(root);

		Main.mainStage.setScene(scene);
	}

	@Override
	public void step() {

	}

	private void throwBanana() {

	}

}
