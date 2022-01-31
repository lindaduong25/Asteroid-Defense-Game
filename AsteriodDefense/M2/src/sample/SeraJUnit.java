package sample;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class SeraJUnit extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }

    @Test
    public void testNameBad() {
        clickOn("Start Game");
        write(" ");
        clickOn("Easy");
        verifyThat("Easy", NodeMatchers.isNotNull());
    }

    @Test
    public void testNameGood() {
        clickOn("Start Game");
        write(" ");
        write("A");
        clickOn("Easy");
        verifyThat("Difficulty: Easy\n"
                + "Starting Money: $5000\n"
                + "Monument Type: Self-Repairing\n"
                + "Monument Health: 2500HP", NodeMatchers.isNotNull());
    }
}
