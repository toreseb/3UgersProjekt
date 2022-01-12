package gameObjects;

import framework.Main;
import java.util.ArrayList;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.*;

/**
 * The function of this class is:
 * 		Create the gorilla
 * 		Draw the gorilla
 *		Enables the gorilla to throw the banana
 *
 * by: William Holberg
 */

public class Gorilla extends GameObject {

	// Fields
	public static final int width = Main.n/15;
	public static final int height = Main.n/15;
	public int point, numLife;
	public boolean moveable;
	
	private Rectangle rect;
	private ArrayList<ImageView> numLifeList = new ArrayList<ImageView>();
	private Image heart;
	private ImageView helth; 

	// Constructor
	public Gorilla(int posX) {
		super(posX, 0, width, height);
		point = 0;
		moveable = false;
		numLife = 3;
		this.vectorPos.set(1, (double) (Main.cLevel.maxHeightAtLocation(((int)(double)this.vectorPos.get(0)),width)+height));
		step();
	
		// init the array list with the correct amount of life.
		heart = new Image("Heart.png");
		helth = new ImageView(heart);
		for (int i = 0; i < numLife; i++) {
			numLifeList.add(helth);
		}	
	}


	/*
	 * Implementation of the must have functions from GameObject:
	 * drawShape()
	 * initShape()
	 * step()
	 */


	@Override
	public void step() {
		super.step();
		
	} // This class is not used here

	@Override
	void initShape() {
		rect = new Rectangle(0, 0,width,height);  // Creates our gorilla
		rect.setFill(Color.BROWN);
		groupShape.getChildren().add(rect);
	}

	/*
	 * Other functions that the gorilla have
	 *  throwBanana()
	 *  moveGorilla()
	 */
	public void throwBanana(double angle, double speed) {
		if(Main.cPlayer == 0) {
			Projectile banana = new Projectile(vectorPos.get(0)+width/2, vectorPos.get(1), -angle, speed);
		}else {
			Projectile banana = new Projectile(vectorPos.get(0)+height/2, (double)vectorPos.get(1), 180+angle, speed);
		}
	}

	// Enables for moving the gorilla
	private double startPosX, startPosY;
	public void moveGorilla(Group shape) {
		if(moveable) {

			shape.setOnMouseEntered(event -> {
				shape.setCursor(Cursor.HAND);
			});

			// checks if the mouse is pressed and calculates the offset
			shape.setOnMousePressed(event -> {
				startPosX = event.getSceneX() - vectorPos.get(0);
				startPosY = event.getSceneY() - (Main.m - vectorPos.get(1));
				event.consume();
			});

			// Sets the new position to the shape when the mouse is dragged
			shape.setOnMouseDragged(event -> {
				vectorPos.set(0, event.getSceneX() - startPosX);
				vectorPos.set(1, event.getSceneY() - startPosY);
				
				event.consume();
			});


			// sets the moveable back to false when released
			shape.setOnMouseReleased(event -> {

				/*
				 * @TODO: Check for collisions
				 */
				
				this.moveable  = false;
				System.out.println(moveable);
				
				event.consume();
			});
			
		}
	}

	
}
