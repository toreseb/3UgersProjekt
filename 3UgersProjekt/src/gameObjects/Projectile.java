package gameObjects;

import framework.*;
import javafx.application.Platform;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * Projectile is an abstract class that extends the abstract class GameObject.
 * 
 * This class is the framework for a projectile.
 *
 * By: Embla
 */
public abstract class Projectile extends GameObject {
	private double xSpeed;
	private double ySpeed;
	double g = 9.82;
	int count = 0;
	public static int width = 20;
	public static int height = 20;

	protected Image banana = new Image("Banana.png");
	protected Image hand = new Image("GorillaHand.png");
	protected ImageView viewHand;

	/*
	 * Projectile()
	 * 
	 * Constructor for the class.
	 * 
	 * Calls the constructor in GameObject.
	 * 
	 * Parses speeds and mouse position and uses them to set speed in the x- and
	 * y-direction and the angle.
	 */
	public Projectile(double posX, double posY, double xSpeed, double ySpeed) {
		super(posX - width / 2, posY, 2, 2);
		this.xSpeed = xSpeed / 20;
		this.ySpeed = -ySpeed / 20;
		int angle = 360; // rotation
		if (xSpeed < 0)
			angle *= -1;
		initAnimation(angle);
	}

	/*
	 * step()
	 * 
	 * Calls step() in GameObject.
	 * 
	 * Calculates where the projectiles next position is and whether or not
	 * something is hit.
	 * 
	 * If something is hit, do things, and go to next players turn.
	 */
	public void step() {
		super.step();
		ySpeed -= g / 60; // calc new ySpeed from acceleration - 60 frames per second
		vectorPos.set(0, (vectorPos.get(0) + xSpeed));
		vectorPos.set(1, (vectorPos.get(1) + ySpeed));

		outOfSight(); // calls function that check if banana is out of sight and then shows a pointing
						// GorillaHand

		// Check if level part is hit
		boolean goToNextPlayer = false;

		for (GameObject gO : Main.objList) {
			if (this != gO) {
				if (objectCollision(gO) && PowerUp.class.isAssignableFrom(gO.getClass())) {
					((PowerUp) gO).collected();
				}
				if (objectCollision(gO) && gO.getClass().getSimpleName().equals("Gorilla") && !((Gorilla) gO).isDead
						&& Main.pList.get(Main.cPlayer).id != gO.id) {
					Gorilla p = (Gorilla) gO;
					// calls the playerHit function which is different to each of the different
					// projectiles
					playerHit(p);
					explosion(vectorPos.get(0) + width / 2, vectorPos.get(1));
					// william
					if (p.curNumLife <= 0) {
						p.isDead = true;
					}
					boolean anyAlive = false;

					for (Gorilla g : Main.pList) {
						if (!g.isDead && Main.pList.get(Main.cPlayer) != g) {
							anyAlive = true;
						}
					}
					if (!anyAlive) {
						GameOver.endGame();
						this.deleteObject();
					} else {
						goToNextPlayer = true;
					}

				} else if (objectCollision(gO) && LevelPart.class.isAssignableFrom(gO.getClass())) {
					explosion(vectorPos.get(0) + width / 2, vectorPos.get(1));
					goToNextPlayer = true;
				}
			}
		}

		if (goToNextPlayer) {
			nextPlayer();
		}
	}

	/*
	 * collision()
	 * 
	 * Overwrites collision from GameObject.
	 * 
	 * Calls nextPlayer().
	 */
	public void collision() {
		if (vectorPos.get(1) <= 0) {
			nextPlayer();
		}
		if (vectorPos.get(0) < 0) {
			vectorPos.set(0, (double) 0);

			// Makes the projectile bounce off walls.
			// xSpeed = -xSpeed;
		}
		if (vectorPos.get(0) + width > Main.n) {
			vectorPos.set(0, (double) Main.n - width);

			// Makes the projectile bounce off walls.
			// xSpeed = -xSpeed;
		}

	}

	/*
	 * outOfSight()
	 * 
	 * Makes a pointing hand appear in the top of the screen to show where the
	 * projectile is if it is above the screen.
	 * 
	 * By: Embla
	 */
	public void outOfSight() {
		// If it is above the screen: show the hand and move it with the projectile
		if (vectorPos.get(1) > Main.m) {
			if (viewHand == null) {
				viewHand = new ImageView(hand);
				viewHand.setX(vectorPos.get(0));
				viewHand.setY(0);
				Main.mainRoot.getChildren().add(viewHand);
			} else {
				viewHand.setX(vectorPos.get(0));
				viewHand.setY(0);
			}
		} else if (vectorPos.get(1) < Main.m && viewHand != null) {
			// Get rid of hand
			Main.mainRoot.getChildren().remove(viewHand);
			viewHand = null;
		}
	}

	/*
	 * nextPlayer()
	 * 
	 * Counts up cPlayer. If cPlayer becomes higher that the amount of players it is
	 * set to 0. If cPlayer is dead, cPlayer is counted up again until it isn't.
	 * 
	 * Calls startTurn for next player and deletes projectile.
	 */
	protected void nextPlayer() {
		Main.cPlayer++;
		if (Main.cPlayer > Main.pList.size() - 1) {
			Main.cPlayer = 0;
		}
		while (Main.pList.get(Main.cPlayer).isDead) {
			Main.cPlayer++;
			if (Main.cPlayer > Main.pList.size() - 1) {
				Main.cPlayer = 0;
			}
		}
		PlayerTurn.startTurn(Main.cPlayer);

		this.deleteObject();
	}

	/*
	 * initShape()
	 * 
	 * Sets image of projectile, formats it and shows it.
	 * 
	 * Calls initShape() in GameObject.
	 */
	protected void initShape() {
		ImageView imageView = new ImageView(banana);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		imageView.setX(0);
		imageView.setY(0);

		groupShape.getChildren().add(imageView);
		super.initShape();
	}

	/*
	 * initAnimation()
	 * 
	 * Rotates the image of the projectile.
	 */
	void initAnimation(int angle) {
		// rotate
		RotateTransition rotate = new RotateTransition();
		rotate.setNode(groupShape);
		rotate.setDuration(Duration.millis(1000));
		rotate.setCycleCount(TranslateTransition.INDEFINITE);
		rotate.setInterpolator(Interpolator.LINEAR);
		rotate.setByAngle(angle);
		rotate.play();
	}

	/*
	 * explosion()
	 * 
	 * Run explosion via multithreading.
	 * 
	 * By: Embla & Tore
	 */
	public static void explosion(double x, double y) {
		int size = 100;
		Image bang = new Image("Bang.png");
		ImageView imageView = new ImageView(bang);
		imageView.setFitWidth(size);
		imageView.setFitHeight(size);
		imageView.setX(x - size / 2);
		imageView.setY(Main.m - y - size / 2);
		Main.mainRoot.getChildren().add(imageView);

		//Creates thread
		new Thread(() -> {
			try {
				//Thread stops itself
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Tell application to run this
			Platform.runLater(() -> {
				imageView.setImage(null);
				Main.mainRoot.getChildren().remove(imageView);
			});

		}).start();

	}

	// playerHit() gets defined by the specific projectile.
	public abstract void playerHit(Gorilla p);

}
