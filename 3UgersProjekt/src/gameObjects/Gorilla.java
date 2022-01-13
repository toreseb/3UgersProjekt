package gameObjects;

import framework.*;
import java.util.ArrayList;
import javafx.event.Event;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.*;

/**
 * The function of this class is: Create the gorilla Draw the gorilla Enables
 * the gorilla to throw the banana
 *
 * by: William Holberg
 */

public class Gorilla extends GameObject {

	// Fields
	public static final int width = Main.n / 15;
	public static final int height = Main.n / 15;
	public int point, numLife;
	public boolean moveable = true;

	private Rectangle rect;
	private ArrayList<Image> hearts = new ArrayList<>();
	private Image heart = new Image("Heart.png");
	//private ImageView helth = new ImageView(heart);

	// Constructor
	public Gorilla(int posX) {
		super(posX, 0, width, height);
		point = 0;
		moveable = false;
		numLife = 3;
		this.vectorPos.set(1, (double) (Main.cLevel.maxHeightAtLocation(((int) (double) this.vectorPos.get(0)), width) + height));
		
		for (int i = 0; i < numLife; i++) {
			hearts.add(heart);
		}
		
		drawHearts();
		step();
	}

	/*
	 * Implementation of the must have functions from GameObject: drawShape()
	 * initShape() step()
	 */

	@Override
	public void step() {
		super.step();

	} // This class is not used here

	@Override
	void initShape() {
		rect = new Rectangle(0, 0, width, height); // Creates our gorilla
		rect.setFill(Color.BROWN);
		groupShape.getChildren().add(rect);
	}

	/*
	 * Other functions that the gorilla have throwBanana() moveGorilla()
	 */
	public void throwBanana(double angle, double speed) {
		if (Main.cPlayer == 0) {
			Projectile banana = new Projectile(vectorPos.get(0) + width / 2, vectorPos.get(1), -angle, speed);
		} else {
			Projectile banana = new Projectile(vectorPos.get(0) + height / 2, (double) vectorPos.get(1), 180 + angle,
					speed);
		}
	}

	// Enables for moving the gorilla
	private double startPosX, startPosY;

	public void moveGorilla(Group shape) {
		shape.setOnMouseEntered(event -> {
			if (moveable) {
				shape.setCursor(Cursor.HAND);
				System.out.println("hey1");
			}
		});

		// Sets the new position to the shape when the mouse is dragged
		shape.setOnMouseDragged(event -> {
			if (moveable) {
				vectorPos.set(0, event.getSceneX() - width / 2);
				vectorPos.set(1, Main.m - event.getSceneY() + height / 2);
				System.out.println("hey2");
			}
		});

		// sets the moveable back to false when released
		shape.setOnMouseReleased(event -> {
			if (moveable) {
				this.vectorPos.set(1,
						(double) (Main.cLevel.maxHeightAtLocation(((int) (double) this.vectorPos.get(0)), width)
								+ height));
				moveable = false;
				System.out.println("hey3");
				Main.cPlayer++;// The player changes when the projectile hits the ground
				if (Main.cPlayer > Main.pList.size() - 1) {
					Main.cPlayer = 0;
				}
				event.consume();
				PlayerTurn.startTurn(Main.cPlayer);
			}
		});
	}
	
	
	public void drawHearts() {
		for(int i = 0; i < hearts.size(); i++) {
			ImageView health = new ImageView(hearts.get(i));
			health.setLayoutY(-20);
			health.setFitHeight(27);
			health.setFitWidth(27);
			health.setLayoutX((i*30));
			groupShape.getChildren().add(health);
		}
	}
	
}
