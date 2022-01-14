package gameObjects;

import framework.Main;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LevelPart extends GameObject {
	
	
	
	int inLevelId;
	static int inLevelIdCounter = 0;

	public LevelPart(int posX, int width, int height) {
		super(posX, height, width, height);
		inLevelId = inLevelIdCounter;
		inLevelIdCounter++;
	}


	@Override
	public void step() {
		super.step();

	}
	@Override
	public void collision() {} //collision() kæmper mod initShape();

	@Override
	void initShape() {
		double windowWidth = 8;
		double windowHeight = 15;
		int horizontalMargins = 5;
		int verticalMargins = 5;
		Rectangle mainShape = new Rectangle(0,0,width,height);
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
		int rows = (int)Math.floor((height-verticalMargins)/(windowHeight+verticalMargins));
		double extraHeight = (height-verticalMargins) % (windowHeight+verticalMargins);
		int columns = (int)Math.floor((width-horizontalMargins)/(windowWidth+horizontalMargins));
		double extraWidth = (width-horizontalMargins) % (windowWidth+horizontalMargins);
		//windowHeight += extraHeight/columns;
		//windowWidth += extraWidth/rows;
		System.out.println(rows * (windowWidth+horizontalMargins)+horizontalMargins);
		
		for(int cRows = 0; cRows < rows; cRows++) {
			for(int cColumns = 0; cColumns < columns; cColumns++) {
				Rectangle window = new Rectangle(0 + (horizontalMargins + cColumns * (windowWidth+horizontalMargins)),(verticalMargins + cRows * (windowHeight+verticalMargins)),windowWidth,windowHeight);
				window.setFill(Color.ALICEBLUE);
				groupShape.getChildren().add(window);
			}
		}
	}

}
