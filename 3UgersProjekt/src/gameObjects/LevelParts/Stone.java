package gameObjects.LevelParts;

import gameObjects.LevelPart;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Stone extends LevelPart {
	
	public static int maxWidth = LevelPart.maxWidth;
	public static int divHeight = LevelPart.divHeight;
	
	public Stone(int posX, int width, int height) {
		super(posX, width, height);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void initShape() {
		dwidth = (double)width;
		dheight = (double)height;
		Polygon p = new Polygon();
		if(height <= 150) {
			initSmallStone(p);
		}else {
			initBigStone(p);
		}
		hitBox = p;
		groupShape.getChildren().add(p);
	}
	
	void initSmallStone(Polygon p) {
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
	
	void initBigStone(Polygon p) {
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
