package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomeController {

    @FXML
    private Button startGame;

    @FXML
    void goToConfig(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfigScreen.fxml"));
        Parent root = loader.load();
        Main.setConfigController(loader.getController());
        Stage primaryStage = (Stage) startGame.getScene().getWindow();
        primaryStage.setTitle("Config Screen");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
