package sample;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class WilliamJUnit23 extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }

    @Test
    public void towerPriceChangeOnDiffMedium() throws Exception {
        clickOn("#startGame");
        clickOn("#medium");
        clickOn("#towerMenuMedium");
        clickOn("#menuItemMedium1");
        clickOn("#towerView2");

        verifyThat("money: 750", NodeMatchers.isNotNull());
    }

    @Test
    public void towerPriceChangeOnDiffHard() throws Exception {
        clickOn("#startGame");
        clickOn("#hard");
        clickOn("#towerMenuHard");
        clickOn("#menuItemHard1");
        clickOn("#towerView2");

        verifyThat("money: 500", NodeMatchers.isNotNull());
    }
}