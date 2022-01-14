package framework;


import gameObjects.Gorilla;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Group;

/*
 * The function of this class is:
 *	To display the winner and loser of the game when the game ends
 *
 * By: William
 */

public class GameOver {
	
	// The scene for the game over screen
	private static Scene endGame(int cPlayer) {
		return new Scene(createContent(cPlayer));
	}
	
	// Creates all the content for the GameOver screen.
	private static Parent createContent(int cPlayer) {
		Group root = new Group();
		GridPane pane = new GridPane();
		Label winner = new Label();
		Label loser = new Label();
		winner.setText("Victory: " + Main.pList.get(cPlayer));
		for (Gorilla p : Main.pList) {
			if (p.id != Main.pList.get(cPlayer).id) loser.setText("Loser: " + p);
		}
		pane.getChildren().add(winner);
		pane.getChildren().add(loser);
		root.getChildren().add(pane);
		return root;
	}
}
