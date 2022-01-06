package framework;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import gameObjects.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class Main extends Application {
	public static int n, m;
	
	public static ArrayList<GameObject> objList = new ArrayList<GameObject>();
	
    @Override
    public void start(Stage stage) throws Exception{
    	Parent root = FXMLLoader.load(getClass().getResource("startScene.fxml"));
    	Scene scene = new Scene(root);
        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();
    }
    
    // Main method 
    public static void main(String[] args) {
    	
    	System.out.println("Hi");
    	Timer t = new Timer();
    	t.scheduleAtFixedRate(new TimerTask(){
		    @Override
		    public void run(){
		       Main.run();
		    }
		},0,16);
        launch(args);
        t.cancel();
        System.out.println("Hi3");
    }
    
    static void run() {
    	for (GameObject gO : objList) {
			gO.run();
		}
    	
    }
}