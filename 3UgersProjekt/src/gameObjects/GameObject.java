package gameObjects;

import framework.Main;
import java.util.Vector;

import javafx.scene.Group;
import javafx.scene.Scene;

/**
 * @author williamholberg
 *
 */


public abstract class GameObject {
	

	protected Vector<Integer> vectorPos= new Vector<Integer>(2); 		// The position for the players
	protected int width, height; 				// the width and height of the shapes
	
	
	// Constructor
	public GameObject(int posX, int posY, int width, int height) {
		vectorPos.add(posX);
		vectorPos.add(posY);
		this.width = width;
		this.height = height;
		
		Main.objList.add(this);// Adds the player to a list.
	}
	
	
	public abstract Group drawShape(Group root);
	
	public abstract void step();
	
	public void run(Group root) {
		step();
		collision();
		drawShape(root);
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
