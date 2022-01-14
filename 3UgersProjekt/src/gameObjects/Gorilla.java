package gameObjects;

import framework.*;
import gameObjects.Projectiles.*;

import java.util.ArrayList;
import javafx.event.Event;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
	public static final int width = 40;
	public static final int height = 40;
	public int point, numLife, curNumLife;
	public boolean moveable, hasPow, frozen;
	public Projectile banana;
	public PowerUp pow;
	public Group lifeBar = new Group();
	public ArrayList<Image> hearts = new ArrayList<>();
	private Image heart = new Image("Heart.png");


	// Constructor
	public Gorilla(int posX) {
		super(posX, 0, width, height);
		this.vectorPos.set(1,(double) (Main.cLevel.maxHeightAtLocation(((int) (double) this.vectorPos.get(0)), width) + height));
		point = 0;
		numLife = 3;
		curNumLife = numLife;
		moveable = false;
		hasPow = false;
		frozen = false;
		
		// Adds the right amount of life to the list.
		for (int i = 0; i < numLife; i++) {
			hearts.add(heart);
		}

		drawHearts(); // draws the init hearts
		groupShape.getChildren().add(lifeBar);
		Main.pList.add(this);
		step();
	}

	/*
	 * Implementation of the must have functions from GameObject: drawShape()
	 * initShape() step()
	 */
	@Override
	public void step() {
		super.step();

	}

	@Override
	void initShape() {
		Image gorillaImg = new Image("Gorilla.png");
		ImageView gorilla = new ImageView(gorillaImg);
		gorilla.setFitHeight(height);
		gorilla.setFitWidth(width);
		groupShape.getChildren().add(gorilla);
	}

	/*
	 * thowBanana connects gorilla and mouse until with a line and throws banana
	 * when a mouse button is pressed
	 *
	 * by: Embla Peulicke
	 */
	public void throwBanana(int cPlayer) {

		int maxThrow = 200;
		double xBegin = vectorPos.get(0) + width / 2; // gorilla center coordinates
		double yBegin = Main.m - (vectorPos.get(1) - width / 2);
		Line line = new Line(xBegin, yBegin, xBegin, yBegin); // draw line: begins and ends in gorilla center
		Main.mainRoot.getChildren().add(line); // Her bruges mainRoot, da den skal tegne oven på hele billedet
		Main.mainRoot.setOnMouseMoved(event -> {
			double c = Math.sqrt((xBegin - event.getSceneX()) * (xBegin - event.getSceneX())
					+ (yBegin - event.getSceneY()) * (yBegin - event.getSceneY()));
			if (c < maxThrow) {
				line.setEndX(event.getSceneX()); // move line end to follow the mouse
				line.setEndY(event.getSceneY());
			} else {
				line.setEndX(xBegin - ((xBegin - event.getSceneX()) / c) * maxThrow); // move line end to follow the
																						// mouse
				line.setEndY(yBegin - ((yBegin - event.getSceneY()) / c) * maxThrow);
			}
		});
		Main.mainRoot.setOnMousePressed(event -> {

			if (banana != null) return; // if there is already a banana, return

			Main.mainRoot.getChildren().remove(line); // else remove the line and make a banana
			double xEnd = event.getSceneX();
			double yEnd = event.getSceneY();
			double c = Math.sqrt((xBegin - event.getSceneX()) * (xBegin - event.getSceneX())
					+ (yBegin - event.getSceneY()) * (yBegin - event.getSceneY()));
			if (c < maxThrow) {
				xEnd = event.getSceneX(); // move line end to follow the mouse
				yEnd = event.getSceneY();
			} else {
				xEnd = xBegin - ((xBegin - event.getSceneX()) / c) * maxThrow; // move line end to follow the mouse
				yEnd = yBegin - ((yBegin - event.getSceneY()) / c) * maxThrow;
			}
			double xSpeed = xEnd - xBegin;
			double ySpeed = yEnd - yBegin;

			// @TODO Lav forskellige projektiles, og skriv ind her.
			banana = new Banana(vectorPos.get(0) + width / 2, vectorPos.get(1), xSpeed, ySpeed);
		});

	}

	/*
	 * Enables for moving the gorilla
	 *
	 * by: William Holberg
	 */
	private double startPosX, startPosY;
	public void moveGorilla(Group shape) {
		shape.setOnMouseEntered(event -> {
			if (moveable) {
				shape.setCursor(Cursor.HAND);
			}
		});

		// Sets the new position to the shape when the mouse is dragged
		shape.setOnMouseDragged(event -> {
			if (moveable) {
				vectorPos.set(0, event.getSceneX() - width / 2);
				vectorPos.set(1, Main.m - event.getSceneY() + height / 2);
			}
		});

		// sets the moveable back to false and removes prompt when released
		shape.setOnMouseReleased(event -> {
			if (moveable) {
				this.vectorPos.set(1,
						(double) (Main.cLevel.maxHeightAtLocation(((int) (double) this.vectorPos.get(0)), width)
								+ height));

				moveable = false;
				if (!Main.pList.get(Main.cPlayer).moveable) {
					Main.frameworkRoot.getChildren().remove(PlayerTurn.root); // Removes the prompt
				}

				shape.setCursor(Cursor.DEFAULT);

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
		int i = 0;
		for (Image image : hearts) {
			ImageView health = new ImageView(image);
			double size = height / numLife;
			health.setLayoutY(-size);
			health.setFitHeight(size);
			health.setFitWidth(size);
			health.setLayoutX((i * size) + 2);
			lifeBar.getChildren().add(health);
			i++;
		}
	}

}
