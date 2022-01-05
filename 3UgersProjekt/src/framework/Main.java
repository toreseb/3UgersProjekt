package framework;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import gameObjects.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static ArrayList<GameObject> objList = new ArrayList<GameObject>();
	
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
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
    	System.out.println("Hi2");
    }
}