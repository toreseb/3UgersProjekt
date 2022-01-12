package gameObjects;

import framework.Main;
import java.util.Vector;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

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
	public Vector<Double> vectorPos= new Vector<Double>(2); 		// The position for the objects
	
	public int width, height; 				// the width and height of the shapes
	
	protected Rectangle hitBox;
	
	public Group groupShape = new Group();
	
	
	// Constructor
	public GameObject(double posX, double posY, int width, int height) {
		vectorPos.add((double)posX);
		vectorPos.add((double)posY);
		this.width = width;
		this.height = height;
		
		id = idCounter;
		idCounter++;
		initShape();
		Main.objList.add(this);// Adds the player to a list.
		Main.gameRoot.getChildren().add(groupShape);
	}
	
	abstract void initShape();
	
	protected void step() {
		groupShape.setTranslateX(vectorPos.get(0));
		groupShape.setTranslateY(Main.m -vectorPos.get(1));
		
	}
	
	void draw(Group root2) {	
		/*
		Group root = new Group();
		drawShape(root);
		root2.getChildren().add(root);
		*/
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
		if (vectorPos.get(0)-width/2< 0) {
			vectorPos.set(0,(double)0);
		}
		if (vectorPos.get(0)+width/2>Main.n) {
			vectorPos.set(0, (double)Main.n-width);
		}
			
		if (vectorPos.get(1)+height/2 > Main.m)
			vectorPos.set(1, (double)Main.m-height/2);
		if (vectorPos.get(1)-height/2 < 0)
			vectorPos.set(1, (double)0+height/2);
		
		
	}
	
	
	/***************
	 *   William   *
	 ***************/
	
	// Getters
	public Vector<Double> getVectorPos() {
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
		Main.gameRoot.getChildren().remove(groupShape);
	}
	
	public boolean objectCollision(GameObject gO) {
		boolean inLeftOf = false, inOver = false, inRightOf = false, inUnder = false;
		if(this.vectorPos.get(0) > gO.vectorPos.get(0)) {
			inLeftOf = true;
		}
		if(this.vectorPos.get(0)+this.width < gO.vectorPos.get(0)+gO.width) {
			inRightOf = true;
		}
		if(this.vectorPos.get(1)>gO.vectorPos.get(1)) {
			inOver = true;
		}
		if(this.vectorPos.get(1)-this.height < gO.vectorPos.get(1)+gO.height) {
			inUnder = true;
		}
		if(inLeftOf && inOver && inRightOf && inUnder) {
			return true;
		}else {
			return false;
		}
	}
	
 
}
