package framework;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

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
	 * Sets the scene of mainStage to one with two sliders and a text field for the
	 * user to set the size of the window and the amount of players.
	 *
	 * by: Helene & William
	 */
	public static void windowSize() {
		// Fields
		GridPane pane = new GridPane();
		Scene sceneSize = new Scene(pane, Main.startSizeW, Main.startSizeH);
		Text headingWHP = new Text("Set the width and height of the game window, and the number of players");
		Text widthPrompt = new Text();
		Text heightPrompt = new Text();
		Text numPlayer = new Text("Number of Player");
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
		widthSlider.setMajorTickUnit((widthSlider.getMax() - widthSlider.getMin()) / 10);
		widthSlider.setMinorTickCount(0);
		widthSlider.getStylesheets().add("SliderStyle.css");

		// height slider
		heightSlider.setShowTickMarks(true);
		heightSlider.setMajorTickUnit((heightSlider.getMax() - heightSlider.getMin()) / 10);
		heightSlider.setMinorTickCount(0);
		heightSlider.getStylesheets().add("SliderStyle.css");

		// the code for the width and height value labels
		widthPrompt.setText("Width: " + Integer.toString((int) widthSlider.getValue()));
		widthSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				widthPrompt.setText("Width: " + Integer.toString((int) widthSlider.getValue()));
			}
		});

		heightPrompt.setText("Height: " + Integer.toString((int) heightSlider.getValue()));
		heightSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				heightPrompt.setText("Height: " + Integer.toString((int) heightSlider.getValue()));
			}
		});

		// Style of the submit Btn
		submitBtn.setText("Submit");
		submitBtn.setBorder(null);
		submitBtn.setPrefSize(215, 55);
		submitBtn.setStyle(
				"-fx-background-color: linear-gradient(to right bottom, #42E596, #3CB3B8); -fx-background-radius: 25px;");
		submitBtn.setFont(new Font("Times New Roman", 25.0));
		submitBtn.setTextFill(Color.WHITE);
		submitBtn.setOnMouseEntered(event -> {
			submitBtn.setCursor(Cursor.HAND);
		});

		// Submits the values given when it's clicked
		submitBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Get values from sliders
				Main.n = (int) widthSlider.getValue();
				Main.m = (int) heightSlider.getValue();
				try {
					int count = Integer.parseInt(numPlayerTextField.getText());

					// Check if input from text field is within bounds
					if ((count < 2 || count > 6)) {
						throw new IllegalCallerException();
					}
					// set pAmount in Main
					Main.pAmount = count;

					// Call next input prompt
					playerNames();
				} catch (IllegalCallerException e) {
					numPlayer.setText("Only values between 2 and 6");
				} catch (Exception e) {
					numPlayer.setText("Please enter integers only!");
				}
			}
		});

		// Setting up the grid pane
		// pane.setGridLinesVisible(true);
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
		GridPane.setConstraints(widthSlider, 0, 4);
		GridPane.setColumnSpan(widthSlider, 2);

		GridPane.setConstraints(heightPrompt, 0, 6);
		GridPane.setConstraints(heightSlider, 0, 8);
		GridPane.setColumnSpan(heightSlider, 2);

		GridPane.setConstraints(numPlayer, 0, 10);
		GridPane.setConstraints(numPlayerTextField, 1, 10);

		GridPane.setHalignment(submitBtn, HPos.CENTER);
		GridPane.setConstraints(submitBtn, 0, 12);
		GridPane.setColumnSpan(submitBtn, 2);

		// Adding the elements to the GridPane
		pane.getChildren().addAll(headingWHP, widthPrompt, widthSlider, heightPrompt, heightSlider, numPlayer,
				numPlayerTextField, submitBtn);

		// Set scene on mainStage
		Main.mainStage.setScene(sceneSize);
	}

	/*
	 * playerNames()
	 * Sets the scene of mainStage to one with a text field and a button and asks
	 * all players, one after the other, to set their name.
	 *
	 * Helene & William
	 */
	public static void playerNames() {
		// GridPane setup
		GridPane grid = new GridPane();
		// grid.setGridLinesVisible(true);
		grid.setMinSize(650, 390);
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setVgap(7);
		grid.setHgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setAlignment(Pos.CENTER);

		// Create scene and add grid
		Scene sceneNames = new Scene(grid, Main.startSizeW, Main.startSizeH);

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
		btn.setStyle(
				"-fx-background-color: linear-gradient(to right bottom, #42E596, #3CB3B8); -fx-background-radius: 25px;");
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

		// Add components to grid
		grid.getChildren().addAll(prompt, name, btn);

		// Create button press event
		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					System.out.println(name.getText());

					// Limit length of name

					// Check if text field is left blank or is a single space and throw exception if yes.
					if (name.getText().equalsIgnoreCase("") || name.getText().equalsIgnoreCase(" ")) {
						throw new IllegalCallerException();
					} else {
						//Limit length of name
						if (name.getText().length() > 10) {
							prompt.setText("Please enter a shorter name");
						} else {
							// Add name to nList in Main and add 1 to pCount
							Main.nList.add(name.getText());
							pCount++;

							// If all names have been set, call levelSelect()
							if (pCount > Main.pAmount) {
								levelSelect();
							} else {
								// If not, clear text field and prompt next player
								prompt.setText("Player " + (pCount) + ", please enter a name");
								name.clear();
							}
						}
					}
				} catch (IllegalCallerException e) {
					prompt.setText("Please enter only letters and numbers");
				}
			}
		});

		// Set scene on stage
		Main.mainStage.setScene(sceneNames);
	}

	/*
	 * levelSelect()
	 *
	 * Creates the scene that allows the players to select which level they want to
	 * play. Calls startGame()
	 *
	 * By: Helene, William & Tore
	 */
	public static void levelSelect() {
		// Setup panes
		GridPane buttons = new GridPane();
		// buttons.setGridLinesVisible(true);
		buttons.setMinSize(650, 390);
		buttons.setAlignment(Pos.TOP_CENTER);
		buttons.setVgap(25);
		buttons.setHgap(25);
		buttons.setPadding(new Insets(10, 10, 10, 10));
		buttons.setAlignment(Pos.CENTER);

		VBox main = new VBox();
		main.setAlignment(Pos.CENTER);

		// Create scene and add "main"
		Scene sceneLevel = new Scene(main, Main.startSizeW, Main.startSizeH);

		// Create components
		Text prompt = new Text("Please select a level");
		Button cityLevel = new Button();
		Button forestLevel = new Button();
		Button mountainLevel = new Button();
		Button villageLevel = new Button();
		Button hillLevel = new Button();
		Button rockyHillLevel = new Button();
		Text city = new Text("City");
		Text forest = new Text("Forest");
		Text mountain = new Text("Mountain");
		Text village = new Text("village");
		Text hill = new Text("Hill");
		Text rocky = new Text("Rocky Hill");

		// Style of the text
		city.setFont(new Font("Times New Roman", 30.0));
		city.setStyle("    -fx-fill: white;\n"
				+ "    -fx-stroke: black;\n"
				+ "    -fx-stroke-width: 1;");

		forest.setFont(new Font("Times New Roman", 30.0));
		forest.setStyle("-fx-fill: white;\n"
				+ "    -fx-stroke: black;\n"
				+ "    -fx-stroke-width: 1;");

		mountain.setFont(new Font("Times New Roman", 30.0));
		mountain.setStyle("-fx-fill: white;\n"
				+ "    -fx-stroke: black;\n"
				+ "    -fx-stroke-width: 1;");

		village.setFont(new Font("Times New Roman", 30.0));
		village.setStyle("-fx-fill: white;\n"
				+ "    -fx-stroke: black;\n"
				+ "    -fx-stroke-width: 1;");

		hill.setFont(new Font("Times New Roman", 30.0));
		hill.setStyle("-fx-fill: white;\n"
				+ "    -fx-stroke: black;\n"
				+ "    -fx-stroke-width: 1;");

		rocky.setFont(new Font("Times New Roman", 30.0));
		rocky.setStyle("-fx-fill: white;\n"
				+ "    -fx-stroke: black;\n"
				+ "    -fx-stroke-width: 1;");

		// Init the prompt
		prompt.setFont(new Font("Times New Roman", 25.0));
		prompt.setStyle("-fx-translate-y: 75;");

		// Style of the buttons
		cityLevel.setBorder(null);
		cityLevel.setGraphic(city);
		cityLevel.setPrefSize(200, 75);
		cityLevel.setStyle("-fx-background-image: url(\"City.gif\"); -fx-background-position: center;");
		cityLevel.setFont(new Font("Times New Roman", 19.0));
		cityLevel.setTextFill(Color.BLACK);
		cityLevel.setOnMouseEntered(event -> {
			cityLevel.setCursor(Cursor.HAND);
		});

		forestLevel.setBorder(null);
		forestLevel.setGraphic(forest);
		forestLevel.setPrefSize(200, 75);
		forestLevel.setStyle("-fx-background-image: url(\"Forest.gif\"); -fx-background-position: center; -fx-stroke: white");
		forestLevel.setFont(new Font("Times New Roman", 19.0));
		forestLevel.setTextFill(Color.BLACK);
		forestLevel.setOnMouseEntered(event -> {
			forestLevel.setCursor(Cursor.HAND);
		});

		mountainLevel.setBorder(null);
		mountainLevel.setGraphic(mountain);
		mountainLevel.setPrefSize(200, 75);
		mountainLevel.setStyle("-fx-background-image: url(\"Mountain.gif\"); -fx-background-position: center;");
		mountainLevel.setFont(new Font("Times New Roman", 19.0));
		mountainLevel.setTextFill(Color.BLACK);
		mountainLevel.setOnMouseEntered(event -> {
			mountainLevel.setCursor(Cursor.HAND);
		});

		villageLevel.setBorder(null);
		villageLevel.setGraphic(village);
		villageLevel.setPrefSize(200, 75);
		villageLevel.setStyle("-fx-background-image: url(\"Village.gif\"); -fx-background-position: center");
		villageLevel.setFont(new Font("Times New Roman", 19.0));
		villageLevel.setTextFill(Color.BLACK);
		villageLevel.setOnMouseEntered(event -> {
			villageLevel.setCursor(Cursor.HAND);
		});

		hillLevel.setBorder(null);
		hillLevel.setGraphic(hill);
		hillLevel.setPrefSize(200, 75);
		hillLevel.setStyle("-fx-background-image: url(\"Hills.gif\"); -fx-background-position: center");
		hillLevel.setFont(new Font("Times New Roman", 19.0));
		hillLevel.setTextFill(Color.BLACK);
		hillLevel.setOnMouseEntered(event -> {
			hillLevel.setCursor(Cursor.HAND);
		});

		rockyHillLevel.setBorder(null);
		rockyHillLevel.setGraphic(rocky);
		rockyHillLevel.setPrefSize(200, 75);
		rockyHillLevel.setStyle("-fx-background-image: url(\"RockyHills.gif\"); -fx-background-position: center;");
		rockyHillLevel.setFont(new Font("Times New Roman", 19.0));
		rockyHillLevel.setTextFill(Color.BLACK);

		rockyHillLevel.setOnMouseEntered(event -> {
			rockyHillLevel.setCursor(Cursor.HAND);
		});

		// Set placement of components
		GridPane.setConstraints(cityLevel, 0, 0);
		GridPane.setConstraints(forestLevel, 0, 1);
		GridPane.setConstraints(mountainLevel, 1, 0);
		GridPane.setConstraints(villageLevel, 1, 1);
		GridPane.setConstraints(hillLevel, 2, 0);
		GridPane.setConstraints(rockyHillLevel, 2, 1);

		//Add components to panes
		buttons.getChildren().addAll(cityLevel,forestLevel,mountainLevel,villageLevel,hillLevel,rockyHillLevel, city, hill, mountain,
				rocky, village, forest);
		main.getChildren().addAll(prompt,buttons);

		// Set button press event for cityLevel
		cityLevel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Main.levelName = "CITY";
				Main.background = new Image("City.gif");
				startGame();
			}
		});

		// Set button press event for forestLevel
		forestLevel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Main.levelName = "FOREST";
				Main.background = new Image("Forest.gif");
				Main.imageView = new ImageView(Main.background);
				startGame();
			}
		});

		// Set button press event for mountainLevel
		mountainLevel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Main.levelName = "MOUNTAINS";
				Main.background = new Image("Mountain.gif");
				startGame();
			}
		});

		// Set button press event for villageLevel
		villageLevel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Main.levelName = "VILLAGE";
				Main.background = new Image("Village.gif");
				startGame();
			}
		});

		// Set button press event for hillLevel
		hillLevel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Main.levelName = "HILL";
				Main.background = new Image("Hills.gif");
				startGame();
			}
		});

		// Set button press event for rockyHillLevel
		rockyHillLevel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Main.levelName = "ROCKYHILLS";
				Main.background = new Image("RockyHills.gif");
				startGame();
			}
		});

		// Set scene on stage
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

		Main.imageView = new ImageView(Main.background);
		// reposition Stage - Skal m�ske laves om
		Main.mainStage.setX((Screen.getPrimary().getVisualBounds().getWidth() - Main.n) / 2);
		Main.mainStage.setY((Screen.getPrimary().getVisualBounds().getHeight() - Main.m) / 2);

		// set background image
		double w = Main.background.getWidth();
		double h = Main.background.getHeight();
		Main.imageView.setPreserveRatio(true);
		if ((Main.n / w) > (Main.m / h)) {
			Main.imageView.setFitWidth(Main.n);
		} else
			Main.imageView.setFitHeight(Main.m);

		// Skal udredes, men gider ikke lige nu. @TODO i guess.
		Main.mainRoot = new Group();
		Main.mainRoot.getChildren().add(Main.imageView);
		Main.mainRoot.getChildren().addAll(Main.frameworkRoot, Main.gameRoot); // Noget galt her tror jeg mainRoot er
																				// vist allerede en Group fra Main.

		Scene scene = new Scene(Main.mainRoot, Main.n, Main.m);
		Main.mainStage.setScene(scene);

		// Call Main.initMain()
		Main.initMain();
	}

}
