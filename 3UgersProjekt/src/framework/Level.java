package framework;

import java.util.ArrayList;
import java.lang.reflect.*;
import gameObjects.LevelPart;
import gameObjects.PowerUp;
import gameObjects.LevelParts.*;

/*
 * This class contains functions to create level parts and add them to an array list.
 * 
 * By: Tore
 */
public class Level {
	private int width, height;

	// Create lists to contain things that go in the level
	public ArrayList<LevelPart> parts = new ArrayList<LevelPart>();
	public ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	public ArrayList<Class<? extends LevelPart>> partTypes = new ArrayList<Class<? extends LevelPart>>();

	/*
	 * Level()
	 * 
	 * Adds needed types of parts to partTypes, depending on which level is chosen
	 * and calls initLevelParts()
	 */
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		String name = Main.levelName;

		if (name.equals("FOREST") || name.equals("VILLAGE")) {
			partTypes.add(Tree.class);
		}
		if (name.equals("CITY") || name.equals("VILLAGE")) {
			partTypes.add(Building.class);
		}
		if (name.equals("FOREST") || name.equals("MOUNTAINS") || name.equals("ROCKYHILLS")) {
			partTypes.add(Stone.class);
		}
		if (name.equals("HILL") || name.equals("ROCKYHILLS")) {
			partTypes.add(Hill.class);
		}

		initLevelParts();
	}

	/*
	 * initLevelParts()
	 * 
	 * Creates randomly sized level parts until the window is filled.
	 */
	private void initLevelParts() {

		int widthLeft = width;

		// While the window is not filled -> add a level part
		while (widthLeft > 0) {
			LevelPart part = null;
			Class<? extends LevelPart> c = null; // Don't know which class is used, but we know it extends LevelPart

			// Gets a random part type from the array list created earlier
			int random = (int) Math.floor(Math.random() * (partTypes.size()));
			c = partTypes.get(random);

			// Give part random size within bounds
			try {
				int partWidth = 50 + (int) (Math.random() * c.getDeclaredField("maxWidth").getInt(null));
				int partHeight = (int) (50 + ((Math.random() * Main.m / c.getDeclaredField("divHeight").getInt(null))));

				// If random size is larger than there is room for - have it fill in the rest of
				// the level
				if (widthLeft <= c.getDeclaredField("maxWidth").getDouble(c)) {
					partWidth = widthLeft;
				}
				part = c.getDeclaredConstructor(int.class, int.class, int.class).newInstance(width - widthLeft,
						partWidth, partHeight);

				// Add created part to parts array list
				parts.add(part);

				// Calculate room needed to be filled
				widthLeft -= partWidth;

			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
				// Player has no influence on this code, so none of these exceptions can occur
			}
		}
	}
}
