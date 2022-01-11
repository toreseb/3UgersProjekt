package gameObjects;

import framework.Main;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LevelPart extends GameObject {
	
	static int windowWidth = 8;
	static int windowHeight = 15;
	static int horizontalMargins = 5;
	static int verticalMargins = 5;

	public LevelPart(int posX, int width, int height) {
		super(posX, Main.m, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawShape(Group root) {
		root.getChildren().add(groupShape);
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub

	}

	@Override
	void initShape() {
		Rectangle mainShape = new Rectangle(vectorPos.get(0),vectorPos.get(1)-height,width,height);
		hitBox = mainShape;
		int random = (int)Math.floor(Math.random()*3);
		switch (random) {
		case 0: {
			mainShape.setFill(Color.RED);
			break;
		}
		case 1: {
			mainShape.setFill(Color.BLUE);
			break;
		}
		case 2: {
			mainShape.setFill(Color.GREEN);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + random);
		}
		
		
		groupShape.getChildren().add(mainShape);
		int rows = (int)Math.floor(height/(windowHeight+verticalMargins));
		int columns = (int)Math.floor(width/(windowWidth+horizontalMargins));
		for(int cRows = 0; cRows < rows; cRows++) {
			for(int cColumns = 0; cColumns < columns; cColumns++) {
				Rectangle window = new Rectangle(vectorPos.get(0) + (horizontalMargins + cColumns * (windowWidth+horizontalMargins)),(vectorPos.get(1)-height)+(verticalMargins + cRows * (windowHeight+verticalMargins)),windowWidth,windowHeight);
				window.setFill(Color.ALICEBLUE);
				groupShape.getChildren().add(window);
			}
		}
	}

}
