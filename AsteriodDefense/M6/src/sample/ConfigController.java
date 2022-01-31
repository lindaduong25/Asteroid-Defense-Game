package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfigController {

    @FXML
    private TextField username;

    private int difficulty = -1;

    @FXML
    void gameScreenEasy(ActionEvent event) throws Exception {
        difficulty = 0;
        goToGameScreen();
    }

    @FXML
    void gameScreenMedium(ActionEvent event) throws Exception {
        difficulty = 1;
        goToGameScreen();
    }

    @FXML
    void gameScreenHard(ActionEvent event) throws Exception {
        difficulty = 2;
        goToGameScreen();
    }

    @FXML
    void goToGameScreen() throws Exception {
        if (!usernameValid(username)) {
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
        Parent root = loader.load();
        Main.setGameController(loader.getController());
        Stage primaryStage = (Stage) username.getScene().getWindow();
        primaryStage.setTitle("Game Screen");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public boolean usernameValid(TextField text) {
        CharSequence chars = text.getCharacters();
        if (chars.length() == 0) {
            return false;
        }
        for (int i = 0; i < chars.length(); i++) {
            if (chars.charAt(i) != ' ') {
                return true;
            }
        }
        return false;
    }


    public int getDifficulty() {
        return difficulty;
    }
}
