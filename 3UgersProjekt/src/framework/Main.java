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
	
	public static Stage mainStage;
	public static Scene mainScene;
	
	public static Timer mainT = new Timer();
	
	public static ArrayList<GameObject> objList = new ArrayList<GameObject>();
	
    @Override
    public void start(Stage stage) throws Exception{
    	Parent root = FXMLLoader.load(getClass().getResource("startScene.fxml"));

    	mainStage = stage;
    	mainScene = new Scene(root);
        mainStage.setTitle("Hello World");
        mainStage.setScene(mainScene);
        mainStage.setResizable(false);
        mainStage.show();
    }
    
    // Main method 
    public static void main(String[] args) {
    	
    	System.out.println("Hi");
    	
    	
    	startMain();
        launch(args);
        mainT.cancel();
        System.out.println("Hi3");
    }
    
    static void run() {
    	for (GameObject gO : objList) {
			gO.run();
		}
    	
    }
    
    static void startMain() {
    	
    }
    
}