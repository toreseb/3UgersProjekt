package framework;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlayerTurn {

	/*
	 * startTurn()
	 * 
	 * Parses an integer as parameter.
	 * 
	 * Asks the player if they want to shoot or move.
	 * 
	 * By: Helene Moesgaard.
	 */
	public static void startTurn(int cPlayer) {
		// Create components
		Label ask = new Label("Player " + (cPlayer + 1) + " what do you want to do on your turn?");
		Button btnShoot = new Button("Shoot");
		Button btnMove = new Button("Move");
		// Add to H- and VBox
		HBox buttons = new HBox(btnShoot, btnMove);
		VBox groupAll = new VBox(ask, buttons);

		// Add to scene
		Main.frameworkRoot.getChildren().add(groupAll);

		// Set event on Shoot button
		btnShoot.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// Remove prompt
				Main.frameworkRoot.getChildren().remove(groupAll);

				// Call shoot prompt
				promptShoot(cPlayer);
			}
		});

		// Set event on Move button
		btnMove.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// Remove prompt
				Main.frameworkRoot.getChildren().remove(groupAll);

				// Call move prompt
				promptMove(cPlayer);
			}
		});
	}

	/*
	 * promptShoot()
	 * 
	 * Parses an integer as parameter.
	 * 
	 * Creates a prompt for a player to set values for shot.
	 * 
	 * By: Helene Moesgaard.
	 */
	public static void promptShoot(int cPlayer) {
		// Create components
		Label player = new Label("Player " + (cPlayer + 1) + ":");
		Label score = new Label((Main.pList.get(0).point) + "> Points < " + Main.pList.get(1).point);
		Label speedLabel = new Label("Set speed:");
		Label angleLabel = new Label("Set angle:");
		TextField speedText = new TextField();
		TextField angleText = new TextField();
		Button submit = new Button("Submit");

		BorderPane placement = new BorderPane();
		placement.setPrefWidth(Main.n);
		placement.setPrefHeight(Main.m);
		placement.setPadding(new Insets(10, 10, 10, 10));

		// Set button event
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					Main.pList.get(cPlayer).throwBanana(Integer.parseInt(angleText.getText()),
							Integer.parseInt(speedText.getText()));
					Main.frameworkRoot.getChildren().remove(placement);
				} catch (Exception e) {
					Text advarsel = new Text("Angiv venligst kun hele tal!");
					placement.setTop(advarsel);
				}

			}
		});

		// Place components
		GridPane group = new GridPane();
		GridPane.setConstraints(player, 0, 0);
		GridPane.setConstraints(speedLabel, 0, 1);
		GridPane.setConstraints(angleLabel, 0, 2);
		GridPane.setConstraints(speedText, 1, 1);
		GridPane.setConstraints(angleText, 1, 2);
		GridPane.setConstraints(submit, 0, 3);
		group.getChildren().addAll(player, speedLabel, angleLabel, speedText, angleText, submit);

		switch (Main.cPlayer) {
		case 0: {
			placement.setLeft(group);
			break;
		}
		case 1: {
			placement.setRight(group);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + Main.cPlayer);
		}

		placement.setTop(score);
		BorderPane.setAlignment(score, Pos.CENTER);

		Main.frameworkRoot.getChildren().add(placement);
	}

	/*
	 * promptMove()
	 * 
	 * Parses an integer as parameter.
	 * 
	 * Allows player to move gorilla.
	 * 
	 * By: Helene Moesgaard
	 */
	// Jeg tænker at for at få dette til at virke, kan der være en boolean for om en
	// gorilla kan flyttes eller ej i Gorilla. Denne function gør den true og beder
	// spilleren om at flytte sin gorilla. Der skal så være en function i Gorilla
	// for at flytte en gorilla, men den lader kun en gøre det hvis ens boolean er
	// true. I slutningen skal den gøre booleanen false.
	public static void promptMove(int cPlayer) {
		// Create components
		Label prompt = new Label("Please drag and drop gorilla :)");

		// Placement
		prompt.setLayoutX(Main.n / 2 - 80);
		prompt.setLayoutY(Main.m / 2);

		// Make root to add to scene and add prompt
		Group root = new Group();
		root.getChildren().add(prompt);

		Main.frameworkRoot.getChildren().add(root);

		// TODO Lav metode til drag and drop af spiller
		// Skal måske ligge i Gorillas
		// William er i gang
	}

}
