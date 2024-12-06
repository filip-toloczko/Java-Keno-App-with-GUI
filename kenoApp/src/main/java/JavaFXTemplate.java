import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.awt.print.Pageable;


public class JavaFXTemplate extends Application
{
	private HBox welcomeHBox;
	gameplayScreen gameScreen = new gameplayScreen();

	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		BorderPane gameBorderPane = gameScreen.getBorderPane();

		primaryStage.setTitle("Keno Lottery Game");

		// Initializing new menu for welcome screen
		welcomeHBox = getMenu(false);

		// Setting up Start button to begin gameplay and generate new scene w/ appropriate children
		Button startButton  = new Button("Start");
		startButton.setTranslateY(100);
		startButton.setPrefSize(150, 60);
		startButton.setFont(Font.font("ROBOTO", FontWeight.BOLD, 25));

		startButton.setOnAction(e ->
		{
			// Setting scene to gameplay screen
			primaryStage.getScene().setRoot(gameBorderPane);
		});

		// Setting up welcomePane
		Label welcomeText = new Label("Welcome to the Keno Lottery Game!");
		welcomeText.setFont(Font.font("ROBOTO", FontPosture.ITALIC, 30));
		welcomeText.setTranslateY(-90);

		Pane welcomePane = new StackPane(welcomeHBox, startButton, welcomeText);
		welcomePane.setPrefSize(700, 700);

		// Setting up welcomeScene
		Scene welcomeScene = new Scene(welcomePane, 700, 500);


		primaryStage.setScene(welcomeScene);
		primaryStage.show();

	}

	// This method will initialize the menu and its respective menu items
	public HBox getMenu(boolean gameStarted)
	{
		HBox returnWelcomeHBox;

		// Strings to later be used within menu items
		String gameRules = "How the game is played:\n" +
				"Keno is a popular gambling game offered in many modern casinos and also offered as a\n" +
				"game in many state lotteries.\n" +
				"Players wager by choosing a set amount of numbers( pick 2 numbers, pick 10 numbers,\n" +
				"etc.) ranging from 1 to 80. After all players have made their wagers and picked their\n" +
				"numbers, twenty numbers are drawn at random, between 1 and 80 with no duplicates.\n" +
				"Players win by matching a set amount of their numbers to the numbers that are\n" +
				"randomly drawn";
		String gameOdds = "These are the odds to the Keno Lottery Game if all matching numbers are to be chosen:\n\n" +
				"- If 1 spot is chosen: 1/4.00\n" +
				"- If 4 spots are chosen: 1/3.86\n" +
				"- If 8 spots are chosen: 1/9.77\n" +
				"- If 10 spots are chosen: 1/9.05\n\n" +
				"Happy playing!";

		// Setting up MenuBar for welcome screen
		MenuBar welcomeMenuBar = new MenuBar();
		welcomeMenuBar.setMinWidth(65);
		returnWelcomeHBox = new HBox(welcomeMenuBar);

		// Setting up Menu Items
		Menu welcomeMenu = new Menu("Menu");

		// Assigning welcomeMenu to welcomeMenuBar
		welcomeMenuBar.getMenus().add(welcomeMenu);

		// Creating Alerts for menu items
		Alert rulesAlert = new Alert(AlertType.INFORMATION);
		rulesAlert.setTitle("Keno Lottery Game Rules");
		rulesAlert.setHeaderText(null);
		rulesAlert.setContentText(gameRules);
		rulesAlert.initStyle(StageStyle.UTILITY);

		Alert oddsAlert = new Alert(AlertType.INFORMATION);
		oddsAlert.setTitle("Keno Lottery Game Odds");
		oddsAlert.setHeaderText(null);
		oddsAlert.setContentText(gameOdds);
		oddsAlert.setHeight(500);
		oddsAlert.initStyle(StageStyle.UTILITY);

		// Assigning menu items to welcomeMenu
		MenuItem rulesMenuItem = new MenuItem("Display Rules");
		MenuItem oddsMenuItem = new MenuItem("Display Odds");
		MenuItem exitMenuItem = new MenuItem("Exit Game");

		// Event handlers for menu items
		rulesMenuItem.setOnAction(e -> rulesAlert.showAndWait());
		oddsMenuItem.setOnAction(e -> oddsAlert.showAndWait());
		exitMenuItem.setOnAction(e -> System.exit(0));


		// Adding menu items to menu bar
		welcomeMenu.getItems().add(rulesMenuItem);
		welcomeMenu.getItems().add(oddsMenuItem);
		welcomeMenu.getItems().add(exitMenuItem);

		return returnWelcomeHBox;
	}
}
