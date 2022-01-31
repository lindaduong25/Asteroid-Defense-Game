package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;

public class M6JUnit extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeScreen.fxml"));
        Parent root = loader.load();
        Main.setWelcomeController(loader.getController());
        primaryStage.setTitle("Welcome Screen");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    @Test
    public void towerUpgradeButtonExists() throws Exception {
        clickOn("#startGame");
        clickOn("#easy");
        verifyThat("#playButton", NodeMatchers.isVisible());
    }
    @Test
    public void noEnemiesAtStart() throws Exception {
        clickOn("#startGame");
        clickOn("#easy");
        assertEquals(Main.getGameController().getAsteroids(), false);

    }
    @Test
    public void noCombatButtonAvailableOnRoundStart() throws Exception {
        clickOn("#startGame");
        clickOn("#easy");
        clickOn("#playButton");
        verifyThat("#playButton", NodeMatchers.isDisabled());
    }

    @Test
    public void noTowersGameOver() throws Exception {
        clickOn("#startGame");
        clickOn("#easy");
        Main.getGameController().displayGameOver();
        clickOn("#towerMenu");
        assertEquals(Main.getGameController().getTowers(), false);
    }
    @Test
    public void gameOverScreen() throws Exception {
        clickOn("#startGame");
        clickOn("#easy");
        clickOn("#playButton");
        Main.getGameController().setHealth(0);
        verifyThat("#restart", NodeMatchers.isVisible());
    }
    @Test
    public void restartGame() throws Exception {
        clickOn("#startGame");
        clickOn("#easy");
        Main.getGameController().displayGameOver();
        clickOn("#restart");
        verifyThat("#startGame", NodeMatchers.isNotNull());
    }
    @Test
    public void exitGame() throws Exception {
        clickOn("#startGame");
        clickOn("#easy");
        Main.getGameController().displayGameOver();
        clickOn("#exit");
        assertEquals(Main.getGameOverController().isExited(), true);
    }
}
