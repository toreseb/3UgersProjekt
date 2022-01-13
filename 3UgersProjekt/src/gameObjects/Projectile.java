package gameObjects;

import framework.*;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Projectil: shows the banana when is is thrown and simulates projectile motion
 * until the banana collides
 * 
 * By: Embla Peulicke
 *
 */
public abstract class Projectile extends GameObject {
	private double xSpeed;
	private double ySpeed;
	double g = 9.82;
	int count = 0;
	public static int width = Main.n / 30;
	public static int height = Main.n / 30;

	protected Image banana = new Image("BananaNew.png");
	ImageView imageView = new ImageView(banana);

	public Projectile(double posX, double posY, double xSpeed, double ySpeed) {
		super(posX, posY, 2, 2);
		this.xSpeed = xSpeed / 20;
		this.ySpeed = -ySpeed / 20;
		int angle = 360; // rotation
		initAnimation(angle);
	}

	public void step() {
		super.step();
		ySpeed -= g / 60; // calc new ySpeed from acceleration - 60 frames per second
		vectorPos.set(0, (vectorPos.get(0) + xSpeed));
		vectorPos.set(1, (vectorPos.get(1) + ySpeed));

		collision();

		// Check if level part is hit
		for (LevelPart lp : Main.cLevel.parts) {
			
			if (objectCollision(lp)) {
				System.out.println("Hit Ground");
				nextPlayer();
			}
		}
		// Check if player is hit
		for (Gorilla p : Main.pList) {
			if (Main.pList.get(Main.cPlayer).id != p.id) {
				if (objectCollision(p)) {
					System.out.println("Damage Dealt");
					nextPlayer();
				}
			}
		}
		// Check if power up is hit

		for (PowerUp pow : Main.cLevel.powerUps) {
			if (objectCollision(pow)) {
				System.out.println("Collected Powerup");
				pow.collected();
				
			}

		}

	}

	@Override
	public void collision() {
		if (vectorPos.get(0) < 0) {
			vectorPos.set(0, (double) 0);
			// xSpeed = -xSpeed;
		}
		if (vectorPos.get(0) + width > Main.n) {
			vectorPos.set(0, (double) Main.n - width);
			// xSpeed = -xSpeed;
		}

	}

	private void nextPlayer() {
		Main.cPlayer++;
		if (Main.cPlayer > Main.pList.size() - 1) {
			Main.cPlayer = 0;
		}

		PlayerTurn.startTurn(Main.cPlayer);
		this.deleteObject();
		// vis bang.png her i lidt tid
	}

	@Override
	protected void initShape() {
		
		

		ImageView imageView = new ImageView(banana);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		imageView.setX(0);
		imageView.setY(0);

		groupShape.getChildren().add(imageView);
	}

	void initAnimation(int angle) {

		// translate banana origin to center
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(groupShape);
		translate.setDuration(Duration.millis(1));
		translate.setByX(-width);
		translate.setByY(-height);
		translate.play();

		// rotate
		RotateTransition rotate = new RotateTransition();
		rotate.setNode(groupShape);
		rotate.setDuration(Duration.millis(1000));
		rotate.setCycleCount(TranslateTransition.INDEFINITE);
		rotate.setInterpolator(Interpolator.LINEAR);
		rotate.setByAngle(angle);
		rotate.play();
	}
}
