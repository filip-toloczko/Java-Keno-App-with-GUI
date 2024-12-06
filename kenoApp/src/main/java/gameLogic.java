import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class gameLogic extends Application
{
    public ArrayList <Integer> userNumbers = new ArrayList<>();
    public ArrayList<HashSet<Integer>> allDraws = new ArrayList<>();
    ArrayList <Integer> matchingNumbers = new ArrayList<>();
    ArrayList <ArrayList<Integer>> matchingNumbersDraw = new ArrayList<>();
    ArrayList <Integer> moneyPerDraw = new ArrayList<>();
    ArrayList <String> resultStrings = new ArrayList<>();
    public int userNumSpots = 0;
    public int userNumDraws = 0;
    public int drawNumber = 0;
    public int totalMoneyWon = 0;
    public int moneyWon = 0;

    // This method will add the specified number to the vector of user selected numbers
    public void addNumber(int btnNumber)
    {
        userNumbers.add(btnNumber);
    }

    // This method will return the size of the vector with user selected numbers
    public int userNumCount()
    {
        return userNumbers.size();
    }

    // This method will set the number of spots the user specifies
    public void setSpotCount(int numOfSpots) {userNumSpots = numOfSpots; }

    // This method will return the number of spots the user specified
    public int getSpotCount()
    {
        return userNumSpots;
    }

    // This method will set the number of drawings the user specifies
    public void setDrawCount(int numOfDraws)
    {
        userNumDraws = numOfDraws;
    }

    // This method will return the number of spots the user specified
    public int getDrawCount()
    {
        return userNumDraws;
    }

    // This method will return the winning numbers for a draw
    public HashSet<Integer> getWinningNums(){
        return drawWinningNums();
    }

    // This method will return the numbers chosen by the user
    public ArrayList<Integer> getUserNumbers() {
        return userNumbers;
    }

    // This method will generate a random number 1 - 80
    public static int randomNumGen()
    {
        Random randObj = new Random();
        int lowerBound = 1;
        int upperBound = 80;
        int randomInt = randObj.nextInt(upperBound - lowerBound) + lowerBound;

        return randomInt;
    }

    // This method will choose the user's numbers if they haven't chosen any yet
    public void getRandUserNums()
    {
        // Clear out any potential prior choices
        userNumbers.clear();

        // Add random values into userNums
        while (userNumbers.size() != getSpotCount()) {
            userNumbers.add(randomNumGen());
        }
    }

    // This method will randomly draw the winning numbers
    public HashSet<Integer> drawWinningNums()
    {
        HashSet<Integer> randomNums = new HashSet<Integer>();

        // Populate randomNums with Random values
        while(randomNums.size() != 20)
        {
            randomNums.add(randomNumGen());
        }

        return randomNums;
    }

    // This method will look for matches amongst the user's numbers and the drawn numbers
    public void findMatchingNums()
    {
        for(int draw = 0; draw < userNumDraws; draw++) {
            // Make sure that matchingNumbers is empty before each draw
            matchingNumbers.clear();
            HashSet<Integer> winningNumbers = allDraws.get(draw);

            // Check if the chosen numbers are in the set of winning numbers
            for (Integer e : winningNumbers) {
                // If so add it to matchingNumbers
                if (userNumbers.contains(e)) {
                    matchingNumbers.add(e);
                }
            }

            // Add the matching numbers from this draw to the array storing matching numbers for all the draws
            matchingNumbersDraw.add(matchingNumbers);

            // Handle adding money depending on the number of spots the user chose
            if (userNumSpots == 1) {
                if (matchingNumbers.size() == 1) {
                    moneyWon += 2;
                    totalMoneyWon += 2;
                }
            } else if (userNumSpots == 4) {
                if (matchingNumbers.size() == 2) {
                    moneyWon += 1;
                    totalMoneyWon += 1;
                } else if (matchingNumbers.size() == 3) {
                    moneyWon += 5;
                    totalMoneyWon += 5;
                } else if (matchingNumbers.size() == 4) {
                    moneyWon += 75;
                    totalMoneyWon += 75;
                }
            } else if (userNumSpots == 8) {
                if (matchingNumbers.size() == 4) {
                    moneyWon += 2;
                    totalMoneyWon += 2;
                } else if (matchingNumbers.size() == 5) {
                    moneyWon += 12;
                    totalMoneyWon += 12;
                } else if (matchingNumbers.size() == 6) {
                    moneyWon += 50;
                    totalMoneyWon += 50;
                } else if (matchingNumbers.size() == 7) {
                    moneyWon += 750;
                    totalMoneyWon += 750;
                } else if (matchingNumbers.size() == 8) {
                    moneyWon += 1000;
                    totalMoneyWon += 1000;
                }
            } else if (userNumSpots == 10) {
                if (matchingNumbers.size() == 0) {
                    moneyWon += 5;
                    totalMoneyWon += 5;
                } else if (matchingNumbers.size() == 5) {
                    moneyWon += 2;
                    totalMoneyWon += 2;
                } else if (matchingNumbers.size() == 6) {
                    moneyWon += 15;
                    totalMoneyWon += 15;
                } else if (matchingNumbers.size() == 7) {
                    moneyWon += 40;
                    totalMoneyWon += 40;
                } else if (matchingNumbers.size() == 8) {
                    moneyWon += 450;
                    totalMoneyWon += 450;
                } else if (matchingNumbers.size() == 9) {
                    moneyWon += 4250;
                    totalMoneyWon += 4250;
                } else if (matchingNumbers.size() == 10) {
                    moneyWon += 100000;
                    totalMoneyWon += 100000;
                }
            }

            // Add the current moneyWon to moneyWonPerDraw
            moneyPerDraw.add(moneyWon);
            // Clear moneyWon variable for the next draw
            moneyWon = 0;

            // Concatenating results to string
            String resultString = "The number of matches are: ";
            resultString += matchingNumbersDraw.get(draw).size() + "\n\n";
            if(matchingNumbersDraw.get(draw).size() != 0){
                resultString += "Matching Numbers: ";
                for(int i = 0; i < matchingNumbersDraw.get(draw).size(); i++){
                    resultString += matchingNumbersDraw.get(draw).get(i) + " ";
                }
                resultString += "\n\n";
            }
            else{
                resultString += "No matching numbers this draw\n\n";
            }
            resultString += "Money won this draw: $" + moneyPerDraw.get(drawNumber)+ "\n\n";
            resultString += "Total money won: $" + totalMoneyWon;

            // Iterate drawNumber
            drawNumber += 1;
            // Add the resultString to the array of resultStrings for every draw
            resultStrings.add(resultString);
        }
    }

    // Used to disable spot or draw buttons
    public void disableFourButtons(Button spotButton1, Button spotButton4, Button spotButton8, Button spotButton10)
    {
        spotButton1.setDisable(true);
        spotButton4.setDisable(true);
        spotButton8.setDisable(true);
        spotButton10.setDisable(true);
    }

    // Used to enable spot or draw buttons
    public void enableFourButtons(Button spotButton1, Button spotButton4, Button spotButton8, Button spotButton10)
    {
        spotButton1.setDisable(false);
        spotButton4.setDisable(false);
        spotButton8.setDisable(false);
        spotButton10.setDisable(false);
    }

    // Used to change color of buttons
    public void changeFourButtonsColor(Button spotButton1, Button spotButton4, Button spotButton8, Button spotButton10)
    {
        spotButton1.setStyle("-fx-background-color:#4040bf;");
        spotButton4.setStyle("-fx-background-color:#4040bf;");
        spotButton8.setStyle("-fx-background-color:#4040bf;");
        spotButton10.setStyle("-fx-background-color:#4040bf;");
    }

    // Used to enable the grid of numbers
    public void enableGrid(GridPane gp)
    {
        for(Node node : gp.getChildren())
        {
            // enable the button
            node.setDisable(false);
        }
    }

    // Used to disable the grid of numbers
    public void disableGrid(GridPane gp){
        for (Node node : gp.getChildren()) {
            if (userNumSpots == 0 && userNumDraws == 0) {
                // disable the button
                node.setDisable(true);
            }
        }
    }

    // Used to change color of gridPane buttons
    public void newGridPaneColor(GridPane gp){
        for (Node node : gp.getChildren()) {
            node.setStyle("-fx-background-color:#4040bf;");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

    // This method will return a button with the specified string
    public ButtonType getCustomBtn(String buttonText)
    {
        ButtonType customButton = new ButtonType(buttonText, ButtonBar.ButtonData.OK_DONE);

        return customButton;
    }

    public void showDrawnNums()
    {
        int currentDraw = 1;

        ButtonType nextNumberBtn = getCustomBtn("Next Number");

        // Looping through ArrayList (draws) to access each HashSet (drawn numbers)
        for (int i = 0; i < allDraws.size(); i++)
        {
            HashSet <Integer> currentSet = allDraws.get(i);

            // Looping through HashSet at current index
            for (int currNumber : currentSet)
            {
                String drawnText = "Current Draw: " + currentDraw + "\n\nDrawn Number: " + currNumber;

                Alert tempAlert = new Alert(AlertType.INFORMATION, drawnText, nextNumberBtn);
                tempAlert.setTitle("Drawn Number");
                tempAlert.setHeaderText(null);
                tempAlert.setContentText(drawnText);
                tempAlert.initStyle(StageStyle.UTILITY);

                tempAlert.showAndWait();
            }

            // This string will calculate the results for the current draw
            String resultsText = resultStrings.get(i);

            // Showing results page here for current draw
            Alert resultsWindow = new Alert(AlertType.INFORMATION, resultsText);
            resultsWindow.initStyle(StageStyle.UTILITY);
            resultsWindow.setTitle("Draw Number " + currentDraw + " Results");
            resultsWindow.setHeaderText(null);

            resultsWindow.showAndWait();

            currentDraw++;
        }
    }
}