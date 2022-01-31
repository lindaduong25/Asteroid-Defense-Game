package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;


public class Main extends Application {

    private static WelcomeController welcomeController;

    private static ConfigController configController;

    private static GameController gameController;

    private static GameOverController gameOverController;

    private static WinScreenController winScreenController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeScreen.fxml"));
        Parent root = loader.load();
        Main.setWelcomeController(loader.getController());
        primaryStage.setTitle("Welcome Screen");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static WelcomeController getWelcomeController() {
        return welcomeController;
    }

    public static void setWelcomeController(WelcomeController welcomeController) {
        Main.welcomeController = welcomeController;
    }

    public static ConfigController getConfigController() {
        return configController;
    }

    public static void setConfigController(ConfigController configController) {
        Main.configController = configController;
    }

    public static GameController getGameController() {
        return gameController;
    }

    public static void setGameController(GameController gameController) {
        Main.gameController = gameController;
    }

    public static GameOverController getGameOverController() {
        return gameOverController;
    }

    public static void setGameOverController(GameOverController gameOverController) {
        Main.gameOverController = gameOverController;
    }
    public static WinScreenController getWinScreenController() {
        return winScreenController;
    }

    public static void setWinScreenController(WinScreenController winScreenController) {
        Main.winScreenController = winScreenController;
    }

}