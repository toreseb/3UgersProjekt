package framework;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/*
 * This class handles the players turns - contains the functions:
 * - startTurn
 * - promptMove
 * 
 * Handles a players turn and any effects of debuffs.
 * 
 * By: Helene
 */
public class PlayerTurn {
	public static Group root = new Group();

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
		// Checks if a power up should be spawned this round - 20% chance
		int rnd = (int)(Math.random()*5);
		if (rnd == 1) {
			SpawnPowerup.newPowerUp = true;
		}
		
		// Create components
		Label ask = new Label(Main.nList.get(cPlayer) + " what do you want to do on your turn?");
		Button btnShoot = new Button("Shoot");
		Button btnMove = new Button("Move");
		// Add to H- and VBox
		HBox buttons = new HBox(btnShoot, btnMove);
		VBox groupAll = new VBox(ask, buttons);

		// Debuff warning
		Label debuffWarning = new Label();
		debuffWarning.setLayoutX(Main.n / 2 - 70);
		debuffWarning.setLayoutY(20);

		// Create space
		HBox.setMargin(btnShoot, new Insets(0, 10, 0, 0));
		VBox.setMargin(ask, new Insets(10, 10, 10, 10));
		VBox.setMargin(buttons, new Insets(0, 0, 10, 10));

		// Add to scene
		Main.frameworkRoot.getChildren().add(groupAll);
		Main.frameworkRoot.getChildren().add(debuffWarning);

		// Set event on Shoot button
		btnShoot.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (Main.pList.get(cPlayer).frozen == 0) {
					// Remove prompt
					Main.frameworkRoot.getChildren().remove(groupAll);
					Main.frameworkRoot.getChildren().remove(debuffWarning);

					// Call throw banana
					Main.pList.get(cPlayer).banana = null;
					Main.pList.get(cPlayer).throwBanana(cPlayer);

					// Count down slime debuff if active
					if (Main.pList.get(cPlayer).slimed != 0) {
						Main.pList.get(cPlayer).slimed--;
						if ((Main.pList.get(cPlayer).slimed == 0) && (Main.pList.get(cPlayer).normalImage == false)) {
							Main.pList.get(cPlayer).gorillaImg = new Image(ClassLoader.getSystemResource("Gorilla.png").toString());
							Main.pList.get(cPlayer).gorillaThrowImg = new Image(ClassLoader.getSystemResource("GorillaThrow.png").toString());
							Main.pList.get(cPlayer).normalImage = true;
						}
					}
				} else {
					debuffWarning.setText("You can't move your arm!");
				}
			}
		});

		// Set event on Move button
		btnMove.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (Main.pList.get(cPlayer).slimed == 0) {
					// Remove prompt
					Main.frameworkRoot.getChildren().remove(groupAll);
					Main.frameworkRoot.getChildren().remove(debuffWarning);
					
					// Call move prompt
					Main.pList.get(cPlayer).moveable = true;
					promptMove(cPlayer);

					// Count down frozen debuff if active
					if (Main.pList.get(Main.cPlayer).frozen != 0) {
						Main.pList.get(Main.cPlayer).frozen--;
						if ((Main.pList.get(Main.cPlayer).frozen == 0) && (Main.pList.get(Main.cPlayer).normalImage == false)) {
							Main.pList.get(Main.cPlayer).gorillaImg = new Image(ClassLoader.getSystemResource("Gorilla.png").toString());
							Main.pList.get(Main.cPlayer).normalImage = true;
						}
					}

				} else {
					debuffWarning.setText("You're stuck to the ground!");
				}
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
	public static void promptMove(int cPlayer) {
		// Create components
		Label prompt = new Label("Please drag and drop gorilla :)");

		// Placement
		prompt.setLayoutX(Main.n / 2 - 80);
		prompt.setLayoutY(20);

		// Add prompt to root and add to scene
		root.getChildren().add(prompt);
		Main.frameworkRoot.getChildren().add(root);

		Main.pList.get(cPlayer).moveGorilla(Main.pList.get(cPlayer).groupShape); // Moves the gorilla to new location
	}
	
	
}
