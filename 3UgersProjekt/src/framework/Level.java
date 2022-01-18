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
		String name = Main.levelName;
		
		if(name.equals("FOREST")||name.equals("VILLAGE")) {
			partTypes.add(Tree.class);
		}
		if(name.equals("CITY") || name.equals("VILLAGE")) {
			partTypes.add(Building.class);
		}
		if(name.equals("FOREST") || name.equals("MOUNTAINS") || name.equals("ROCKYHILLS")) {
			partTypes.add(Stone.class);
		}
		if(name.equals("HILL") || name.equals("ROCKYHILLS")) {
			partTypes.add(Hill.class);
		}
		
		
		
		
		initLevelParts();
	}
	
	void initLevelParts(){
		
		int widthLeft = width;
		while(widthLeft > 0) {
			LevelPart part = null;
			Class<? extends LevelPart> c = null;
			
			
			int random = (int)Math.floor( Math.random()*(partTypes.size()));
			c = partTypes.get(random);
			
			
			
			try {
				
				int partWidth = 50 + (int)(Math.random()*c.getDeclaredField("maxWidth").getInt(null));
				int partHeight = (int)(50 +((Math.random()*Main.m/c.getDeclaredField("divHeight").getInt(null))));
				if(widthLeft <= c.getDeclaredField("maxWidth").getDouble(c)){
					partWidth = widthLeft;
				}
				part = c.getDeclaredConstructor(int.class,int.class,int.class).newInstance(width-widthLeft,partWidth,partHeight);
				parts.add(part);
				widthLeft -= partWidth;
				
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
			
			
			
			
		}
		
	}
	

}
