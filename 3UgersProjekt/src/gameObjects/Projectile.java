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

	Image banana = new Image("Banana.png");
	ImageView imageView = new ImageView(banana);
	

	public Projectile(int posX, int posY, double direction, double speed) {

		super(posX, posY, 2, 2);
		
        int angle = 360; 				// rotation
        if (direction>90) angle *=-1;	//clockwise or counterclockwise rotation
		
		direction = Math.toRadians(direction);	// converts from degrees to radians
		xSpeed = Math.cos(direction) * speed; 	// calculating x speed
		ySpeed = -Math.sin(direction) * speed; 	// calculation y begin speed

		// set size of banana image
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        
        // translate banana origin to center
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(imageView);
        translate.setDuration(Duration.millis(1));
        translate.setByX(-width/2);
        translate.play();
        translate.setByY(-height/2);
        
        // rotate
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(imageView);
        rotate.setDuration(Duration.millis(1000));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(angle);
        rotate.play();
        
	}

	@Override
	public void drawShape(Group root) {
		imageView.setX((int)vectorPos.get(0));
        imageView.setY((int)vectorPos.get(1));
        
		root.getChildren().add(imageView);
	}

	public void step() {
		ySpeed += g / 60; // calc new ySpeed from acceleration - 60 frames per second
		vectorPos.set(0, (int) (vectorPos.get(0) + xSpeed));
		vectorPos.set(1, (int) (vectorPos.get(1) + ySpeed));
	}

	@Override
	public void collision() {
		if (vectorPos.get(0) - width / 2 < 0) {
			vectorPos.set(0, 0 + width / 2);
			//xSpeed = -xSpeed;
		}
		if (vectorPos.get(0) + width / 2 > Main.n) {
			vectorPos.set(0, Main.n - width / 2);
			//xSpeed = -xSpeed;
		}
		if (vectorPos.get(1) + height / 2 > Main.m) {
			vectorPos.set(1, Main.m - height / 2);
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
		// TODO Auto-generated method stub
		
	}
}
