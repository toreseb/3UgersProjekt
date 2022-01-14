package gameObjects.LevelParts;

import gameObjects.LevelPart;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Stone extends LevelPart {
	double dwidth;
	double dheight;
	Label type;
	public Stone(int posX, int width, int height) {
		super(posX, width, height);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void initShape() {
		dwidth = (double) width;
		dheight = (double) height;
		Polygon p = new Polygon();
		type = new Label();
		type.setLayoutX(dwidth);
		type.setLayoutY(dheight);
		
		if(height <= 150) {
			initSmallStone(p);
		}else {
			initBigStone(p);
		}
		groupShape.getChildren().addAll(p,type);
	}
	
	void initSmallStone(Polygon p) {
		type.setText("SMALL STONE");
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
		type.setText("BIG STONE");
		p.getPoints().addAll(new Double[] {
		dwidth/3,0.0,
		dwidth*3/5,dheight/50,
		dwidth*4/5,dheight/4,
		dwidth*6/7,dheight*19/20,
		dwidth*5/6,dheight,
		dwidth*1/6,dheight,
		dwidth*1/7,dheight*18/20,
		dwidth*1/8,dheight*10/20
		});
		p.setFill(Color.GRAY);
	}

}
