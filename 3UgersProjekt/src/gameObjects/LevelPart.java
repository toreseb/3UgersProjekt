package gameObjects;

/*
 * LevelPart is an abstract class extends the abstract class GameObject.
 * 
 * This class is the further framework for level parts.
 * 
 * By: Tore
 */
public abstract class LevelPart extends GameObject {
	protected static int maxWidth = 150;
	protected static int divHeight = 2;
	
	public int inLevelId;
	private static int inLevelIdCounter = 0;
	protected double dwidth;
	protected double dheight;

	/*
	 * LevelPart()
	 * 
	 * Calls the constructor in GameObject and sets the inLevelId.
	 */
	public LevelPart(int posX, int width, int height) {
		super(posX, height, width, height);
		inLevelId = inLevelIdCounter;
		inLevelIdCounter++;
	}

	/*
	 * step()
	 * 
	 * Calls step() in GameObject.
	 */
	public void step() {
		super.step();
	}
	
	public void collision() {} //collision() fights against the initial construction of the level, because multithreading.
}
