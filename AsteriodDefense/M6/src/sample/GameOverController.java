package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOverController {

    @FXML
    private Pane gameOver;

    private boolean exited = false;

    @FXML
    public void goBackToWelcome() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeScreen.fxml"));
        Parent root = loader.load();
        Stage primaryStage = (Stage) gameOver.getScene().getWindow();
        primaryStage.setTitle("WelcomeScreen");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    public void closeWindow() {
        exited = true;
        Stage primaryStage = (Stage) gameOver.getScene().getWindow();
        primaryStage.close();
    }

    public Pane getGameOver() {
        return gameOver;
    }

    public boolean isExited() {
        return exited;
    }
}
