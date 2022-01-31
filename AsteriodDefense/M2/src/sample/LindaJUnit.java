package sample;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.api.FxAssert.verifyThat;

public class LindaJUnit extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }
    @Test
    public void testEasyExists() {
        clickOn("Start Game");
        verifyThat("Easy", NodeMatchers.isNotNull());
    }
    @Test
    public void testNormalExists() {
        clickOn("Start Game");
        verifyThat("Medium", NodeMatchers.isNotNull());
    }
    @Test
    public void testHardExists() {
        clickOn("Start Game");
        verifyThat("Hard", NodeMatchers.isNotNull());
    }
}