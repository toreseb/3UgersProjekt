package framework;

import gameObjects.GameObject;
import gameObjects.Gorilla;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;

/*
 * The function of this class is:
 * To display the winner and loser of the game when the game ends
 *
 * By: William
 */

public class GameOver {

	private static final double WIDTH = 500;
	private static final double HEIGHT = 500;

	// This function display the game over scene in the window when the main game is
	// done
	public static void endGame() {

		Main.frameworkRoot.getChildren().clear();
		Main.gameRoot.getChildren().clear();
		for (GameObject gO : Main.objList) {
			gO.deleteObject();
		}

		Main.cPlayer = 0;
		Main.timer.stop();

		Main.mainStage.setScene(new Scene(createContent(), WIDTH, HEIGHT));

		// Centering the window on the screen
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		Main.mainStage.setX(screenBounds.getWidth() / 2 - Main.mainStage.getWidth() / 2);
		Main.mainStage.setY(screenBounds.getHeight() / 2 - Main.mainStage.getHeight() / 2);
	}

	/*
	 * This function creates all the content that's displayed on the scene and
	 * returns Group named root, that holds all the content
	 */
	private static Group createContent() {
		Button exit = new Button();
		Button playAgain = new Button();
		Group root = new Group();
		GridPane pane = new GridPane();
		Image city = new Image("pixel-city-chill.gif");
		Image banana = new Image("Banana.png");
		ImageView background = new ImageView(city);
		ImageView btnImg = new ImageView(banana);
		Label winner = new Label();
		Label gameOver = new Label();

		// Sets the background image
		double w = city.getWidth();
		double h = city.getHeight();
		background.setPreserveRatio(true);
		if ((WIDTH / w) > (HEIGHT / h)) {
			background.setFitWidth(WIDTH);
		} else
			background.setFitHeight(HEIGHT);

		// Sets the layout of the grid pane
		pane.setAlignment(Pos.TOP_CENTER);
		pane.setMinSize(500, 500);
		pane.setPadding(new Insets(15, 10, 10, 10));
		pane.setVgap(15);
		pane.setHgap(25);

		// Positioning the elements inside the gridpane.
		GridPane.setHalignment(gameOver, HPos.CENTER);
		GridPane.setHalignment(winner, HPos.CENTER);
		GridPane.setConstraints(gameOver, 1, 0);
		GridPane.setConstraints(winner, 1, 5);
		GridPane.setConstraints(playAgain, 0, 13);
		GridPane.setConstraints(exit, 2, 13);
		GridPane.setColumnSpan(gameOver, 2);
		GridPane.setColumnSpan(winner, 2);
		GridPane.setColumnSpan(playAgain, 2);

		// Adds the text to the labels
		gameOver.setText("Game Over");
		int winnerNum = 0;
		for (Gorilla g : Main.pList) {
			System.out.println(Main.nList.get(Main.pList.indexOf(g)) + ": " + g.isDead);
			if (!g.isDead) {
				winnerNum = Main.pList.indexOf(g);
			}
		}
		winner.setText("Winner: " + Main.nList.get(winnerNum));
		Main.pList.clear();

		// Sets the fonts and the size of the text
		gameOver.setFont(new Font("Times New Roman", 50.0));
		gameOver.setTextFill(Color.WHITE);
		winner.setFont(new Font("Times New Roman", 35.0));
		winner.setTextFill(Color.WHITE);

		// Adding a play again button
		playAgain.setText("Play Again");
		playAgain.setGraphic(btnImg);
		playAgain.setFont(new Font("Times New Roman", 25.0));
		playAgain.setAlignment(Pos.BASELINE_LEFT);
		playAgain.setTextFill(Color.WHITE);
		playAgain.setBorder(null);
		playAgain.setStyle(
				"-fx-background-color: linear-gradient(to right bottom, #31CCB0, #2C90C4); -fx-background-radius: 25px;");
		playAgain.setPrefSize(200, 55);
		playAgain.setOnMouseEntered(event -> {
			playAgain.setCursor(Cursor.HAND);
		});
		playAgain.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				SetupScenes.pCount = 1;
				SetupScenes.windowSize();
			}
		});

		// adding a Exit button
		exit.setText("Exit");
		exit.setFont(new Font("Times New Roman", 25.0));
		exit.setTextFill(Color.WHITE);
		exit.setAlignment(Pos.CENTER);
		exit.setBorder(null);
		exit.setStyle(
				"-fx-background-color: linear-gradient(to right bottom, #B22858, #E36138); -fx-background-radius: 25px;");
		exit.setPrefSize(200, 60);
		exit.setOnMouseEntered(event -> {
			exit.setCursor(Cursor.HAND);
		});
		exit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evnet) {
				Main.mainStage.close();
			}
		});

		// Adds the Nodes to the pane and the pane to the group
		pane.getChildren().add(winner);
		pane.getChildren().add(gameOver);
		pane.getChildren().add(playAgain);
		pane.getChildren().add(exit);

		root.getChildren().add(background);
		root.getChildren().add(pane);
		return root;
	}
}
