package sample;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.api.FxAssert.verifyThat;


public class LindaJUnit123 extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }

    // towerMenuEasy exists on initGameScreenEasy
    @Test
    public void towerMenuEasyExists() throws Exception {
        clickOn("#startGame");
        clickOn("#easy");
        clickOn("#towerMenuEasy");
        verifyThat("#towerMenuEasy", NodeMatchers.isNotNull());
    }

    @Test
    public void towerMenuMediumExists() throws Exception {
        clickOn("#startGame");
        clickOn("#medium");
        clickOn("#towerMenuMedium");
        verifyThat("#towerMenuMedium", NodeMatchers.isNotNull());
    }

    @Test
    public void towerMenuHardExists() throws Exception {
        clickOn("#startGame");
        clickOn("#hard");
        clickOn("#towerMenuHard");
        verifyThat("#towerMenuHard", NodeMatchers.isNotNull());
    }
}