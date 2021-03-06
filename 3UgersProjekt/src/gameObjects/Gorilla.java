package gameObjects;

import framework.*;
import gameObjects.Projectiles.*;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.scene.image.*;

/**
 * The function of this class is: Create the gorilla Draw the gorilla Enables
 * the gorilla to throw the banana
 *
 * by: William
 */

public class Gorilla extends GameObject {

	// Fields
	public static final int width = 40;
	public static final int height = 40;
	public int point, numLife, curNumLife, frozen, slimed;
	public boolean moveable, normalImage, isDead = false;;
	public Projectile banana;
	public String hasPow;
	public Image gorillaImg;
	public Image gorillaThrowImg; 

	public String name;

	public Group lifeBar = new Group();
	public static Image heart = new Image("Heart.png");
	public ImageView gorilla;
	private boolean right = true;

	/*
	 * Gorilla()
	 * 
	 * Constructor for Gorilla.
	 * 
	 * Calls the constructor in GameObject.
	 * 
	 * Sets variables, draws hearts, sets name, and adds to pList.
	 */
	public Gorilla(int posX) {
		super(posX, height, width, height);
		numLife = 3;
		curNumLife = numLife;
		point = 0;
		frozen = 0;
		slimed = 0;
		moveable = false;
		normalImage = true;
		hasPow = "no";

		drawHearts(); // draws the init hearts
		groupShape.getChildren().add(lifeBar);

		Main.pList.add(this);

		name = Main.nList.get(Main.pList.indexOf(this));
		displayName();
		
		if (Main.pList.indexOf(this) == 1) rotate();
	}


	/*
	 * Implementation of the must have functions from GameObject: drawShape()
	 * initShape() step()
	 */
	
	/*
	 * step()
	 * 
	 * Handles the step of the gorilla.
	 * 
	 * By: Tore
	 */
	public void step() {
		if(vectorPos.get(1) <= height+10) {
			toTop();
		}
		if(isDead) {
			Main.gameRoot.getChildren().remove(groupShape);
			Main.pList.remove(this);
		}
		
		super.step();
	}

	/*
	 * initShape()
	 * 
	 * Sets and formats images of gorilla and calls initShape() in GameObject.
	 * 
	 * By: tore og William
	 */
	protected void initShape() {
		gorillaImg = new Image(ClassLoader.getSystemResource("Gorilla.png").toString());
		gorillaThrowImg = new Image(ClassLoader.getSystemResource("GorillaThrow.png").toString());
		gorilla = new ImageView(gorillaImg);
		gorilla.setFitHeight(height);
		gorilla.setFitWidth(width);
		groupShape.getChildren().add(gorilla);
		super.initShape();
	}

	/*
	 * thowBanana connects gorilla and mouse until with a line and throws banana
	 * when a mouse button is pressed
	 *
	 * rotate() turns the gorilla towards the mouse
	 *
	 * by: Embla Peulicke
	 */
	public void throwBanana(int cPlayer) {
		int maxThrow = 200;
		double xBegin = vectorPos.get(0) + width / 2; // gorilla center coordinates
		double yBegin = Main.m - (vectorPos.get(1) - width / 2);
		
		// Makes line to show aim of shot.
		Line line = new Line(xBegin, yBegin, xBegin, yBegin); // draw line: begins and ends in gorilla center
		Main.mainRoot.getChildren().add(line); 

		gorilla.setImage(gorillaThrowImg);
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
			if (xBegin > event.getSceneX() && right == true || xBegin < event.getSceneX() && right == false) {
				if (banana == null) {
					rotate();
				}
			}
		});
		Main.mainRoot.setOnMousePressed(event ->

		{
			if (banana != null)
				return; // if there is already a banana, return

			gorilla.setImage(gorillaImg);

			Main.mainRoot.getChildren().remove(line); // else remove the line and make a banana
			double xEnd = event.getSceneX();
			double yEnd = event.getSceneY();
			double c = Math.sqrt((xBegin - event.getSceneX()) * (xBegin - event.getSceneX())
					+ (yBegin - event.getSceneY()) * (yBegin - event.getSceneY()));
			if (c < maxThrow) {
				xEnd = event.getSceneX();
				yEnd = event.getSceneY();
			} else {
				xEnd = xBegin - ((xBegin - event.getSceneX()) / c) * maxThrow;
				yEnd = yBegin - ((yBegin - event.getSceneY()) / c) * maxThrow;
			}
			double xSpeed = xEnd - xBegin;
			double ySpeed = yEnd - yBegin;

			// Find the right type of projectile
			if (hasPow.equals("ice")) {
				banana = new Ice(vectorPos.get(0) + width / 2, vectorPos.get(1), xSpeed, ySpeed);
				hasPow = "no";
			} else if (hasPow.equals("slime")) {
				banana = new Slime(vectorPos.get(0) + width / 2, vectorPos.get(1), xSpeed, ySpeed);
				hasPow = "no";
			} else if (hasPow.equals("anvil")) {
				banana = new Anvil(vectorPos.get(0) + width / 2, vectorPos.get(1), xSpeed, ySpeed);
				hasPow = "no";
			} else {
				banana = new Banana(vectorPos.get(0) + width / 2, vectorPos.get(1), xSpeed, ySpeed);
			}
		});
	}

	/*
	 * rotate()
	 * 
	 * Rotates player to face the direction of the mouse while lining up their shot.
	 * 
	 * By: Embla
	 */
	public void rotate() {
		RotateTransition rotate = new RotateTransition();
		rotate.setNode(gorilla);
		rotate.setDuration(Duration.millis(1));
		rotate.setInterpolator(Interpolator.LINEAR);
		rotate.setByAngle(180);
		rotate.setAxis(Rotate.Y_AXIS);
		rotate.play();
		right = !right;
	}

	/*
	 * Enables for moving the gorilla
	 *
	 * by: William
	 */
	private double startPosX, startPosY;

	public void moveGorilla(Group shape) {
		gorilla.setImage(gorillaImg);
		shape.setOnMouseEntered(event -> {
			if (moveable) {
				shape.setCursor(Cursor.HAND);
			}
		});
		
		shape.setOnMousePressed(event -> {
			startPosX = event.getSceneX();
			startPosY = event.getSceneY();
		});
		

		// Sets the new position to the shape when the mouse is dragged
		shape.setOnMouseDragged(event -> {
			if (moveable) {
				if(Math.abs((event.getSceneX()-startPosX))<(1/4d)*Main.n) {
					vectorPos.set(0, event.getSceneX() - width / 2);
				}
				vectorPos.set(1, Main.m - event.getSceneY() + height / 2);
				
			}
		});

		// sets the moveable back to false and removes prompt when released
		shape.setOnMouseReleased(event -> {
			if (moveable) {
				vectorPos.set(1,1d);
				super.step();
				toTop();

				moveable = false;
				if (!Main.pList.get(Main.cPlayer).moveable) {
					Main.frameworkRoot.getChildren().remove(PlayerTurn.root); // Removes the prompt
					
					//Update image
					gorilla.setImage(gorillaImg);
				}

				shape.setCursor(Cursor.DEFAULT);
				Main.cPlayer++;
				if (Main.cPlayer > Main.pList.size() - 1) {
					Main.cPlayer = 0;
				}
				while(Main.pList.get(Main.cPlayer).isDead) {
					Main.cPlayer++;
					if (Main.cPlayer > Main.pList.size() - 1) {
						Main.cPlayer = 0;
					}
				}
				event.consume();
				PlayerTurn.startTurn(Main.cPlayer);
			}
		});
	}

	/*
	 * drawHearts()
	 * 
	 * Draws the hearts above a player.
	 * 
	 * By: William, Tore & Embla
	 */
	public void drawHearts() {
		this.lifeBar.getChildren().clear();
		for (int i=0; i<curNumLife; i++) {
			ImageView health = new ImageView(heart);
			double size = height / numLife;
			health.setLayoutY(-size);
			health.setFitHeight(size);
			health.setFitWidth(size);
			health.setLayoutX((i * size) + 2);
			lifeBar.getChildren().add(health);
		}
	}
	
	/*
	 * displayName()
	 * 
	 * Sets and places name above gorilla.
	 * 
	 * By: Embla
	 */
	public void displayName() {
		Text nameText = new Text(name);
		nameText.setX(width/2-nameText.getLayoutBounds().getWidth() / 2);
		nameText.setY(-20);
		groupShape.getChildren().add(nameText);
	}

	/*
	 * toTop()
	 * 
	 * Places the gorilla on top of level parts.
	 * 
	 * By: Tore
	 */
	private void toTop() {
		for (GameObject gO : Main.objList) {
			if(LevelPart.class.isAssignableFrom(gO.getClass())) {
				while(objectCollision(gO)) {
					vectorPos.set(1,vectorPos.get(1)+1);
					groupShape.setTranslateX(vectorPos.get(0));
					groupShape.setTranslateY(Main.m - vectorPos.get(1));
				}
			}
		}
	}
}
