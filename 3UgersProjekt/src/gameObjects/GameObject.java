package gameObjects;

import framework.Main;
import java.util.Vector;

/**
 * @author williamholberg
 *
 */


public abstract class GameObject {
	

	protected Vector<Integer> vectorPos; 		// The position for the players
	protected int width, height; 				// the width and height of the shapes
	
	
	// Constructor
	public GameObject(int posX, int posY, int width, int height) {
		vectorPos = new Vector<Integer>(posX, posY);
		this.width = width;
		this.height = height;
		
		Main.objList.add(this);// Adds the player to a list.
	}
	
	
	public abstract void drawShape();
	
	public abstract void step();
	
	public void run() {
		step();
		collision();
		drawShape();
	}
	
	public void collision() {
		if (vectorPos.get(1) > Main.m)
			vectorPos.set(1, Main.m);
		if (vectorPos.get(1) < 0)
			vectorPos.set(1, 0);
		if (vectorPos.get(0)< 0)
			vectorPos.set(0,0);
		if (vectorPos.get(0)>Main.n)
			vectorPos.set(0, Main.n);
		
	}
	
	// Getters
	public Vector<Integer> getVectorPos() {
		return vectorPos;
	}
	
	
	// to string method
	public String toString() {
		return "Objects position: " + "[" + vectorPos.get(0) + ";" + vectorPos.get(1) + "]";
	}
	
 
}
