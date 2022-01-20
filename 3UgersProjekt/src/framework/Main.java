package framework;

import java.util.ArrayList;
import gameObjects.*;
import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.*;

/**
 * This class contains the functions: 
 * - main() 
 * - start() 
 * - run() 
 * - initMain() 
 * - initTimer 
 * - clearLists 
 * - fullClear lists
 * 
 * This class starts and runs the game.
 *
 * By: Tore & Helene
 */

public class Main extends Application {
	public static int n, m;// Width and Height

	public static boolean showHitbox = false;

	public static Stage mainStage;

	public static int startSizeW = 650;
	public static int startSizeH = 390;

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
	 * main()
	 * 
	 * This function calls launch() which opens the JavaFX window and keeps it
	 * running. It also closes the program.
	 */
	public static void main(String[] args) {
		launch(args);
		System.exit(0);
	}

	/*
	 * start()
	 *
	 * Creates the stage and calls the first variable-defining function.
	 *
	 * By: Helene Moesgaard.
	 */
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		mainStage.setTitle("Gorillas");
		mainStage.setResizable(false);

		// Center on screen
		mainStage.setX((Screen.getPrimary().getVisualBounds().getWidth() - startSizeW) / 2);
		mainStage.setY((Screen.getPrimary().getVisualBounds().getHeight() - startSizeH) / 2);

		// Window icon
		Image icon = new Image("Banana.png");
		mainStage.getIcons().add(icon);
		
		SetupScenes.windowSize();

		mainStage.show();
	}

	/*
	 * run()
	 * 
	 * This function is called by the timer (every frame) and handles everything
	 * that needs to happen this often.
	 * 
	 * By: Tore
	 */
	static void run() {

		// Every object in objLists run() function is called
		for (GameObject gO : objList) {
			gO.run();
		}

		// If it is time to spawn a new power up, it is done
		if (SpawnPowerup.newPowerUp) {
			SpawnPowerup.spawnPower();
			SpawnPowerup.newPowerUp = false;
		}

		// Deletes anything that needs deleting
		clearLists();
	}

	/*
	 * initMain()
	 * 
	 * This function starts the timer, creates the level, creates the gorillas, and
	 * starts the game by calling the first players turn.
	 * 
	 * By: Tore
	 */
	public static void initMain() {
		// Start timer
		initTimer();

		// Create level
		cLevel = new Level(n, m);

		// Create all gorillas
		for (int i = 0; i < pAmount; i++) {
			new Gorilla((int) (((double) Main.n / pAmount) * (i + 0.5)));
		}

		// Call first turn
		PlayerTurn.startTurn(0);
	}

	/*
	 * initTimer()
	 * 
	 * Starts timer for animations and handles timer events
	 * 
	 * By: Tore
	 */
	static void initTimer() {
		timer = new AnimationTimer() {

			private long lastToggle;

			// Thread that calls Main.run() ~60 times per second
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

	/*
	 * clearLists()
	 * 
	 * Deletes all objects in the array list delList.
	 * 
	 * By: Tore
	 */
	private static void clearLists() {
		for (GameObject gameObject : delList) {
			objList.remove(gameObject);
		}
	}

	/*
	 * fullClearLists()
	 * 
	 * Essentially restarts everything. Clears cLevel and objList.
	 * 
	 * By: Tore
	 */
	public static void fullClearLists() {
		objList.clear();
		cLevel = null;
	}
}
