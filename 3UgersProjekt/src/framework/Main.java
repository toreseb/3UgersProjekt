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
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

/**
 * Tore og Helene har skrevet denne klasse
 *
 *
 */

public class Main extends Application {
	public static int n, m;// Width and Height

	public static Stage mainStage;
	public static Scene mainScene;
	// public static Group root2 = new Group();

	public static Level cLevel;

	public static AnimationTimer timer;

	public static ArrayList<GameObject> objList = new ArrayList<GameObject>();
	public static ArrayList<GameObject> delList = new ArrayList<GameObject>();

	public static ArrayList<Gorilla> pList = new ArrayList<Gorilla>();

	public static int cPlayer = 0;

	public static Group gameRoot = new Group(); // @TEST
	public static Group frameworkRoot = new Group(); // @TEST
	public static Group mainRoot; // @TEST
	public static Label score = new Label();

	/*
	 * start()
	 *
	 * Creates the first scene/stage. xxx hvilken en?
	 *
	 * Asks for two integer values as the dimensions of the game.
	 *
	 * By: Helene Moesgaard.
	 */
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		mainStage.setTitle("SimpGorillas");

		Group root = new Group();

		// BorderPane setup
		BorderPane border = new BorderPane();
		border.setPadding(new Insets(10, 10, 10, 10));

		// GridPane setup
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));

		// Window icon
		Image icon = new Image("BananaNew.png");
		mainStage.getIcons().add(icon);

		root.getChildren().add(grid);
		root.getChildren().add(border);

		int startWidth = 300;
		int startHeight = 200;
		mainStage.setScene(new Scene(root, startWidth, startHeight));

		Text text = new Text();
		TextField setN = new TextField();
		setN.setPromptText("300 to 2000");
		TextField setM = new TextField();
		setM.setPromptText("300 to 2000");
		Label askN = new Label();
		Label askM = new Label();
		Button btn = new Button();

		text.setText("Please define the dimensions of the game:");

		// Place text
		border.setTop(text);

		// Place setN
		GridPane.setConstraints(setN, 1, 4);

		// Place setM
		GridPane.setConstraints(setM, 1, 5);

		askN.setText("Width:");
		// Place askN
		GridPane.setConstraints(askN, 0, 4);

		askM.setText("Height:");
		// Place askM
		GridPane.setConstraints(askM, 0, 5);

		btn.setText("Submit");
		btn.setLayoutX(150);
		btn.setLayoutY(80);
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					int newN = Integer.parseInt(setN.getText());
					int newM = Integer.parseInt(setM.getText());
					if ((newN < 300 || newN > 2000) || (newM < 300 || newM > 2000)) {
						throw new IllegalCallerException();
					}
					n = newN;
					m = newM;

					// reposition Stage
					Main.mainStage.setX((Main.mainStage.getX() + (startWidth - n) / 2));
					Main.mainStage.setY((Main.mainStage.getY() + (startHeight - m) / 3));
					if (Main.mainStage.getY() < 5)
						Main.mainStage.setY(5);

					mainRoot = new Group();
					mainRoot.getChildren().addAll(frameworkRoot, gameRoot);
					Scene scene = new Scene(mainRoot, Main.n, Main.m);
					Main.mainStage.setScene(scene);
					initMain();
				} catch (IllegalCallerException e) {
					text.setText("Only values between 300 and 2000");
				} catch (Exception e) {
					text.setText("Please enter integers only!");
				}
			}
		});

		// Place btn
		GridPane.setConstraints(btn, 1, 6);

		grid.getChildren().addAll(askN, setN, askM, setM, btn);

		primaryStage.setResizable(false);
		primaryStage.show();
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
		clearLists();

	}

	public static void initMain() {
		initTimer();

		cLevel = new Level(n, m);
		new Gorilla(Gorilla.width * 2);
		new Gorilla(n - (Gorilla.width * 2));
		SpawnPowerup.spawnPower();
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
