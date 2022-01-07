package framework;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import framework.Main;

public class PlayerPrompter {

	public PlayerPrompter() {
		// TODO Auto-generated constructor stub
	}
	
	public static void promptPlayer() {
		//Create components
		Label speedLabel = new Label("Set speed:");
		Label angleLabel = new Label("Set angle:");
		TextField speedText = new TextField();
		TextField angleText = new TextField();
		Button submit = new Button("Submit");
		
		BorderPane placement = new BorderPane();
		placement.setPadding(new Insets(10,10,10,10));
		
		//Set size and event of button
		//submit.setLayoutX(150);
		//submit.setLayoutY(80);
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
					promptPlayer();
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
		
		
		placement.setRight(group);
				
		Main.root2.getChildren().add(placement);

}
