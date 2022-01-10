package framework;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import gameObjects.*;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.Duration;

/**
 * Tore og Helene har skrevet denne klasse
 *
 *
 */

public class Main extends Application {
	public static int n, m;

	public static Stage mainStage;
	public static Scene mainScene;
	// public static Group root2 = new Group();

	public static Timer mainT = new Timer();

	public static ArrayList<GameObject> objList = new ArrayList<GameObject>();
	public static ArrayList<GameObject> delList = new ArrayList<GameObject>();

	public static ArrayList<Gorilla> pList = new ArrayList<Gorilla>();

	public static int cPlayer = 0;

	public static Group gameRoot = new Group(); // @TEST
	public static Group frameworkRoot = new Group(); // @TEST
	public static Group mainRoot = new Group(); // @TEST

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
		Image icon = new Image("Beutiful_Banana.png");
		mainStage.getIcons().add(icon);

		root.getChildren().add(grid);
		root.getChildren().add(border);

		mainStage.setScene(new Scene(root, 300, 200));

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
		mainT.cancel();
	}

	static void run() {
		gameRoot.getChildren().clear();
		for (GameObject gO : objList) {
			gO.run(gameRoot);
		}
		clearLists();

	}

	public static void initMain() {
		initTimer();
		Gorilla p0 = new Gorilla(Gorilla.width * 2, m - Gorilla.height / 2);
		Gorilla p1 = new Gorilla(n - (Gorilla.width * 2), m - Gorilla.height / 2);
		pList.add(p0);
		pList.add(p1);

		// Kald tur
		promptPlayer();
	}

	/*
	 * promptPlayer()
	 * 
	 * Parses an integer as parameter.
	 * 
	 * Creates a prompt for a player to set values for throw.
	 * 
	 * By: Helene Moesgaard.
	 */
	public static void promptPlayer() {
		// Create components
		Label player = new Label("Player " + (cPlayer + 1) + ":");
		Label score = new Label((pList.get(0).point) + "> Points < " + pList.get(1).point);
		Label speedLabel = new Label("Set speed:");
		Label angleLabel = new Label("Set angle:");
		TextField speedText = new TextField();
		speedText.setPromptText("0 to 60");
		TextField angleText = new TextField();
		angleText.setPromptText("0 to 90");
		Button submit = new Button("Submit");

		BorderPane placement = new BorderPane();
		placement.setPrefWidth(n);
		placement.setPrefHeight(m);
		placement.setPadding(new Insets(10, 10, 10, 10));

		// Set size and event of button
		// submit.setLayoutX(150);
		// submit.setLayoutY(80);
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					double angle = Double.parseDouble(angleText.getText());
					double speed = Double.parseDouble(speedText.getText());
					if((angle > 90 || angle < 0)||(speed > 60 || speed < 0)){
						throw new IllegalArgumentException();
					}
					
					pList.get(cPlayer).throwBanana(angle, speed);
					Main.frameworkRoot.getChildren().remove(placement);
				} catch (Exception e) {
					Text advarsel = new Text("Angiv venligst kun tal i de givne områder!");
					placement.setTop(advarsel);
				}

			}
		});

		// Place components
		GridPane group = new GridPane();
		GridPane.setConstraints(player, 0, 0);
		GridPane.setConstraints(speedLabel, 0, 1);
		GridPane.setConstraints(angleLabel, 0, 2);
		GridPane.setConstraints(speedText, 1, 1);
		GridPane.setConstraints(angleText, 1, 2);
		GridPane.setConstraints(submit, 0, 3);
		group.getChildren().addAll(player, speedLabel, angleLabel, speedText, angleText, submit);

		switch (cPlayer) {
		case 0: {
			placement.setLeft(group);
			break;
		}
		case 1: {
			placement.setRight(group);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + cPlayer);
		}

		placement.setTop(score);
		placement.setAlignment(score, Pos.CENTER);

		frameworkRoot.getChildren().add(placement);
	}

	static void initTimer() {
		AnimationTimer timer = new AnimationTimer() {

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
