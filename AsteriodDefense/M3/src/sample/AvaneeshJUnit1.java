package sample;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class AvaneeshJUnit1 extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }

    // towerMenuEasy exists on initGameScreenEasy
    @Test
    public void towerPlaces() throws Exception {
        clickOn("#startGame");
        clickOn("#easy");
        clickOn("#towerMenuEasy");
        clickOn("#menuItemEasy1");
        clickOn("#towerView2");

        verifyThat("#towerView1", NodeMatchers.isVisible());
    }
}