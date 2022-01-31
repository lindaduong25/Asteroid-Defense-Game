package sample;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.control.Label;

public class Enemy extends ImageView {

    private IntegerProperty health;
    private DoubleProperty xCoord;
    private DoubleProperty yCoord;
    private PathTransition pathTransition;
    private Label enemyHealthDisplay = new Label();

    public Enemy(IntegerProperty health, DoubleProperty xCoord, DoubleProperty yCoord, Duration time) {
        this.setImage(new Image("https://i.groupme.com/1920x1911.png.87ad28a0524b47109bab936d39620bb7"));
        this.setFitHeight(40);
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.setFitWidth(40);
        this.setLayoutX(1);
        this.setLayoutY(-80);
        this.health = health;
        pathTransition = new PathTransition();
        pathTransition.setPath(Main.getGameController().getPath());
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.setDuration(time);
        enemyHealthDisplay.setText(String.valueOf(health.intValue()));
    }

    public Enemy() {
        this(new SimpleIntegerProperty(10), new SimpleDoubleProperty(0.0), new SimpleDoubleProperty(0.0), Duration.seconds(30));
    }

    public void updateCoords(double x, double y) {
        this.setLayoutX(x - 40);
        this.setLayoutY(y - 150);
    }

    public void setHealth(int health) {
        this.health.setValue(health);
    }

    public int getHealth() {
        return health.getValue();
    }

    public IntegerProperty getHealthProp() { return health; };

    public void decreaseHealth(int damage) {
        health.setValue(health.getValue() - damage);
        if (health.intValue() <= 0) {
            health.setValue(0);
        }
        //testing
        System.out.println("Health is now " + health.intValue());
    }

    public PathTransition getPathTransition() {
        return pathTransition;
    }

    public void setPathTransition(PathTransition pathTransition) {
        this.pathTransition = pathTransition;
    }

    public Label getEnemyHealthDisplay() {
        return enemyHealthDisplay;
    }

}
