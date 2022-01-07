package gameObjects;

import framework.Main;
import java.util.Vector;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;

/**
 * The function of this class is:
 * 		function as a super class for all the other classes in the gameObjecte package
 *		Initialize the objects a position and width and height
 *		Draw the objects and update their position
 *		Check for collisions with the gameWorld
 *		Gets the position of the vector
 *		Makes it possible to print the state of the object
 *		Makes it possible to delete the Objects form the game world.
 *
 * By: Tore og William 		
 */
public abstract class GameObject {
	
	/********************
	 *     William      *
	 ********************/ 
	
	public int id;
	static int idCounter = 0;
	protected Vector<Integer> vectorPos= new Vector<Integer>(2); 		// The position for the objects
	
	public int width, height; 				// the width and height of the shapes
	
	
	// Constructor
	public GameObject(int posX, int posY, int width, int height) {
		vectorPos.add(posX);
		vectorPos.add(posY);
		this.width = width;
		this.height = height;
		
		id = idCounter;
		idCounter++;
		Main.objList.add(this);// Adds the player to a list.
	}
	
	
	public abstract void drawShape(Group root);
	
	public abstract void step();
	
	void draw(Group root2) {	
		Group root = new Group();
		drawShape(root);
		root2.getChildren().add(root);
	}
	
	/******************
	 *      Tore      *
	 ******************/
	
	public void run(Group root) {
		step();
		collision();
		draw(root);
	}
	
	public void collision() {
		if (vectorPos.get(0)-width/2< 0)
			vectorPos.set(0,0+width/2);
		if (vectorPos.get(0)+width/2>Main.n)
			vectorPos.set(0, Main.n-width/2);
		if (vectorPos.get(1)+height/2 > Main.m)
			vectorPos.set(1, Main.m-height/2);
		if (vectorPos.get(1)-height/2 < 0)
			vectorPos.set(1, 0+height/2);
		
		
	}
	
	
	/***************
	 *   William   *
	 ***************/
	
	// Getters
	public Vector<Integer> getVectorPos() {
		return vectorPos;
	}
	
	
	// to string method
	public String toString() {
		return "Objects position: " + "[" + vectorPos.get(0) + ";" + vectorPos.get(1) + "]";
	}
	
	
	/**********
	 *  Tore  *
	 *********/
	
	public void deleteObject() {
		Main.delList.add(this);
	}
	
 
}
