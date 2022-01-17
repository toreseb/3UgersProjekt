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
	public static Scene mainScene;
	// public static Group root2 = new Group();
	
	public static int startSizeX= 650;
	public static int startSizeY= 390;

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
	public static Label score = new Label();

	public static Image background = new Image("pixel-city-chill.gif");
	public static ImageView imageView = new ImageView(background);

	/*
	 * start()
	 *
	 * Creates the stage and calls the different, variable-defining scenes.
	 *
	 * By: Helene Moesgaard.
	 */
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		mainStage.setTitle("SimpGorillas");
		mainStage.setResizable(false);
		
		//Center on screen
		mainStage.setX((Screen.getPrimary().getVisualBounds().getWidth()- startSizeX)/2);
		mainStage.setY((Screen.getPrimary().getVisualBounds().getHeight()- startSizeY)/2);
		
		mainRoot.getChildren().addAll(frameworkRoot, gameRoot);
		
		// Window icon
		Image icon = new Image("Banana.png");
		mainStage.getIcons().add(icon);
		
		//SetupScenes.windowSize1(mainStage);
		
		mainScene = new Scene(mainRoot,startSizeX,startSizeY);
		
		mainStage.setScene(mainScene);
		
		SetupScenes.windowSize();
		
		mainStage.show();
	}

	// Main method
	public static void main(String[] args) {
		launch(args);
		System.exit(0);
	}

	static void run() {
		// gameRoot.getChildren().clear();

		// Update score board
		score.setText((pList.get(0).point) + "> Points < " + pList.get(1).point);

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
		new Gorilla(Gorilla.width * 2);
		new Gorilla(n - (Gorilla.width * 2));
		
		/*
		 * new LevelPart(0,100,100); new LevelPart(100,100,100); new
		 * LevelPart(200,100,100); new LevelPart(300,100,100); new
		 * LevelPart(400,100,100); new LevelPart(500,100,100);
		 */

		// Insert score board
		score = new Label((pList.get(0).point) + "> Points < " + pList.get(1).point);
		BorderPane placeScore = new BorderPane();
		placeScore.setPrefWidth(Main.n);
		placeScore.setPrefHeight(Main.m);
		placeScore.setTop(score);
		BorderPane.setAlignment(score, Pos.CENTER);
		frameworkRoot.getChildren().add(placeScore);

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

}
