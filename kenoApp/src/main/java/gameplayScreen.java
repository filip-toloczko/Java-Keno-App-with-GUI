import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;

public class gameplayScreen extends Application
{
    private final GridPane numberGridPane;
    private final BorderPane gameBorderPane = new BorderPane();
    HBox topBar = new HBox();
    private int btnNumber = 0;
    private int userNumSpots = 0;

    private int userNumDraws = 0;

    public gameLogic gameLogicObj = new gameLogic();

    Button oneSpotBtn = new Button("1");;
    Button fourSpotBtn = new Button("4");;
    Button eightSpotBtn = new Button("8");;
    Button tenSpotBtn = new Button("10");;

    Button oneDrawBtn = new Button("1");
    Button twoDrawBtn = new Button("2");
    Button threeDrawBtn = new Button("3");
    Button fourDrawBtn = new Button("4");

    Button randomPickButton = new Button("Choose Random Spots");
    Button resetButton = new Button("Reset Game");
    Button confirmButton = new Button("Confirm Choices");

    @Override
    public void start(Stage primaryStage) throws Exception
    {

    }

    // This method will return the gameLogic instance
    public gameLogic getGameLogicObj()
    {
        return gameLogicObj;
    }

    // Method to set up the gamePlayScreen
    public gameplayScreen()
    {
        // Setting up GridPane for 10 x 8 grid of buttons
        numberGridPane = new GridPane();
        numberGridPane.setPadding(new Insets(10));
        numberGridPane.setHgap(10);
        numberGridPane.setVgap(10);
        numberGridPane.setAlignment(Pos.CENTER);

        // This block will generate all of the numbered buttons onto GridPane
        for (int rowNum = 0; rowNum < 10; rowNum++)
        {
            for (int colNum = 0; colNum < 8; colNum++)
            {
                Button newButton = createButton();
                numberGridPane.add(newButton, colNum, rowNum);
            }
        }
    }

    // This method will return a button to be used within the game grid pane
    private Button createButton()
    {
        btnNumber++;

        Button newButton = new Button(String.valueOf(btnNumber));

        newButton.setId(String.valueOf(btnNumber));

        newButton.setOnAction(e ->
        {
            // Condition to add number to vector of picked numbers when clicked
            gameLogicObj.addNumber(Integer.parseInt(newButton.getId()));

            if(gameLogicObj.userNumbers.size() == gameLogicObj.getSpotCount()){
                for (Node node : numberGridPane.getChildren()) {
                    // disable the button
                    node.setDisable(true);
                }
            }

            // Then have button to be disabled
            newButton.setDisable(true);
        });
        return newButton;
    }

    // This method will return the built GridPane
    public GridPane getRootPane()
    {
        return numberGridPane;
    }

    // This method will return the borderPane which contains the numberGridPane
    public BorderPane getBorderPane()
    {
        gameBorderPane.setCenter(getRootPane());

        // Initializing menu for gameplay screen
        HBox gameplayMenuHBox;

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

        MenuBar gameplayMenuBar = new MenuBar();
        gameplayMenuBar.setMinWidth(65);
        gameplayMenuHBox = new HBox(gameplayMenuBar);

        Menu gameplayMenu = new Menu("Menu");

        gameplayMenuBar.getMenus().add(gameplayMenu);

        // Creating Alerts for menu items
        Alert rulesAlert = new Alert(Alert.AlertType.INFORMATION);
        rulesAlert.setTitle("Keno Lottery Game Rules");
        rulesAlert.setHeaderText(null);
        rulesAlert.setContentText(gameRules);
        rulesAlert.initStyle(StageStyle.UTILITY);

        Alert oddsAlert = new Alert(Alert.AlertType.INFORMATION);
        oddsAlert.setTitle("Keno Lottery Game Odds");
        oddsAlert.setHeaderText(null);
        oddsAlert.setContentText(gameOdds);
        oddsAlert.setHeight(500);
        oddsAlert.initStyle(StageStyle.UTILITY);

        // Assigning menu items to welcomeMenu
        MenuItem rulesMenuItem = new MenuItem("Display Rules");
        MenuItem oddsMenuItem = new MenuItem("Display Odds");
        MenuItem exitMenuItem = new MenuItem("Exit Game");
        MenuItem newLookMenuItem = new MenuItem("New Look");

        newLookMenuItem.setOnAction(e -> {
            changeButtonColor();
            gridColor();

        });

        rulesMenuItem.setOnAction(e -> rulesAlert.showAndWait());
        oddsMenuItem.setOnAction(e -> oddsAlert.showAndWait());
        exitMenuItem.setOnAction(e -> System.exit(0));

        // Adding menu items to menu bar
        gameplayMenu.getItems().add(newLookMenuItem);
        gameplayMenu.getItems().add(rulesMenuItem);
        gameplayMenu.getItems().add(oddsMenuItem);
        gameplayMenu.getItems().add(exitMenuItem);

        for (Node node : numberGridPane.getChildren()) {
            if (userNumSpots == 0 && userNumDraws == 0) {
                // disable the button
                node.setDisable(true);
            }
        }

        // Adding Menu to gameplay screen
        gameBorderPane.getChildren().add(gameplayMenuHBox);

        randomPickButton.setDisable(true);

        // Disable the draw buttons until player picks spots
        gameLogicObj.disableFourButtons(oneDrawBtn, twoDrawBtn, threeDrawBtn, fourDrawBtn);

        // Event handlers for spot buttons
        oneSpotBtn.setOnAction(event ->
        {
            gameLogicObj.setSpotCount(1);
            gameLogicObj.disableFourButtons(oneSpotBtn, fourSpotBtn, eightSpotBtn, tenSpotBtn);
            gameLogicObj.enableFourButtons(oneDrawBtn, twoDrawBtn, threeDrawBtn, fourDrawBtn);
            gameLogicObj.enableGrid(numberGridPane);
            randomPickButton.setDisable(false);
        });
        fourSpotBtn.setOnAction(event ->
        {
            gameLogicObj.setSpotCount(4);
            gameLogicObj.disableFourButtons(oneSpotBtn, fourSpotBtn, eightSpotBtn, tenSpotBtn);
            gameLogicObj.enableFourButtons(oneDrawBtn, twoDrawBtn, threeDrawBtn, fourDrawBtn);
            gameLogicObj.enableGrid(numberGridPane);
            randomPickButton.setDisable(false);

        });
        eightSpotBtn.setOnAction(event ->
        {
            gameLogicObj.setSpotCount(8);
            gameLogicObj.disableFourButtons(oneSpotBtn, fourSpotBtn, eightSpotBtn, tenSpotBtn);
            gameLogicObj.enableFourButtons(oneDrawBtn, twoDrawBtn, threeDrawBtn, fourDrawBtn);
            gameLogicObj.enableGrid(numberGridPane);
            randomPickButton.setDisable(false);
        });
        tenSpotBtn.setOnAction(event ->
        {
            gameLogicObj.setSpotCount(10);
            gameLogicObj.disableFourButtons(oneSpotBtn, fourSpotBtn, eightSpotBtn, tenSpotBtn);
            gameLogicObj.enableFourButtons(oneDrawBtn, twoDrawBtn, threeDrawBtn, fourDrawBtn);
            gameLogicObj.enableGrid(numberGridPane);
            randomPickButton.setDisable(false);
        });

        // Event handlers for draw buttons
        oneDrawBtn.setOnAction(event ->
        {
            gameLogicObj.setDrawCount(1);
            gameLogicObj.disableFourButtons(oneDrawBtn, twoDrawBtn, threeDrawBtn, fourDrawBtn);
        });
        twoDrawBtn.setOnAction(event ->
        {
            gameLogicObj.setDrawCount(2);
            gameLogicObj.disableFourButtons(oneDrawBtn, twoDrawBtn, threeDrawBtn, fourDrawBtn);
        });
        threeDrawBtn.setOnAction(event ->
        {
            gameLogicObj.setDrawCount(3);
            gameLogicObj.disableFourButtons(oneDrawBtn, twoDrawBtn, threeDrawBtn, fourDrawBtn);
        });
        fourDrawBtn.setOnAction(event ->
        {
            gameLogicObj.setDrawCount(4);
            gameLogicObj.disableFourButtons(oneDrawBtn, twoDrawBtn, threeDrawBtn, fourDrawBtn);
        });

        // Add right bar to gameplay screen
        VBox rightBar = new VBox(10);
        rightBar.setPadding(new Insets(10));

        Label rules = new Label("Please Choose Number of Spots,\n" +
                "Followed by a Number of Draws.\n" +
                "Finally Choose the Spots You Wish to Play Using the\n" +
                "Grid to the Left. Have Fun! Gamble Responsibly!\n\n" +
                "Have Gambling Issues? Call 1-800-GAMBLER\n\n\n\n\n\n\n");

        // Add spot buttons to HBox
        HBox spotsPick = new HBox(7.5);
        Label spotsTextField = new Label("Choose No. of Spots:  ");
        spotsPick.getChildren().add(spotsTextField);
        spotsPick.getChildren().add(oneSpotBtn);
        spotsPick.getChildren().add(fourSpotBtn);
        spotsPick.getChildren().add(eightSpotBtn);
        spotsPick.getChildren().add(tenSpotBtn);

        // Add draw buttons to HBox
        HBox drawPick = new HBox(7.5);
        Label drawsTextField = new Label("Choose No. of Draws:  ");
        drawPick.getChildren().add(drawsTextField);
        drawPick.getChildren().add(oneDrawBtn);
        drawPick.getChildren().add(twoDrawBtn);
        drawPick.getChildren().add(threeDrawBtn);
        drawPick.getChildren().add(fourDrawBtn);

        // Add the spot and draw HBox's to the rightBar
        rightBar.getChildren().add(rules);
        rightBar.getChildren().add(spotsPick);
        rightBar.getChildren().add(drawPick);
        gameBorderPane.setRight(rightBar);
        rightBar.setAlignment(Pos.CENTER);

        // Event handlers for the random button
        randomPickButton.setOnAction(event ->
        {
            gameLogicObj.getRandUserNums();
        });

        // Add the randomPickButton to the rightBar
        rightBar.getChildren().add(randomPickButton);

        // Event handler for the confirm button
        confirmButton.setOnAction(event ->
        {
            if(gameLogicObj.userNumbers.size() == gameLogicObj.getSpotCount() && gameLogicObj.getSpotCount() != 0){
                //Disable the spot and draw buttons
                gameLogicObj.disableFourButtons(oneSpotBtn,fourSpotBtn, eightSpotBtn,tenSpotBtn);
                gameLogicObj.disableFourButtons(oneDrawBtn, twoDrawBtn, threeDrawBtn, fourDrawBtn);

                // Get winning numbers
                for(int draw = 0; draw < gameLogicObj.userNumDraws; draw++) {
                    gameLogicObj.allDraws.add(gameLogicObj.drawWinningNums());
                }

                // Get the matching numbers
                gameLogicObj.findMatchingNums();

                // Display the results of the draw
                gameLogicObj.showDrawnNums();

                String exitText = "Would you like to Play Again or Exit?";

                ButtonType playButton = new ButtonType("Play Again");
                ButtonType exitButton = new ButtonType("Exit");

                Dialog<ButtonType> exitDialog = new Dialog<>();
                exitDialog.getDialogPane().getButtonTypes().addAll(playButton, exitButton);
                exitDialog.setContentText(exitText);
                exitDialog.setTitle("Exit Screen");

                // See if the user wishes to play again or not
                exitDialog.setResultConverter(dialogButton -> {
                    if (dialogButton == exitButton)
                    {
                        System.exit(0);
                    }
                    return dialogButton;
                });

                exitDialog.showAndWait();

                // Reset values to default
                gameLogicObj.allDraws.clear();
                gameLogicObj.matchingNumbersDraw.clear();
                gameLogicObj.resultStrings.clear();
                gameLogicObj.disableGrid(numberGridPane);
                gameLogicObj.enableFourButtons(oneSpotBtn, fourSpotBtn, eightSpotBtn, tenSpotBtn);
                gameLogicObj.enableFourButtons(oneDrawBtn, twoDrawBtn, threeDrawBtn, fourDrawBtn);
                gameLogicObj.userNumbers.clear();
                randomPickButton.setDisable(true);
            }
            // Inform the user that they have not selevted a sufficient amount of buttons
            else{
                Alert confirmError = new Alert(Alert.AlertType.WARNING, "Not Enough Spots Selected");
                confirmError.showAndWait();
            }
        });

        // Add the confirm button to the rightBar
        rightBar.getChildren().add(confirmButton);

        // Event handler for the resetButton
        resetButton.setOnAction(event ->
        {
            gameLogicObj.disableGrid(numberGridPane);
            gameLogicObj.enableFourButtons(oneSpotBtn, fourSpotBtn, eightSpotBtn, tenSpotBtn);
            gameLogicObj.enableFourButtons(oneDrawBtn, twoDrawBtn, threeDrawBtn, fourDrawBtn);
            gameLogicObj.userNumbers.clear();
            randomPickButton.setDisable(true);
        });

        // Add the resetButton to the rightBar
        rightBar.getChildren().add(resetButton);

        return gameBorderPane;
    }

    // This method will change the colors of the specified buttons
    public void changeButtonColor()
    {
        oneSpotBtn.setStyle("-fx-background-color:#00FFFF;");
        fourSpotBtn.setStyle("-fx-background-color:#00FFFF;");
        eightSpotBtn.setStyle("-fx-background-color:#00FFFF;");
        tenSpotBtn.setStyle("-fx-background-color:#00FFFF;");

        oneDrawBtn.setStyle("-fx-background-color:#00FFFF;");
        twoDrawBtn.setStyle("-fx-background-color:#00FFFF;");
        threeDrawBtn.setStyle("-fx-background-color:#00FFFF;");
        fourDrawBtn.setStyle("-fx-background-color:#00FFFF;");

        confirmButton.setStyle("-fx-background-color:#00FFFF;");
        resetButton.setStyle("-fx-background-color:#00FFFF;");
        randomPickButton.setStyle("-fx-background-color:#00FFFF;");

        System.out.println("I'm right here.");
    }

    public void gridColor(){
        numberGridPane.setStyle("-fx-background-color: #00FFFF;");
    }
}