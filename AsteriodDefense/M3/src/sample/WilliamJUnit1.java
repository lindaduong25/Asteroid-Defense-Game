package sample;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.api.FxAssert.verifyThat;

public class WilliamJUnit1 extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }

    @Test
    public void moneyDoesNotGoBelowZero() throws Exception {
        clickOn("#startGame");
        clickOn("#medium");
        clickOn("#towerMenuMedium");
        clickOn("#menuItemMedium1");
        clickOn("#towerView1");
        clickOn("#towerMenuMedium");
        clickOn("#menuItemMedium1");
        clickOn("#towerView2");
        clickOn("#towerMenuMedium");
        clickOn("#menuItemMedium1");
        clickOn("#healthbar");

        verifyThat("money: 0", NodeMatchers.isNotNull());
    }
}