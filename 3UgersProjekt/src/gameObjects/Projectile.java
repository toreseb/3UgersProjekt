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
 * Embla har skrevet denne class
 *
 */
public class Projectile extends GameObject {
	private double xSpeed;
	private double ySpeed;
	double g = 9.82;
	int count = 0;
	public static int width = Main.n/30;
	public static int height = Main.n/30;

	Image banana = new Image("BananaNew.png");
	ImageView imageView = new ImageView(banana);
	

	public Projectile(double posX, double posY, double direction, double speed) {
		super(posX, posY, 2, 2);
		
        int angle = 360; 				// rotation
        if (direction>90) angle *=-1;	//clockwise or counterclockwise rotation
		
		direction = Math.toRadians(direction);	// converts from degrees to radians
		xSpeed = Math.cos(direction) * speed; 	// calculating x speed
		ySpeed = -Math.sin(direction) * speed; 	// calculation y begin speed

		initAnimation(angle);
	}

	public void step() {
		super.step();
		ySpeed -= g / 60; // calc new ySpeed from acceleration - 60 frames per second
		vectorPos.set(0, (vectorPos.get(0) + xSpeed));
		vectorPos.set(1, (vectorPos.get(1) + ySpeed));
	}

	@Override
	public void collision() {
		if (vectorPos.get(0) < 0) {
			vectorPos.set(0,(double) 0);
			//xSpeed = -xSpeed;
		}
		if (vectorPos.get(0) + width > Main.n) {
			vectorPos.set(0, (double)Main.n - width);
			//xSpeed = -xSpeed;
		}
		if (vectorPos.get(1) + height / 2 < 0) {
			vectorPos.set(1, (double)Main.m - height / 2);
			xSpeed = 0;
			Gorilla gorilla = Main.pList.get((Main.cPlayer+1) % 2);
			if (gorilla.vectorPos.get(0) - vectorPos.get(0) < Main.n / 50
					&& gorilla.vectorPos.get(0) - vectorPos.get(0) > - Main.n / 50) {
				Main.pList.get(Main.cPlayer).point ++;
			}
			Main.cPlayer++;//The player changes when the projectile hits the ground
			if(Main.cPlayer > Main.pList.size()-1) {
				Main.cPlayer = 0;
			}
			PlayerTurn.startTurn(Main.cPlayer);
			//Main.mainStage.getScene().getWindow().setWidth(Main.mainStage.getScene().getWidth() + 14);
			this.deleteObject();
		}
	}

	@Override
	void initShape() {
		Image banana = new Image("BananaNew.png");
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
        translate.play();
        translate.setByY(-height);
        
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
