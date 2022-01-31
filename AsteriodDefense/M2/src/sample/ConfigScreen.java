package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;


public class ConfigScreen {

    private int screenWidth;
    private int screenHeight;
    private HBox settings;
    private VBox levelBox;
    private StackPane root;
    private Button easy;
    private Scene scene;
    private TextField name;

    public ConfigScreen(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        root = new StackPane();
        levelBox = new VBox();
        settings = new HBox();
        easy = new Button("Easy");
        Button medium = new Button("Medium");
        Button hard = new Button("Hard");
        name = new TextField("Enter Name");
        //name.setOnAction(e -> noNullValues());
        VBox text1 = new VBox();
        text1.getChildren().add(name);
        text1.setAlignment(Pos.CENTER);
        easy.setId("Easy");
        medium.setId("Medium");
        hard.setId("Hard");
        levelBox.setId("levelBox");
        levelBox.setSpacing(30);
        settings.getChildren().addAll(easy, medium, hard);
        settings.setAlignment(Pos.CENTER);
        settings.setSpacing(30);
        levelBox.getChildren().addAll(text1, settings);
        levelBox.setAlignment(Pos.CENTER);
        root.getChildren().addAll(levelBox);
        scene = new Scene(root, screenWidth, screenHeight);
        System.out.println(settings.getChildren());

        //https://i.groupme.com/1280x1024.jpeg.b3beb1f8cab24177b4adf90aee9b2423

        Image img = new Image("https://i.groupme.com/1280x1024.jpeg.b3beb1f8cab24177b4adf90aee9b2423");
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        root.setBackground(bGround);

    }

    public Scene getConfigScene() {
        return scene;
    }

    public HBox getSettings() {
        return settings;
    }
    public Button easy() {
        return easy;
    }

    public String getNameString() {
        return name.getCharacters().toString();
    }

    public void setName(String n) {
        name.setText(n);
    }

    public boolean canChange() {
        return !(isBlank(getNameString()) || getNameString() == null);
    }
    public boolean isBlank(String name) {
        if (name == "") {
            return true;
        }
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }
}