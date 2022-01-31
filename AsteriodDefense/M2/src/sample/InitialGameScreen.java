package sample;

import javafx.geometry.Pos;
import javafx.scene.shape.Rectangle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.layout.*;


public class InitialGameScreen {

    private int screenWidth = 839;
    private int screenHeight = 586;
    private int difficulty;

    public InitialGameScreen(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        difficulty = Controller.getDifficulty();
    }

    public Scene getInitialGameScreen() {
        Text opInfo = new Text();
        StackPane root = new StackPane();
        Pane text = new Pane();
        Pane healthBar = new Pane();

        opInfo.setFont(Font.font("rubik", FontWeight.BOLD, FontPosture.REGULAR, 15));
        opInfo.setFill(Color.WHITE);
        root.setAlignment(Pos.TOP_LEFT);
        root.getChildren().add(opInfo);
        Rectangle monumentHP = new Rectangle(200.0, 50.0, Color.BLUE);
        Rectangle monumentHPBG = new Rectangle(200.0, 50.0, Color.RED);
        text.setTranslateY(20);
        healthBar.setTranslateY(10);
        healthBar.setTranslateX(375);
        healthBar.getChildren().add(monumentHPBG);
        healthBar.getChildren().add(monumentHP);
        text.getChildren().add(opInfo);
        root.getChildren().add(text);
        root.getChildren().add(healthBar);
        DoubleProperty rHP = new SimpleDoubleProperty(100.0);
        monumentHP.widthProperty().bind(rHP);


        //MonumentHP.widthProperty().bind(mbind);

        /**
         * CONFIG NOTES:
         * Easy:
         * -Starting money: 2000
         * -Monuement Type: Self-Repairing Space Station, Heals at round start
         * -Monument HP: 2500
         * Normal:
         * -Starting money: 1500
         * -Monument Type: Space Station, Well Rounded
         * -Monument HP: 2000
         * Hard:
         * -Starting money 1000
         * -Monument Type: Dated Space Station, Has more health but progressively takes more damage
         * -Monument HP: 4000
         * */
        /**Difficulty: Easy Starting Money: 2000\s
        Monument Type: Self-Repairing\s
        Monument Health: 2500HP\
         Difficulty: Normals
         Starting Money: 1500s
         Monument Type: Defaults
         Monument Health: 2000HPs
         Difficulty: Hards
         Starting Money: 1000s
         Monument Type: Deterioratings
         Monument Health: 4000HPs*/
        if (difficulty == 1) {
            opInfo.setText("Difficulty: Easy\n"
                    + "Starting Money: $5000\n"
                    + "Monument Type: Self-Repairing\n"
                    + "Monument Health: 2500HP");
        }
        if (difficulty == 2) {
            opInfo.setText("Difficulty: Normal\n"
                + "Starting Money: $1500\n"
                + "Monument Type: Default\n"
                + "Monument Health: 2000HP");
        }
        if (difficulty == 3) {
            opInfo.setText("Difficulty: Hard\n"
                + "Starting Money: $1000\n"
                + "Monument Type: Deteriorating\n"
                + "Monument Health: 4000HP");

        }
        Image img = new Image("https://i.groupme.com/839x586.png.37789ee555ba4b14be1f584bca73d7e4");
        //https://i.groupme.com/839x586.png.37789ee555ba4b14be1f584bca73d7e4
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        root.setBackground(bGround);
        // primaryStage.setTitle("Initial Game Screen");
        // primaryStage.setScene(scene);
        // primaryStage.show();
        Scene scene = new Scene(root, screenWidth, screenHeight);
        return scene;
    }

    public int getDiff() {
        return difficulty;
    }
}