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
	public static void endGame() {
		Main.mainStage.setScene(new Scene(createContent()));
		Main.mainStage.setWidth(500);
		Main.mainStage.setHeight(500);
	}
	
	// Creates all the content for the GameOver screen.
	private static Parent createContent() {
		Group root = new Group();
		GridPane pane = new GridPane();
		Label winner = new Label();
		Label loser = new Label();
		winner.setText("Victory: " + Main.pList.get(0));
		for (Gorilla p : Main.pList) {
			if (p.id != Main.pList.get(1).id) loser.setText("Loser: " + p);
		}
		pane.getChildren().add(winner);
		pane.getChildren().add(loser);
		root.getChildren().add(pane);
		return root;
	}
}
