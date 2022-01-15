package gameObjects.LevelParts;

import gameObjects.LevelPart;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Tree extends LevelPart {
	
	public Rectangle base;
	public Group leaves;
	
	public Shape specialHitbox;

	public Tree(int posX, int width, int height) {
		super(posX, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initShape() {
		dwidth = (double)width;
		dheight = (double)height;
		base = new Rectangle(width*3/8,20,width*2/8, height-20);
		base.setFill(new Color(164d/255,116d/255,73d/255,1));
		hitBox = base;
		double triangleHeights = 50;
		int triangles = (int)Math.floor(dheight/triangleHeights);
		triangleHeights += (dheight % triangleHeights)/triangles;
		leaves = new Group();
		for(int i = 0; i < triangles; i++) {
			Polygon p = new Polygon();
			p.getPoints().addAll(new Double[] {
					0d,dheight-i*triangleHeights,
					dwidth,dheight-i*triangleHeights,
					dwidth/2,dheight-((i+1)*(triangleHeights+(triangleHeights/(i+1))*1d/3d))
			});
			p.setFill(new Color(58d/255,95d/255,11d/255,0.7));
			leaves.getChildren().add(p);
			if(i == 0) {
				specialHitbox = p;
			}else {
				Shape.union(specialHitbox, p);
			}
			
			
		}
		groupShape.getChildren().addAll(base,leaves);
		super.initShape();
	}
	
	@Override
	protected void initHitbox() {
		
	}

}
