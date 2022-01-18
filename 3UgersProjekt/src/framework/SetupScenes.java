package framework;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import gameObjects.LevelParts.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
	/*
	 * windowSize()
	 *
	 * Changes the frameworkRoot from Main to show two sliders and prompts the user
	 * to set the size of the game-window.
	 *
	 * by: William Holberg
	 */
	public static void windowSize() {
		// Fields
		GridPane pane = new GridPane();
		Scene sceneSize = new Scene(pane, Main.startSizeW, Main.startSizeH);
		Text headingWHP = new Text("Set the width and height of the game window, and the number of players");
		Text widthPrompt = new Text("Widh: ");
		Text heightPrompt = new Text("Height: ");
		Text numPlayer = new Text("Number of Player");
		Label widthValueDisplay = new Label();
		Label heightValueDisplay = new Label();
		TextField numPlayerTextField = new TextField();
		Slider widthSlider = new Slider(300, Screen.getPrimary().getBounds().getWidth(), 800);
		Slider heightSlider = new Slider(300, Screen.getPrimary().getBounds().getHeight(), 400);
		Button submitBtn = new Button();

		// Initializing the Nodes
		headingWHP.setFont(new Font("Times New Roman", 20.0));
		widthPrompt.setFont(new Font("Times New Roman", 19.0));
		heightPrompt.setFont(new Font("Times New Roman", 19.0));
		numPlayer.setFont(new Font("Times New Roman", 19.0));
		numPlayerTextField.setPromptText("2 to 6");
		numPlayerTextField.setMaxWidth(265);
		numPlayerTextField.setMinWidth(260);

		// width slider
		widthSlider.setShowTickMarks(true);
		widthSlider.setMajorTickUnit((widthSlider.getMax() - widthSlider.getMin())/10);
		widthSlider.setMinorTickCount(0);
		widthSlider.getStylesheets().add("SliderStyle.css");


		// height slider
		heightSlider.setShowTickMarks(true);
		heightSlider.setMajorTickUnit((heightSlider.getMax() - heightSlider.getMin())/10);
		heightSlider.setMinorTickCount(0);
		heightSlider.getStylesheets().add("SliderStyle.css");


		// the code for the width and height value labels
		widthValueDisplay.setText(Integer.toString((int)widthSlider.getValue()));
		widthValueDisplay.setFont(new Font("Times New Roman", 19.0));
		widthSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				widthValueDisplay.setText(Integer.toString((int)widthSlider.getValue()));
			}
		});

		heightValueDisplay.setText(Integer.toString((int)heightSlider.getValue()));
		heightValueDisplay.setFont(new Font("Times New Roman", 19.0));
		heightSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				heightValueDisplay.setText(Integer.toString((int)heightSlider.getValue()));
			}
		});

		// Style of the submit Btn
		submitBtn.setText("Submit");
		submitBtn.setBorder(null);
		submitBtn.setPrefSize(215, 55);
		submitBtn.setStyle("-fx-background-color: linear-gradient(to right bottom, #42E596, #3CB3B8); -fx-background-radius: 25px;");
		submitBtn.setFont(new Font("Times New Roman", 25.0));
		submitBtn.setTextFill(Color.WHITE);
		submitBtn.setOnMouseEntered(event -> {
			submitBtn.setCursor(Cursor.HAND);
		});

		// Submits the values given when it's clicked
		submitBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//Get values from sliders
				Main.n = (int) widthSlider.getValue();
				Main.m = (int) heightSlider.getValue();



				try {
					int count = Integer.parseInt(numPlayerTextField.getText());

					//Check if input from text field is within bounds
					if ((count < 2 || count > 6)) {
						throw new IllegalCallerException();
					}
					// set pAmount in Main
					Main.pAmount = count;

					//Call next input prompt
					playerNames();
				} catch (IllegalCallerException e) {
					numPlayer.setText("Only values between 2 and 6");
				} catch (Exception e) {
					numPlayer.setText("Please enter integers only!");
				}
			}
		});

		// Setting up the grid pane
		//pane.setGridLinesVisible(true);
		pane.setMinSize(650, 390);
		pane.setAlignment(Pos.TOP_CENTER);
		pane.setVgap(7);
		pane.setHgap(10);
		pane.setPadding(new Insets(10, 10, 10, 10));

		// Placing the elements inside the grid
		GridPane.setHalignment(headingWHP, HPos.CENTER);
		GridPane.setConstraints(headingWHP, 0, 1);
		GridPane.setColumnSpan(headingWHP, 2);

		GridPane.setConstraints(widthPrompt, 0, 2);
		GridPane.setConstraints(widthValueDisplay, 0, 2);
		GridPane.setHalignment(widthValueDisplay, HPos.RIGHT);
		GridPane.setConstraints(widthSlider, 0, 4);
		GridPane.setColumnSpan(widthSlider, 2);

		GridPane.setConstraints(heightPrompt, 0, 6);
		GridPane.setConstraints(heightValueDisplay, 0, 6);
		GridPane.setHalignment(heightValueDisplay, HPos.RIGHT);
		GridPane.setConstraints(heightSlider, 0,8);
		GridPane.setColumnSpan(heightSlider, 2);

		GridPane.setConstraints(numPlayer, 0, 10);
		GridPane.setConstraints(numPlayerTextField, 1, 10);

		GridPane.setHalignment(submitBtn, HPos.CENTER);
		GridPane.setConstraints(submitBtn, 0, 12);
		GridPane.setColumnSpan(submitBtn, 2);

		// Adding the elements to the GridPane
		pane.getChildren().addAll(headingWHP, widthPrompt, widthSlider, heightPrompt, heightSlider, numPlayer,
				numPlayerTextField, submitBtn, widthValueDisplay, heightValueDisplay);

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
		Scene sceneAmount = new Scene(grid,Main.startSizeW,Main.startSizeH);

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
		//grid.setGridLinesVisible(true);
		grid.setMinSize(650, 390);
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setVgap(7);
		grid.setHgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setAlignment(Pos.CENTER);

		//Create scene and add grid
		Scene sceneNames = new Scene(grid,Main.startSizeW,Main.startSizeH);

		// Create components
		Text prompt = new Text("Player " + (pCount) + ", please enter a name");
		TextField name = new TextField();
		Button btn = new Button("Submit");

		// style the components
		prompt.setFont(new Font("Times New Roman", 25.0));
		name.setPrefWidth(350);
		name.setPromptText("Enter name");

		btn.setText("Submit");
		btn.setBorder(null);
		btn.setPrefSize(115, 30);
		btn.setStyle("-fx-background-color: linear-gradient(to right bottom, #42E596, #3CB3B8); -fx-background-radius: 25px;");
		btn.setFont(new Font("Times New Roman", 19.0));
		btn.setTextFill(Color.WHITE);
		btn.setOnMouseEntered(event -> {
			btn.setCursor(Cursor.HAND);
		});


		// Set placement of components
		GridPane.setConstraints(prompt, 0, 0);
		GridPane.setConstraints(name, 1, 0);
		GridPane.setConstraints(btn, 0, 2);
		GridPane.setHalignment(btn, HPos.CENTER);
		GridPane.setColumnSpan(btn, 2);


		//Add components to grid
		grid.getChildren().addAll(prompt, name, btn);

		//Create button press event
		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					//Check if text field is left blank and throw exception if yes.
					if (name.getText().equalsIgnoreCase("") && name.getText().equalsIgnoreCase(" ")) {
						throw new IllegalCallerException();
					} else {
						//Add name to nList in Main and add 1 to pCount
						Main.nList.add(name.getText());
						pCount++;

						//If all names have been set, clear frameworkRoot and call startGame()
						if (pCount > Main.pAmount) {
							Main.frameworkRoot.getChildren().clear();
							levelSelect();
						} else {
							//If not, clear text field and prompt next player
							prompt.setText("Player " + (pCount) + ", please enter a name");
							name.clear();
						}
					}
				} catch (IllegalCallerException e) {
					prompt.setText("Please enter a name, player" + pCount);
				}

				// @TODO Lav endnu en exception s� man ikke kan lave for lange navne
			}
		});

		//Set scene on stage
		Main.mainStage.setScene(sceneNames);
	}

	public static void levelSelect() {
		//Setup panes
		GridPane buttons = new GridPane();
		buttons.setHgap(10);
		buttons.setVgap(10);
		buttons.setPadding(new Insets(10, 10, 10, 10));
		VBox main = new VBox();

		//Create scene and add "main"
		Scene sceneLevel = new Scene(main,Main.startSizeW,Main.startSizeH);

		//Create components
		Text prompt = new Text("Please select a level");
		Button cityLevel = new Button("City");
		Button forestLevel = new Button("Forest");
		Button mountainLevel = new Button("Mountain");
		Button villageLevel = new Button("Village");
		Button hillLevel = new Button("Hills");
		Button rockyHillLevel = new Button("Rocky hills");

		//Set placement of components
		GridPane.setConstraints(cityLevel, 0, 0);
		GridPane.setConstraints(forestLevel, 0, 1);
		GridPane.setConstraints(mountainLevel, 1, 0);
		GridPane.setConstraints(villageLevel, 1, 1);
		GridPane.setConstraints(hillLevel, 2, 0);
		GridPane.setConstraints(rockyHillLevel, 2, 1);

		//Add components to panes
		buttons.getChildren().addAll(cityLevel,forestLevel,mountainLevel,villageLevel,hillLevel,rockyHillLevel);
		main.getChildren().addAll(prompt,buttons);

		//Set button press event for cityLevel
		cityLevel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Main.levelName = "CITY";
				startGame();
			}
		});


		//Set button press event for forestLevel
		forestLevel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Main.levelName = "FOREST";
				startGame();
			}
		});

		//Set button press event for mountainLevel
		mountainLevel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Main.levelName = "MOUNTAINS";
				startGame();
			}
		});

		//Set button press event for villageLevel
		villageLevel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Main.levelName = "VILLAGE";
				startGame();
			}
		});

		//Set button press event for hillLevel
		hillLevel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Main.levelName = "HILL";
				startGame();
			}
		});

		//Set button press event for rockyHillLevel
		rockyHillLevel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Main.levelName = "ROCKYHILLS";
				startGame();
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
