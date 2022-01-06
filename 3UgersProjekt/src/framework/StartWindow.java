package framework;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartWindow extends Application{
	public int n = 0;
	public int m = 0;
	
	

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception{
    	Group root = new Group();
        primaryStage.setTitle("SimpGorillas");
        primaryStage.setScene(new Scene(root, 300, 200));
        
        //Brug HBox, Vbox, eller GridPane til at placere tingene.
        
        Text text = new Text();
        TextField setN = new TextField();
        TextField setM = new TextField();
        Label askN = new Label();
        Label askM = new Label();
        Button btn = new Button();
        
        text.setText("Angiv venligst dimensioner af spilleplade:");
        //text.setX(100);
        //text.setY(20);
        
        //setWidth.setLayoutX(100);
        //setWidth.setLayoutY(40);
        
        //setHeight.setLayoutX(100);
        //setHeight.setLayoutY(60);
        
        askN.setText("Angiv bredde:");
        //askWidth.setLayoutX(100);
        //askWidth.setLayoutY(40);
        
        
        askM.setText("Angiv h�jde:");
        //askHeight.setLayoutX(100);
        //askHeight.setLayoutY(60);

        
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
					
					
				}catch(Exception e) {
					text.setText("Skriv venligst kun hele tal");
				}
			}
        });
        
        root.getChildren().add(text);
        root.getChildren().add(setN);
        root.getChildren().add(setM);
        root.getChildren().add(askN);
        root.getChildren().add(askM);
        root.getChildren().add(btn);

        
        primaryStage.show();
    }
	

}
