package framework;

import java.util.ArrayList;
import java.lang.reflect.*;
import gameObjects.GameObject;
import gameObjects.LevelPart;
import gameObjects.PowerUp;
import gameObjects.LevelParts.*;

public class Level {
	int width,height;
	
	public ArrayList<LevelPart> parts = new ArrayList<LevelPart>();
	public ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	public ArrayList<Class<? extends LevelPart>> partTypes = new ArrayList<Class<? extends LevelPart>>();

	public Level(int width,int height) {
		this.width = width;
		this.height = height;
		partTypes.add(Stone.class);
		partTypes.add(Building.class);
		partTypes.add(Tree.class);
		initLevelParts();
	}
	
	void initLevelParts(){
		
		int widthLeft = width;
		while(widthLeft > 0) {
			LevelPart part = null;
			Class<? extends LevelPart> c = null;
			
			int random = (int)Math.floor( Math.random()*(partTypes.size()));
			c = partTypes.get(random);
			
			
			int partWidth = 50 + (int)((Math.random())*100);
			int partHeight = (int)(50 +((Math.random()*Main.m/2)));
			try {
				if(widthLeft <= 150){
					partWidth = widthLeft;
				}
				part = c.getDeclaredConstructor(int.class,int.class,int.class).newInstance(width-widthLeft,partWidth,partHeight);
				
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
			}
			
			parts.add(part);
			widthLeft -= partWidth;
		}
	}
}
