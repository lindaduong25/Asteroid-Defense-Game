package sample;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class Controller extends Application {
    private Stage mainWindow = new Stage();
    private final int width = 839;
    private final int height = 586;
    private static int difficulty = 0;

    private ConfigScreen secondScreen;
    private InitialGameScreen gameScreen;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainWindow = primaryStage;
        mainWindow.setTitle("Welcome Screen");
        goToConfig();
    }

    public void goToConfig() {
        WelcomeScreen screen = new WelcomeScreen(width, height);
        Button startGame = screen.getButton();
        startGame.setOnAction(e -> goToConfigScreen());
        Scene welcomeScene = screen.getFirstScene();
        mainWindow.setScene(welcomeScene);
        mainWindow.show();
    }

    public void goToConfigScreen() {
        secondScreen = new ConfigScreen(width, height);
        HBox settings = secondScreen.getSettings();
        for (Node difficultyButton : settings.getChildren()) {
            difficultyButton.setOnMouseClicked(e -> {
                switch (difficultyButton.getId()) {
                case "Easy":
                    Controller.difficulty = 1;
                    System.out.println(Controller.difficulty);
                    break;
                case "Medium":
                    Controller.difficulty = 2;
                    System.out.println(Controller.difficulty);
                    break;
                case "Hard":
                    Controller.difficulty = 3;
                    System.out.println(Controller.difficulty);
                    break;
                default:
                }
                if (secondScreen.canChange()) {
                    goToGameScreen();
                }
            });
        }
        Scene config = secondScreen.getConfigScene();
        mainWindow.setScene(config);
        mainWindow.show();
    }

    public void goToGameScreen() {
        gameScreen = new InitialGameScreen(width, height);
        Scene game = gameScreen.getInitialGameScreen();
        mainWindow.setScene(game);
        mainWindow.show();
    }

    public static int getDifficulty() {
        return difficulty;
    }
    public static void main(String[] args) {
        launch(args);
    }

    public static void setDifficulty(int newDiff) {
        difficulty = newDiff;
    }

    public ConfigScreen getSecondScreen() {
        return secondScreen;
    }

    public InitialGameScreen getGameScreen() {
        return gameScreen;
    }
}