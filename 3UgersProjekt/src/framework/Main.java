package framework;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import gameObjects.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class Main extends Application {
	
	public static ArrayList<GameObject> objList = new ArrayList<GameObject>();
	
	public static int n,m;
	
    @Override
    public void start(Stage primaryStage) throws Exception{
    	Group root = new Group();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        
        
        primaryStage.show();
    }
    
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