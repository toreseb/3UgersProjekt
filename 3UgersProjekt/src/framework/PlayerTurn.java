package framework;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
import javafx.scene.shape.Rectangle;
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

		// Create space
		HBox.setMargin(btnShoot, new Insets(0, 10, 0, 0));
		VBox.setMargin(ask, new Insets(10, 10, 10, 10));
		VBox.setMargin(buttons, new Insets(0, 0, 10, 10));

		// Add to scene
		Main.frameworkRoot.getChildren().add(groupAll);

		// Set event on Shoot button
		btnShoot.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// Remove prompt
				Main.frameworkRoot.getChildren().remove(groupAll);

				// Call shoot prompt
				//promptShoot(cPlayer);
				Main.pList.get(cPlayer).banana = null;
				Main.pList.get(cPlayer).throwBanana(cPlayer);
			}
		});

		// Set event on Move button
		btnMove.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// Remove prompt
				Main.frameworkRoot.getChildren().remove(groupAll);

				// Call move prompt
				Main.pList.get(cPlayer).moveable = true;
				promptMove(cPlayer);
			}
		});
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
	// Jeg t�nker at for at f� dette til at virke, kan der v�re en boolean for
	// om en
	// gorilla kan flyttes eller ej i Gorilla. Denne function g�r den true og
	// beder
	// spilleren om at flytte sin gorilla. Der skal s� v�re en function i
	// Gorilla
	// for at flytte en gorilla, men den lader kun en g�re det hvis ens boolean er
	// true. I slutningen skal den g�re booleanen false.
	public static void promptMove(int cPlayer) {
		// Create componenten
		Label prompt = new Label("Please drag and drop gorilla :)");
		Rectangle rect = new Rectangle(100, 100, Color.RED);

		// Placement
		prompt.setLayoutX(Main.n / 2 - 80);
		prompt.setLayoutY(Main.m / 2);

		// Make root to add to scene and add prompt
		Group root = new Group();
		root.getChildren().add(prompt);
		Main.frameworkRoot.getChildren().add(root);

		Main.pList.get(cPlayer).moveGorilla(Main.pList.get(cPlayer).groupShape); // Moves the gorilla to new location
		if (!Main.pList.get(cPlayer).moveable) {
			Main.frameworkRoot.getChildren().remove(root); // Removes the prompt
		}

	}


}
