package framework;

import java.util.ArrayList;

import gameObjects.GameObject;
import gameObjects.LevelPart;

public class Level {
	int width,height;
	
	public ArrayList<LevelPart> parts = new ArrayList<LevelPart>();

	public Level(int width,int height) {
		this.width = width;
		this.height = height;
		initLevelParts();
	}
	
	void initLevelParts(){
		int widthLeft = width;
		while(widthLeft >= 150) {
			int partWidth = 50 + (int)((Math.random())*100);
			int partHeight = (int)(50 +((Math.random()*Main.m/2)));
			LevelPart part = new LevelPart(width-widthLeft,partWidth,partHeight);
			parts.add(part);
			widthLeft -= partWidth;
		}
		LevelPart lastPart = new LevelPart(width-widthLeft,widthLeft,(int)(50 +(Math.random()*Main.m/2)));
		parts.add(lastPart);
	}
	
	public int heightAtLocation(int posX) {
		int totalLength = 0;
		for (LevelPart levelPart : parts) {
			totalLength += levelPart.width;
			if(posX <= totalLength) {
				return(levelPart.height);
			}
		}
		return(0);
	}
	
	public int maxHeightAtLocation(int xPos, int width) {
		int highest = Main.cLevel.heightAtLocation(xPos);
		System.out.println(Main.cLevel.heightAtLocation(xPos) + " " +Main.cLevel.heightAtLocation(xPos+width));
		if(Main.cLevel.heightAtLocation(xPos+width)>highest) {
			return Main.cLevel.heightAtLocation(xPos+width);
		}
		return highest;
	}

}
