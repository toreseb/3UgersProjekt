package gameObjects;

import framework.Main;
import java.util.Vector;

/**
 * @author williamholberg
 *
 */


public abstract class GameObject {
	

	private Vector<Integer> vectorPos; 		// The position for the palyers
	private int width, height; 				// the width and hight of the shaps
	
	
	// Constructor
	public GameObject(int posX, int posY, int width, int height) {
		vectorPos = new Vector<Integer>(posX, posY);
		this.width = width;
		this.height = height;
		
		Main.objList.add(this);// Adds the playr to a list.
	}
	
	
	public abstract void shape();
	
	public abstract void run();
	
	
	public void updatePosition() {
		
	}
	
	public void collision() {
		
	}
	
	// Getters
	public Vector<Integer> getVectorPos() {
		return vectorPos;
	}
	
	
	// to string method
	public String toString() {
		return "Players position: " ;
	}
	
 
}
