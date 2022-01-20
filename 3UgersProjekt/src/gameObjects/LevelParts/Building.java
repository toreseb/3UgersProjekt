package gameObjects.LevelParts;

import gameObjects.LevelPart;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * Building extends the abstract class LevelPart. 
 * 
 * This class makes a building with a random size within bounds and a random colour out of three options.
 * 
 * By: Tore
 */
public class Building extends LevelPart {

	public static int maxWidth = LevelPart.maxWidth;
	public static int divHeight = LevelPart.divHeight;

	/*
	 * Building()
	 * 
	 * Calls the constructor in LevelPart.
	 */
	public Building(int posX, int width, int height) {
		super(posX, width, height);
	}

	/*
	 * initShape()
	 * 
	 * Makes the building and defines the hitBox.
	 */
	protected void initShape() {
		double windowWidth = 8;
		double windowHeight = 15;
		int horizontalMargins = 5;
		int verticalMargins = 5;

		// Rectangle for main shape of building
		Rectangle mainShape = new Rectangle(0, 0, width, height);

		// Random integer, 1, 2 or 3, decides the colour of the building
		int random = (int) Math.floor(Math.random() * 3);
		switch (random) {
		case 0: {
			mainShape.setFill(Color.DARKSALMON);
			break;
		}
		case 1: {
			mainShape.setFill(Color.DARKSLATEBLUE);
			break;
		}
		case 2: {
			mainShape.setFill(Color.DARKMAGENTA);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + random);
		}

		// Buildings hit box is the mainShape
		hitBox = mainShape;

		// Add main shape to groupShape (and through the class, the scene)
		groupShape.getChildren().add(mainShape);

		// Calculate how many rows and columns of windows are needed
		int rows = (int) Math.floor((height - verticalMargins) / (windowHeight + verticalMargins));
		double extraHeight = (height - verticalMargins) % (windowHeight + verticalMargins);
		int columns = (int) Math.floor((width - horizontalMargins) / (windowWidth + horizontalMargins));
		double extraWidth = (width - horizontalMargins) % (windowWidth + horizontalMargins);
		windowHeight += extraHeight / rows;
		windowWidth += extraWidth / columns;

		// Adds windows to building
		for (int cRows = 0; cRows < rows; cRows++) {
			for (int cColumns = 0; cColumns < columns; cColumns++) {
				Rectangle window = new Rectangle(0 + (horizontalMargins + cColumns * (windowWidth + horizontalMargins)),
						(verticalMargins + cRows * (windowHeight + verticalMargins)), windowWidth, windowHeight);
				window.setFill(Color.ALICEBLUE);
				groupShape.getChildren().add(window);
			}
		}

	}

}
