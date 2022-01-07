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

	static Group root2 = new Group(); // @TEST

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
		TextField setM = new TextField();
		Label askN = new Label();
		Label askM = new Label();
		Button btn = new Button();

		text.setText("Please define dimentions of game:");

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
					n = Integer.parseInt(setN.getText());
					m = Integer.parseInt(setM.getText());
					root2 = new Group();
					Scene scene = new Scene(root2, Main.n, Main.m);
					Main.mainStage.setScene(scene);
					initMain();
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
		System.out.println("Hi3");
	}

	static void run() {
		//root2.getChildren().clear();
		for (GameObject gO : objList) {
			gO.run(root2);
		}
		clearLists();
	}

	public static void initMain() {
		initTimer();		
		Gorilla p0 = new Gorilla(Gorilla.width*2,m-Gorilla.height/2);
		Gorilla p1 = new Gorilla(n-(Gorilla.width*2),m-Gorilla.height/2);
		pList.add(p0);
		pList.add(p1);
		
		System.out.println("Hey!");
		//Kald tur
		promptPlayer(1);
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
	public static void promptPlayer(int player) {
		//Create components
		Label speedLabel = new Label("Set speed:");
		Label angleLabel = new Label("Set angle:");
		TextField speedText = new TextField();
		TextField angleText = new TextField();
		Button submit = new Button("Submit");
		
		BorderPane placement = new BorderPane();
		placement.setPadding(new Insets(10,10,10,10));
		
		//Set size and event of button
		submit.setLayoutX(150);
		submit.setLayoutY(80);
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					
					pList.get(cPlayer).throwBanana(Integer.parseInt(angleText.getText()),Integer.parseInt(speedText.getText()));
					cPlayer++;
					if(cPlayer > pList.size()) {
						cPlayer = 0;
					}
					promptPlayer(cPlayer);
				} catch (Exception e) {
					Text advarsel = new Text("Angiv venligst kun hele tal!");
					placement.setTop(advarsel);
				}
			}
		});
		
		//Place components
		GridPane group = new GridPane();
				
		GridPane.setConstraints(speedLabel, 0, 0);
		GridPane.setConstraints(angleLabel, 0, 1);
		GridPane.setConstraints(speedText, 1, 0);
		GridPane.setConstraints(angleText, 1, 1);
		GridPane.setConstraints(submit, 0, 2);
		
		group.getChildren().addAll(speedLabel,angleLabel,speedText,angleText,submit);
		
		
		if (cPlayer==2) {
			placement.setLeft(group);
		}else {
			placement.setRight(group);
		}
				
		root2.getChildren().add(placement);
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
