package gameObjects;

import framework.*;
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
	public int point;
	public static int numLife = 3;
	public boolean moveable = true;

	private Rectangle rect;
	public ArrayList<Image> hearts = new ArrayList<>();
	private static Image heart = new Image("Heart.png");
	// private ImageView helth = new ImageView(heart);

	public Projectile banana;

	// Constructor
	public Gorilla(int posX) {
		super(posX, 0, width, height);
		point = 0;
		moveable = false;
		numLife = 3;
		this.vectorPos.set(1,
				(double) (Main.cLevel.maxHeightAtLocation(((int) (double) this.vectorPos.get(0)), width) + height));

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
		Main.mainRoot.getChildren().add(line); //Her bruges mainRoot, da den skal tegne oven på hele billedet
		Main.mainRoot.setOnMouseMoved(event -> {
			double c = Math.sqrt((xBegin - event.getSceneX()) * (xBegin - event.getSceneX())
					+ (yBegin - event.getSceneY()) * (yBegin - event.getSceneY()));
			if (c < maxThrow) {
				line.setEndX(event.getSceneX()); // move line end to follow the mouse
				line.setEndY(event.getSceneY());
			} else {
				line.setEndX(xBegin - ((xBegin - event.getSceneX()) / c) * maxThrow); // move line end to follow the mouse
				line.setEndY(yBegin - ((yBegin - event.getSceneY()) / c) * maxThrow);
			}
		});
		Main.mainRoot.setOnMousePressed(event -> {

			if (banana != null)
				return; // if there is already a banana, return

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
		
			banana = new Projectile(vectorPos.get(0) + width / 2, vectorPos.get(1), xSpeed, ySpeed);
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

		// sets the moveable back to false when released
		shape.setOnMouseReleased(event -> {
			if (moveable) {
				this.vectorPos.set(1,
						(double) (Main.cLevel.maxHeightAtLocation(((int) (double) this.vectorPos.get(0)), width)
								+ height));
				moveable = false;
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
			health.setLayoutX((i * size));
			groupShape.getChildren().add(health);
			i++;
		}
	}

}
