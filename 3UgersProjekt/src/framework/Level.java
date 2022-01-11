package framework;

import java.util.ArrayList;

import gameObjects.LevelPart;

public class Level {
	int width,height;
	
	ArrayList<LevelPart> parts = new ArrayList<LevelPart>();

	public Level(int width,int height) {
		this.width = width;
		this.height = height;
		initLevelParts();
	}
	
	void initLevelParts(){
		int widthLeft = width;
		while(widthLeft >= 150) {
			int partWidth = 50 + (int)((Math.random())*100);
			int partHeight = 100 + (int)((Math.random()*3)*100);
			LevelPart part = new LevelPart(width-widthLeft,partWidth,partHeight);
			parts.add(part);
			widthLeft -= partWidth;
		}
		LevelPart lastPart = new LevelPart(width-widthLeft,widthLeft,100 + (int)((Math.random()*3)*100));
		parts.add(lastPart);
	}
	

}
