package sample;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;

public class MarcusJUnit extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }
    @Test
    public void healthBarTestInc() {
        double multiplier = 1.0;
        DoubleProperty rHP = new SimpleDoubleProperty(100.0 * multiplier);
        Rectangle monumentHP = new Rectangle(200.0, 50.0, Color.BLUE);
        monumentHP.widthProperty().bind(rHP.add(1));
        Assertions.assertEquals(101, monumentHP.getWidth());
    }
    @Test
    public void healthBarTestDec() {
        double multiplier = 1.0;
        DoubleProperty rHP = new SimpleDoubleProperty(100.0 * multiplier);
        Rectangle monumentHP = new Rectangle(200.0, 50.0, Color.BLUE);
        monumentHP.widthProperty().bind(rHP.add(-1));
        Assertions.assertEquals(99, monumentHP.getWidth());
    }
}
