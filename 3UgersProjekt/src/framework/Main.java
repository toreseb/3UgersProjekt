package framework;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import gameObjects.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class Main extends Application {
	public static int n, m;

	public static Stage mainStage;
	public static Scene mainScene;
	//public static Group root2 = new Group();

	public static Timer mainT = new Timer();

	public static ArrayList<GameObject> objList = new ArrayList<GameObject>();

	static Group root2 = new Group();    // @TEST
	
	
	
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
//		Image icon = new Image("Banan.png");
//		mainStage.getIcons().add(icon);

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
					Gorilla g = new Gorilla(40, 500);
					n = Integer.parseInt(setN.getText());
					m = Integer.parseInt(setM.getText());
					
					Group finalRoot = new Group();
					
					finalRoot.getChildren().add(g.drawShape(root2)); 
					
					/**
					 * @TODO: Write a function that returns a scene
					 *  * And then use the scene her insted.
					 */
					Scene scene = new Scene(finalRoot);
					mainStage.setScene(scene);
					initMain();
				} catch (Exception e) {
					text.setText("Please enter integers only!");
				}
			}
		});

		// Place btn
		GridPane.setConstraints(btn, 1, 6);

		grid.getChildren().addAll(askN, setN, askM, setM, btn);

		primaryStage.show();
	}

    // Main method
    public static void main(String[] args) {
        launch(args);
        mainT.cancel();
        System.out.println("Hi3");
    }

    static void run() {
    	for (GameObject gO : objList) {
			gO.run(root2);
		}

	}

	public static void initMain() {
		Main.mainT.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				Main.run();
			}
		}, 0, 16);
	}

}
