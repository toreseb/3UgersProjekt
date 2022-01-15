package gameObjects;

import framework.Main;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class LevelPart extends GameObject {
	
	
	
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
	protected
	void initShape() {
		
	}

}
