package gameObjects;
import framework.Main;
import javafx.event.Event;
import javafx.scene.Cursor;
import framework.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

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


	// Constructor
	public Gorilla(int posX, int posY) {
		super(posX, posY, width, height);
		point = 0;
		moveable = false;
		numLife = 3;
	}


	/*
	 * Implementation of the must have functions from GameObject:
	 * drawShape()
	 * initShape()
	 * step()
	 */

	@Override
	public void drawShape(Group root) {
		root.getChildren().add(groupShape);    // adding the circle to the group
	}

	@Override
	public void step() {}

	@Override
	void initShape() {
		rect = new Rectangle(vectorPos.get(0)-width/2, vectorPos.get(1)-height/2,width,height);  // Creates our gorilla
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
			Projectile banana = new Projectile(vectorPos.get(0)+width/2, vectorPos.get(1), angle, speed);
		}else {
			Projectile banana = new Projectile(vectorPos.get(0)+height/2, (double)vectorPos.get(1), 180-angle, speed);
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
				startPosX = event.getSceneX() - shape.getTranslateX();
				startPosY = event.getSceneY() - shape.getTranslateY();
			});

			// Sets the new position to the shape when the mouse is dragged
			shape.setOnMouseDragged(event -> {
				shape.setTranslateX(event.getSceneX()- startPosX);
				shape.setTranslateY(event.getSceneY() - startPosY);
			});


			// sets the moveable back to false when released
			shape.setOnMouseReleased(event -> {

				/*
				 * @TODO: Check for collisions
				 */


				moveable = false;
			});

		}
	}

































}
