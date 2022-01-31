package sample;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class AvaneeshJUnit extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }
    @Test
    public void testEasyExists() {
        clickOn("Start Game");
        clickOn("Easy");
        verifyThat("Difficulty: Easy\n"
                + "Starting Money: $5000\n"
                + "Monument Type: Self-Repairing\n"
                + "Monument Health: 2500HP", NodeMatchers.isNotNull());
    }
    @Test
    public void testNormalExists() {
        clickOn("Start Game");
        clickOn("Medium");
        verifyThat("Difficulty: Normal\n"
                + "Starting Money: $1500\n"
                + "Monument Type: Default\n"
                + "Monument Health: 2000HP", NodeMatchers.isNotNull());
    }
    @Test
    public void testHardExists() {
        clickOn("Start Game");
        clickOn("Hard");
        verifyThat("Difficulty: Hard\n"
                + "Starting Money: $1000\n"
                + "Monument Type: Deteriorating\n"
                + "Monument Health: 4000HP", NodeMatchers.isNotNull());
    }
}
