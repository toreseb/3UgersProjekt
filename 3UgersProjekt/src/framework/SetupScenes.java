package framework;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

/*
 * SetupScenes
 * 
 * This class makes the scenes for the start window that allow the user to define the different variables of the game.
 * 
 * By: Helene Moesgaard
 */
public class SetupScenes {
	public static int pCount = 1;

	// Denne er ligegyldig, men er her s� vi kan g� tilbage hvis det er.
	public static void windowSize1(Stage mainStage) {
		Group root = new Group();

		// BorderPane setup
		BorderPane border = new BorderPane();
		border.setPadding(new Insets(10, 10, 10, 10));

		// GridPane setup
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));

		root.getChildren().add(grid);
		root.getChildren().add(border);

//		int startWidth = 300;
//		int startHeight = 200;
		mainStage.setScene(new Scene(root, Main.startSizeX, Main.startSizeY));

		Text text = new Text();
		TextField setN = new TextField();
		setN.setPromptText("300 to 2000");
		TextField setM = new TextField();
		setM.setPromptText("300 to 2000");
		Label askN = new Label();
		Label askM = new Label();
		Button btn = new Button();

		text.setText("Please define the dimensions of the game:");

		// Place text
		border.setTop(text);

		// Place setN
		GridPane.setConstraints(setN, 1, 4);

		// Place setM
		GridPane.setConstraints(setM, 1, 5);

		askN.setText("Width:");
		// Place askN
		GridPane.setConstraints(askN, 0, 4);

		askM.setText("Height:");
		// Place askM
		GridPane.setConstraints(askM, 0, 5);

		btn.setText("Submit");
		btn.setLayoutX(150);
		btn.setLayoutY(80);
		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					int newN = Integer.parseInt(setN.getText());
					int newM = Integer.parseInt(setM.getText());
					if ((newN < 300 || newN > 2000) || (newM < 300 || newM > 2000)) {
						throw new IllegalCallerException();
					}
					Main.n = newN;
					Main.m = newM;

					// reposition Stage
					Main.mainStage.setX((Main.mainStage.getX() + (Main.startSizeX - Main.n) / 2));
					Main.mainStage.setY((Main.mainStage.getY() + (Main.startSizeY - Main.m) / 2));

//					if (Main.mainStage.getY() < 5)
//						Main.mainStage.setY(5);

					// set background image
					double w = Main.background.getWidth();
					double h = Main.background.getHeight();
					Main.imageView.setPreserveRatio(true);
					if ((Main.n / w) > (Main.m / h)) {
						Main.imageView.setFitWidth(Main.n);
					} else
						Main.imageView.setFitHeight(Main.m);

					Main.mainRoot = new Group();
					Main.mainRoot.getChildren().add(Main.imageView);
					Main.mainRoot.getChildren().addAll(Main.frameworkRoot, Main.gameRoot);

					Scene scene = new Scene(Main.mainRoot, Main.n, Main.m);
					Main.mainStage.setScene(scene);
					Main.initMain();
				} catch (IllegalCallerException e) {
					text.setText("Only values between 300 and 2000");
				} catch (Exception e) {
					text.setText("Please enter integers only!");
				}
			}
		});

		// Place btn
		GridPane.setConstraints(btn, 1, 6);

		grid.getChildren().addAll(askN, setN, askM, setM, btn);

	}

	/*
	 * windowSize()
	 * 
	 * Changes the frameworkRoot from Main to show two sliders and prompts the user
	 * to set the size of the game-window.
	 */
	public static void windowSize() {	
		// GridPane setup
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		//Create scene and add grid
		Scene sceneSize = new Scene(grid,Main.startSizeX,Main.startSizeY);

		// Create components
		Text prompt = new Text("Please set width and height:");
		Slider sliderX = new Slider(300, Screen.getPrimary().getVisualBounds().getWidth(), 800);
		Slider sliderY = new Slider(300, Screen.getPrimary().getVisualBounds().getHeight(), 400);
		Button btn = new Button("Submit");

		// Define sliders - Ew - I don't like these - Maybe go back to text fields
		//sliderX
		sliderX.setShowTickMarks(true);
		sliderX.setShowTickLabels(true);
		sliderX.setMajorTickUnit(Screen.getPrimary().getVisualBounds().getWidth() / 50);
		sliderX.setBlockIncrement(1);
		sliderX.setPrefWidth(Main.startSizeX - 20);
		//sliderY
		sliderY.setShowTickMarks(true);
		sliderY.setShowTickLabels(true);
		sliderY.setMajorTickUnit(Screen.getPrimary().getVisualBounds().getHeight() / 50);
		sliderY.setBlockIncrement(1);
		sliderY.setPrefWidth(Main.startSizeY - 20);

		// Set placement of components
		GridPane.setConstraints(prompt, 0, 0);
		GridPane.setConstraints(sliderX, 0, 1);
		GridPane.setConstraints(sliderY, 0, 2);
		GridPane.setConstraints(btn, 0, 3);

		//Add components to grid
		grid.getChildren().addAll(prompt, sliderX, sliderY, btn);

		//Set button press event
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//Get values from sliders
				Main.n = (int) sliderX.getValue();
				Main.m = (int) sliderY.getValue();

				//Call next input prompt
				playerAmount();
			}
		});

		//Add grid to frameworkRoot in Main
		//Main.frameworkRoot.getChildren().add(grid);
		
		//Set scene on stage
		Main.mainStage.setScene(sceneSize);
	}

	/*
	 * playerAmount()
	 * 
	 * Changes the frameworkRoot from Main to show a text field and prompts the user
	 * to set the number of players.
	 */
	public static void playerAmount() {
		// GridPane setup
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		//Create scene and add grid
		Scene sceneAmount = new Scene(grid,Main.startSizeX,Main.startSizeY);

		// Create components
		Text prompt = new Text("Please enter amount of players:");
		TextField amount = new TextField();
		amount.setPromptText("2 to 6");
		amount.setMaxWidth(100);
		Button btn = new Button("Submit");

		// Set placement of components
		GridPane.setConstraints(prompt, 0, 0);
		GridPane.setConstraints(amount, 0, 1);
		GridPane.setConstraints(btn, 0, 2);

		//Add components to grid
		grid.getChildren().addAll(prompt, amount, btn);

		//Set button press event
		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					int count = Integer.parseInt(amount.getText());
					
					//Check if input from text field is within bounds
					if ((count < 2 || count > 6)) {
						throw new IllegalCallerException();
					}
					// set pAmount in Main
					Main.pAmount = count;
					
					//Call next imput prompt
					playerNames();
				} catch (IllegalCallerException e) {
					prompt.setText("Only values between 2 and 6");
				} catch (Exception e) {
					prompt.setText("Please enter integers only!");
				}
			}
		});

		//Clear frameworkRoot in Main and add grid
		//Main.frameworkRoot.getChildren().clear();
		//Main.frameworkRoot.getChildren().add(grid);

		//Set scene on stage
		Main.mainStage.setScene(sceneAmount);
	}

	/*
	 * playerNames()
	 * 
	 * Changes the frameworkRoot from Main to show a text field and cycles through
	 * all the players and prompts them to put in their names.
	 */
	public static void playerNames() {
		// GridPane setup
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		//Create scene and add grid
		Scene sceneNames = new Scene(grid,Main.startSizeX,Main.startSizeY);

		// Create components
		Text prompt = new Text("Player" + (pCount) + ", please enter a name");
		TextField name = new TextField();
		name.setPromptText("Enter name");
		name.setMaxWidth(100);
		Button btn = new Button("Submit");

		// Set placement of components
		GridPane.setConstraints(prompt, 0, 0);
		GridPane.setConstraints(name, 0, 1);
		GridPane.setConstraints(btn, 0, 2);

		//Add components to grid
		grid.getChildren().addAll(prompt, name, btn);

		//Create button press event
		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					//Check if text field is left blank and throw exception if yes.
					if (name.getText().equals("")) {
						throw new IllegalCallerException();
					} else {
						//Add name to nList in Main and add 1 to pCount
						Main.nList.add(name.getText());
						pCount++;

						//If all names have been set, clear frameworkRoot and call startGame()
						if (pCount > Main.pAmount) {
							Main.frameworkRoot.getChildren().clear();
							startGame();
						} else {
							//If not, clear text field and prompt next player
							prompt.setText("Player" + (pCount) + ", please enter a name");
							name.clear();
						}
					}
				} catch (IllegalCallerException e) {
					prompt.setText("Please enter a name, player" + pCount);
				}

				// @TODO Lav endnu en exception s� man ikke kan lave for lange navne
			}
		});

		//Main.frameworkRoot.getChildren().clear();
		//Main.frameworkRoot.getChildren().add(grid);

		//Set scene on stage
		Main.mainStage.setScene(sceneNames);
	}
	
	public static void levelSelect() {
		//Setup panes
		HBox buttons = new HBox();
		VBox main = new VBox();
		
		//Create scene and add "main"
		Scene sceneLevel = new Scene(main,Main.startSizeX,Main.startSizeY);		
		
		//Create components
		Text prompt = new Text("Please select a level");
		Button cityLevel = new Button("City");
		Button forestLevel = new Button("Forest");
		
		//Add components to panes
		buttons.getChildren().addAll(cityLevel,forestLevel);
		main.getChildren().addAll(prompt,buttons);
		
		//Set button press event for cityLevel
		cityLevel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
			}
		});
		
		
		//Set button press event for forestLevel
		forestLevel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
			}
		});
		
		
		
		//Set scene on stage
		Main.mainStage.setScene(sceneLevel);
	}

	/*
	 * startGame()
	 * 
	 * Sets a new scene for the game on the stage with the user-given dimensions,
	 * repositions the stage and calls Main.initMain().
	 * 
	 * Skal m�ske omskrives, men nu har vi noget der virker.
	 */
	public static void startGame() {
		// reposition Stage - Skal m�ske laves om
		Main.mainStage.setX((Screen.getPrimary().getVisualBounds().getWidth()-Main.n) / 2);
		Main.mainStage.setY((Screen.getPrimary().getVisualBounds().getHeight()- Main.m) / 2);


		// set background image
		double w = Main.background.getWidth();
		double h = Main.background.getHeight();
		Main.imageView.setPreserveRatio(true);
		if ((Main.n / w) > (Main.m / h)) {
			Main.imageView.setFitWidth(Main.n);
		} else
			Main.imageView.setFitHeight(Main.m);

		//Skal udredes, men gider ikke lige nu. @TODO i guess.
		Main.mainRoot = new Group();
		Main.mainRoot.getChildren().add(Main.imageView);
		Main.mainRoot.getChildren().addAll(Main.frameworkRoot, Main.gameRoot); //Noget galt her tror jeg mainRoot er vist allerede en Group fra Main.

		Scene scene = new Scene(Main.mainRoot, Main.n, Main.m);
		Main.mainStage.setScene(scene);
		
		//Call Main.initMain()
		Main.initMain();
	}

}
