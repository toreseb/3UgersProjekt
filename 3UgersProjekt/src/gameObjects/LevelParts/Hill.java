package gameObjects.LevelParts;

import gameObjects.LevelPart;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/*
 * Hill extends the abstract class LevelPart. 
 * 
 * This class makes a hill with a random size within bounds.
 * 
 * By: Tore
 */
public class Hill extends LevelPart {
	
	public static int maxWidth = 300;
	public static int divHeight = 3;

	/*
	 * Hill()
	 * 
	 * Calls the constructor in LevelPart.
	 */
	public Hill(int posX, int width, int height) {
		super(posX, width, height);
	}
	
	/*
	 * initShape()
	 * 
	 * Makes the hill and defines the hitBox.
	 */
	protected void initShape(){
		Ellipse el = new Ellipse(width/2,height,width/2,height);
		el.setFill(Color.GREEN);
		hitBox = el;
		groupShape.getChildren().add(el);
	}

}
