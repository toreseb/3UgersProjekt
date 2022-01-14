package framework;




import gameObjects.Gorilla;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.text.Font;

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
		Label gameOver = new Label();
		pane.setHgap(2);
		pane.setVgap(2);
		
		// positions the Nodes on the pane
		GridPane.setConstraints(gameOver, 5, 2);
		GridPane.setConstraints(winner, 5, 6);
		GridPane.setConstraints(loser, 5, 8);

		// Sets the fonts and the size of the text
		gameOver.setFont(new Font("Times New Roman", 40.0));
		winner.setFont(new Font("Times New Roman", 25.0));
		loser.setFont(new Font("Times New Roman", 25.0));
		
		// Adds the text 
		gameOver.setText("Game Over");
		winner.setText("Winner: " + Main.pList.get(0));
		for (Gorilla p : Main.pList) {
			if (p.id != Main.pList.get(1).id) loser.setText("Loser: " + p);
		}
		
		// Adds the Nodes to the pane and the pane to the group
		pane.getChildren().add(winner);
		pane.getChildren().add(loser);
		pane.getChildren().add(gameOver);
		root.getChildren().add(pane);
		return root;
	}
}
