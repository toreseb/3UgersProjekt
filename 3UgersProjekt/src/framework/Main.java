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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class Main extends Application {
	public static int n, m;
	
	public static Stage mainStage;
	public static Scene mainScene;
	
	public static Timer mainT = new Timer();
	
	public static ArrayList<GameObject> objList = new ArrayList<GameObject>();
	
    @Override
    public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("SimpGorillas");
		
		// GridPane setup.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));

		primaryStage.setScene(new Scene(grid, 300, 200));
		
		
		Text text = new Text();
		TextField setN = new TextField();
		TextField setM = new TextField();
		Label askN = new Label();
		Label askM = new Label();
		Button btn = new Button();
				

		text.setText("Angiv venligst dimensioner af spilleplade:");

		// Place text
		GridPane.setConstraints(text, 1, 3);


		// Place setN


		// Place setM


		askN.setText("Angiv bredde:");

		// Place askN


		askM.setText("Angiv h�jde:");

		// Place askM

		
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
					System.out.println("Dette virker");
				} catch (Exception e) {
					text.setText("Skriv venligst kun hele tal");
				}
			}
		});
		
		GridPane.setConstraints(btn, 2, 6);
		GridPane.setConstraints(askN, 1, 4);
		GridPane.setConstraints(setN, 2, 4);
		GridPane.setConstraints(askM, 1, 5);
		GridPane.setConstraints(setM, 2, 5);

		grid.getChildren().addAll(text,askN,setN,askM,setM,btn);

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
			gO.run();
		}
    	
    }
    
    public static void initMain() {
		Main.mainT.scheduleAtFixedRate(new TimerTask(){
		    @Override
		    public void run(){
		       Main.run();
		    }
		},0,16);
    	Gorilla g = new Gorilla(40, 500);
    }
    
}