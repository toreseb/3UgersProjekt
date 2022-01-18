package gameObjects.LevelParts;

import gameObjects.LevelPart;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Hill extends LevelPart {
	
	public static int maxWidth = 300;
	public static int divHeight = 3;

	public Hill(int posX, int width, int height) {
		super(posX, width, height);
		// TODO Auto-generated constructor stub
	}
	
	protected void initShape(){
		Ellipse el = new Ellipse(width/2,height,width/2,height);
		el.setFill(Color.GREEN);
		hitBox = el;
		groupShape.getChildren().add(el);
	}

}
