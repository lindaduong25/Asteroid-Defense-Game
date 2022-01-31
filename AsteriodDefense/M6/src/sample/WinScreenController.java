package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class WinScreenController {

    @FXML
    private Pane winScreen;

    @FXML
    private Label moneyFinalDisplay;

    @FXML
    private Label healthFinalDisplay;

    @FXML
    private Label roundsFinalDisplay;

    private boolean exited = false;

    @FXML
    public void goBackToWelcome() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeScreen.fxml"));
        Parent root = loader.load();
        Stage primaryStage = (Stage) winScreen.getScene().getWindow();
        primaryStage.setTitle("WelcomeScreen");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    public void closeWindow() {
        exited = true;
        Stage primaryStage = (Stage) winScreen.getScene().getWindow();
        primaryStage.close();
    }

    public Pane getWinScreen() {
        return winScreen;
    }

    public boolean isExited() {
        return exited;
    }

    public void setLabelText(int money, int health, int rounds) {
        String moneyText = "Money Earned:  $" + money;
        String healthText = "Final Health: " + health;
        String roundText = "Rounds Lasted: " + rounds;
        moneyFinalDisplay.textProperty().setValue(moneyText);
        healthFinalDisplay.textProperty().setValue(healthText);
        roundsFinalDisplay.textProperty().setValue(roundText);
    }

}
