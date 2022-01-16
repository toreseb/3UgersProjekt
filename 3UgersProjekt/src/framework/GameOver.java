package framework;




import gameObjects.Gorilla;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/*
 * The function of this class is:
 *	To display the winner and loser of the game when the game ends
 *
 * By: William
 */

public class GameOver {
	
	/*
	 * @TODO
	 * 	* Add a play again button
	 */
	
	 // This function display the game over scene in the window when the main game is done 
	public static void endGame() {
		Main.mainStage.setScene(new Scene(createContent()));
		Main.mainStage.setWidth(500);
		Main.mainStage.setHeight(500);
		
	}
	
	/* 
	 * This function creates all the content that's displayed on the scene
	 * and returns Group named root, that holds all the content
	 */
	private static Group createContent() {
		Group root = new Group();
		GridPane pane = new GridPane();
		Label winner = new Label();
		Label loser = new Label();
		Label gameOver = new Label();
		
		// Sets the layout of the grid pane
		pane.setGridLinesVisible(true);
		pane.setAlignment(Pos.TOP_CENTER);
		pane.setMinSize(500, 500);
		pane.setPadding(new Insets(15,10,10,10));
		pane.setVgap(15);
		
		// Positioning the elements inside the gridpane.
		GridPane.setHalignment(gameOver, HPos.CENTER);
		GridPane.setHalignment(winner, HPos.CENTER);
		GridPane.setHalignment(loser, HPos.CENTER);
		GridPane.setConstraints(gameOver, 0, 0);
		GridPane.setConstraints(winner, 0, 5);
		GridPane.setConstraints(loser, 0, 11);
		
		/*
		 * Adds the text to the labels
		 * @TODO: 
		 * Do so that the right player is displayed in the winner filed and the other(s)
		 * in the losers field.
		 */
		gameOver.setText("Game Over");
		winner.setText("Winner: Player 1");
		loser.setText("Loser: Plyaer 2");
		
		// Sets the fonts and the size of the text
		gameOver.setFont(new Font("Times New Roman", 50.0));
		winner.setFont(new Font("Times New Roman", 35.0));
		loser.setFont(new Font("Times New Roman", 35.0));
		
		// Adds the Nodes to the pane and the pane to the group
		pane.getChildren().add(winner);
		pane.getChildren().add(loser); 
		pane.getChildren().add(gameOver);
		root.getChildren().add(pane);
		return root;
	}
}
