package framework;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartWindow extends Application {
	public int n = 0;
	public int m = 0;

	public static void main(String args[]) {
		launch(args);
	}

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
		GridPane.setConstraints(setN, 2, 4);


		// Place setM
		GridPane.setConstraints(setM, 2, 5);
		

		askN.setText("Angiv bredde:");

		// Place askN
		GridPane.setConstraints(askN, 1, 4);


		askM.setText("Angiv h�jde:");

		//Place askM
		GridPane.setConstraints(askM, 1, 5);
		
		
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

				} catch (Exception e) {
					text.setText("Skriv venligst kun hele tal");
				}
			}
		});
		
		//Place btn
		GridPane.setConstraints(btn, 3, 1);
		
		grid.getChildren().addAll(text,askN,setN,askM,setM,btn);
		
		primaryStage.show();
	}
}
