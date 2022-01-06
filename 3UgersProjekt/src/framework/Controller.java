package framework;

import java.util.Timer;
import java.util.TimerTask;

import framework.Main;

import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.shape.*;

public class Controller {
	
	@FXML
	TextField heightTextField, widthTextField;
	@FXML
	Button submitButton;
	
	int height, width;
	
	public void changeScene() {
		Group root = new Group();
		Scene scene = new Scene(root,Integer.parseInt(widthTextField.getText()),Integer.parseInt(heightTextField.getText()));
		Main.mainStage.setScene(scene);
		Main.mainT.scheduleAtFixedRate(new TimerTask(){
		    @Override
		    public void run(){
		       Main.run();
		    }
		},0,16);
		
	}
}
