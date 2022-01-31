package sample;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.api.FxAssert.verifyThat;


public class SeraJUnit123 extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }

    @Test
    public void menuItemEasy() throws Exception {
        clickOn("#startGame");
        clickOn("#easy");
        clickOn("#towerMenuEasy");
        verifyThat("Gravity Accelerator: Speeds up Asteroids"
                + " and knocks them into eachother $2500", NodeMatchers.isNotNull());
        verifyThat("Crusher: Emits a wave of crushing"
                + " gravitational force in a circular area $2500", NodeMatchers.isNotNull());
        verifyThat("Infector: Infects Asteroids with rock eating "
                + "worms. Infected Asteroids deal more "
                + "damage to monument. $2500", NodeMatchers.isNotNull());
    }

    @Test
    public void menuItemMedium() throws Exception {
        clickOn("#startGame");
        clickOn("#medium");
        clickOn("#towerMenuMedium");
        verifyThat("Gravity Accelerator: Speeds up Asteroids"
                + " and knocks them into eachother $750", NodeMatchers.isNotNull());
        verifyThat("Crusher: Emits a wave of crushing"
                + " gravitational force in a circular area $750", NodeMatchers.isNotNull());
        verifyThat("Infector: Infects Asteroids with rock eating "
                + "worms. Infected Asteroids deal more "
                + "damage to monument. $750", NodeMatchers.isNotNull());
    }

    @Test
    public void menuItemHard() throws Exception {
        clickOn("#startGame");
        clickOn("#hard");
        clickOn("#towerMenuHard");
        verifyThat("Gravity Accelerator: Speeds up Asteroids"
                + " and knocks them into eachother $500", NodeMatchers.isNotNull());
        verifyThat("Crusher: Emits a wave of crushing"
                + " gravitational force in a circular area $500", NodeMatchers.isNotNull());
        verifyThat("Infector: Infects Asteroids with rock eating "
                + "worms. Infected Asteroids deal more "
                + "damage to monument. $500", NodeMatchers.isNotNull());
    }
}