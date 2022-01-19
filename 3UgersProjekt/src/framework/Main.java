package framework;

import java.util.ArrayList;
import java.util.Timer;
import gameObjects.*;
import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.Duration;

/**
 * Tore og Helene har skrevet denne klasse
 *
 *
 */

public class Main extends Application {
	public static int n, m;// Width and Height

	public static boolean showHitbox = false;

	public static Stage mainStage;
	// public static Group root2 = new Group();

	public static int startSizeW= 650;
	public static int startSizeH= 390;

	public static String levelName;
	public static Level cLevel;

	public static AnimationTimer timer;

	public static ArrayList<GameObject> objList = new ArrayList<GameObject>();
	public static ArrayList<GameObject> delList = new ArrayList<GameObject>();

	public static ArrayList<Gorilla> pList = new ArrayList<Gorilla>();

	public static int cPlayer = 0;

	public static int pAmount;
	public static ArrayList<String> nList = new ArrayList<String>();

	public static Group gameRoot = new Group(); // @TEST
	public static Group frameworkRoot = new Group(); // @TEST
	public static Group mainRoot = new Group(); // @TEST

	public static Image background;
	public static ImageView imageView;

	/*
	 * start()
	 *
	 * Creates the stage and calls the different, variable-defining scenes.
	 *
	 * By: Helene Moesgaard.
	 */
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		mainStage.setTitle("Gorillas");
		mainStage.setResizable(false);

		//Center on screen
		mainStage.setX((Screen.getPrimary().getVisualBounds().getWidth()- startSizeW)/2);
		mainStage.setY((Screen.getPrimary().getVisualBounds().getHeight()- startSizeH)/2);

		mainRoot.getChildren().addAll(frameworkRoot, gameRoot);

		// Window icon
		Image icon = new Image("Banana.png");
		mainStage.getIcons().add(icon);

		//SetupScenes.windowSize1(mainStage);
		SetupScenes.windowSize();

		mainStage.show();
	}

	// Main method
	public static void main(String[] args) {
		launch(args);
	}

	static void run() {
		// gameRoot.getChildren().clear();

		for (GameObject gO : objList) {
			gO.run();
		}
		if(SpawnPowerup.newPowerUp) {
			SpawnPowerup.spawnPower();
			SpawnPowerup.newPowerUp = false;
		}
		clearLists();
	}

	public static void initMain() {
		initTimer();

		cLevel = new Level(n, m);
		for(int i = 0; i< pAmount; i++) {
			new Gorilla((int)(((double)Main.n/pAmount)*(i+0.5)));
		}

		// Call turn
		PlayerTurn.startTurn(0);
	}

	static void initTimer() {
		timer = new AnimationTimer() {

			private long lastToggle;

			@Override
			public void handle(long now) {
				if (lastToggle == 0L) {
					lastToggle = now;
				} else {
					long diff = now - lastToggle;
					if (diff >= 16_000_000L) { // 500,000,000ns == 500ms
						Main.run();
						lastToggle = now;
					}
				}
			}
		};
		timer.start();
	}

	private static void clearLists() {
		for (GameObject gameObject : delList) {
			objList.remove(gameObject);
		}
	}

	public static void fullClearLists() {
		objList.clear();
		cLevel = null;
	}
}
