package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

public class WelcomeScreen {
    private int screenWidth = 839;
    private int screenHeight = 586;
    private Button startGame;

    public WelcomeScreen(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        startGame = new Button("Start Game");
    }

    public Scene getFirstScene() {
        BorderPane root = new BorderPane();
        VBox titleBox = new VBox();
        VBox container = new VBox();
        Scene scene = new Scene(root, screenWidth, screenHeight);
        Text titleLabel = new Text("Asteroid Defense");
        titleLabel.setFont(Font.font("rubik", FontWeight.BOLD, FontPosture.REGULAR, 50));
        titleLabel.setFill(Color.WHITE);
        titleLabel.setId("titleLabel");
        startGame.setId("startGame");
        titleBox.setId("titleBox");
        container.setId("container");
        container.setAlignment(Pos.CENTER);
        titleBox.setAlignment(Pos.TOP_CENTER);
        root.setCenter(container);
        root.setTop(titleBox);
        container.getChildren().add(startGame);
        titleBox.getChildren().add(titleLabel);
        Image img = new Image("https://i.groupme.com/1280x1024.png.44aa71dc319043169c4f51a4f3d3a8cd");
        //https://i.groupme.com/839x586.png.37789ee555ba4b14be1f584bca73d7e4
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        root.setBackground(bGround);

        return scene;
    }

    public Button getButton() {
        return startGame;
    }
}