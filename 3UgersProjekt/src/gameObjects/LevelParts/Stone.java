package gameObjects.LevelParts;

import gameObjects.LevelPart;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/*
 * Stone extends the abstract class LevelPart. 
 * 
 * This class makes a stone with a random size within bounds.
 * 
 * By: Tore
 */
public class Stone extends LevelPart {
	
	public static int maxWidth = LevelPart.maxWidth;
	public static int divHeight = LevelPart.divHeight;
	
	/*
	 * Stone()
	 * 
	 * Calls the constructor in LevelPart.
	 */
	public Stone(int posX, int width, int height) {
		super(posX, width, height);
	}
	
	/*
	 * initShape()
	 * 
	 * Makes the stone and defines the hitBox.
	 */
	protected void initShape() {
		dwidth = (double)width;
		dheight = (double)height;
		Polygon p = new Polygon();
		
		// Choose appropriate shape depending on size
		if(height <= 150) {
			initSmallStone(p);
		}else {
			initBigStone(p);
		}
		hitBox = p;
		groupShape.getChildren().add(p);
	}
	
	/*
	 * initSmallStone()
	 * 
	 * Sets shape and colour of the small stone.
	 */
	private void initSmallStone(Polygon p) {
		p.getPoints().addAll(new Double[] {
		dwidth/3,0.0,
		dwidth*3/5,dheight/20,
		dwidth*4/5,dheight/4,
		dwidth*6/7,dheight*19/20,
		dwidth*5/6,dheight,
		dwidth*1/6,dheight,
		dwidth*1/7,dheight*18/20,
		dwidth*1/8,dheight*10/20
		});
		p.setFill(Color.GRAY);
	}
	
	/*
	 * initBigStone()
	 * 
	 * Sets shape and colour of the big stone.
	 */
	private void initBigStone(Polygon p) {
		p.getPoints().addAll(new Double[] {
		dwidth/3,0.0,
		dwidth*3/5,dheight/50,
		dwidth*4/5,dheight/4,
		dwidth*6/7,dheight*19/20,
		dwidth*5/6,dheight,
		dwidth*1/6,dheight,
		dwidth*1/7,dheight*18/20,
		dwidth*1/8,dheight/30
		});
		p.setFill(Color.GRAY);
	}

}
