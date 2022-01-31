package sample;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.api.FxAssert.verifyThat;

public class M4JUnit2 extends ApplicationTest {

    private int health = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }

    // testing if exit button closes the window
    @Test
    public void restartGame() throws Exception {
        clickOn("#startGame");
        clickOn("#easy");
        health = 0;
        clickOn("#exit");
        verifyThat("#startGame", NodeMatchers.isNull());
    }
}
