package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller extends Application implements Initializable {

    private Parent root;

    @FXML
    private Pane rootPane;

    @FXML
    private Button startGame;

    @FXML
    private Button easy;

    @FXML
    private Button hard;

    @FXML
    private Button medium;

    @FXML
    private MenuButton towerMenuEasy;

    @FXML
    private MenuButton towerMenuMedium;

    @FXML
    private MenuButton towerMenuHard;

    @FXML
    private MenuItem menuItemEasy1;

    @FXML
    private MenuItem menuItemEasy2;

    @FXML
    private MenuItem menuItemEasy3;

    @FXML
    private MenuItem menuItemMedium1;

    @FXML
    private MenuItem menuItemMedium2;

    @FXML
    private MenuItem menuItemMedium3;

    @FXML
    private MenuItem menuItemHard1;

    @FXML
    private MenuItem menuItemHard2;

    @FXML
    private MenuItem menuItemHard3;

    @FXML
    private TextField username;

    @FXML
    private ImageView towerView1;

    @FXML
    private ImageView towerView2;

    @FXML
    private Button testButton;

    @FXML
    private ImageView background;

    @FXML
    private ImageView path1;

    @FXML
    private ImageView path2;

    @FXML
    private ImageView path3;

    @FXML
    private ImageView path4;

    @FXML
    private ImageView path5;

    @FXML
    private ImageView path6;

    @FXML
    private ImageView path7;

    @FXML
    private ImageView path8;

    @FXML
    private ImageView path9;

    @FXML
    private ImageView path10;

    @FXML
    private ImageView path11;

    @FXML
    private ImageView path12;

    @FXML
    private TextArea moneyDisplay;

    private boolean start = true;

    private Stage mainWindow = new Stage();

    private boolean towerSelected = false;
    private boolean towerSelected1 = false;
    private boolean towerSelected2 = false;


    private double mouseX;
    private double mouseY;

    private int money = 0;
    private int difficulty;

    private int towerNum = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        rootPane = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        Scene scene = new Scene(rootPane);
        mainWindow = primaryStage;
        mainWindow.setTitle("Welcome Screen");
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    @FXML
    void goToConfig(ActionEvent event) throws Exception {
        mainWindow = (Stage) startGame.getScene().getWindow();
        rootPane = FXMLLoader.load(getClass().getResource("ConfigScreen.fxml"));
        Scene scene = new Scene(rootPane);
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    @FXML
    void goToInitGameScreenEasy() throws Exception {
        if (!usernameValid(username)) {
            return;
        }
        mainWindow = (Stage) easy.getScene().getWindow();
        rootPane = FXMLLoader.load(getClass().getResource("initGameScreenEasy.fxml"));
        Scene scene = new Scene(rootPane);
        mainWindow.setScene(scene);
    }

    @FXML
    void goToInitGameScreenMedium() throws Exception {
        if (!usernameValid(username)) {
            return;
        }
        mainWindow = (Stage) medium.getScene().getWindow();
        rootPane = FXMLLoader.load(getClass().getResource("initGameScreenMedium.fxml"));
        Scene scene = new Scene(rootPane);
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    @FXML
    void goToInitGameScreenHard() throws Exception {
        if (!usernameValid(username)) {
            return;
        }
        mainWindow = (Stage) hard.getScene().getWindow();
        rootPane = FXMLLoader.load(getClass().getResource("initGameScreenHard.fxml"));
        Scene scene = new Scene(rootPane);
        mainWindow.setScene(scene);
        mainWindow.show();
    }


    @FXML
    public void selectTower1() {
        if (!enoughMoney()) {
            return;
        }
        towerSelected = true;
    }
    @FXML
    public void selectTower2() {
        if (!enoughMoney()) {
            return;
        }
        towerSelected1 = true;
    }
    @FXML
    public void selectTower3() {
        if (!enoughMoney()) {
            return;
        }
        towerSelected2 = true;
    }

    @FXML
    public void placeTower(MouseEvent mouseEvent) {
        if (towerSelected || towerSelected1 || towerSelected2) {
            mouseX = mouseEvent.getSceneX();
            mouseY = mouseEvent.getSceneY();
            initialize(null, null);
        }
    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (moneyDisplay == null) {
            return;
        }
        if (towerMenuEasy != null && start) {
            start = false;
            difficulty = 0;
            changeMoney(5000);
            return;
        }
        if (towerMenuMedium != null && start) {
            start = false;
            difficulty = 1;
            changeMoney(1500);
            return;
        }
        if (towerMenuHard != null && start) {
            start = false;
            difficulty = 2;
            changeMoney(1000);
            return;
        }

        if (!validCoord(mouseX, mouseY)) {
            return;
        }

        System.out.println("running");

        ImageView towerView = towerView1;
        towerView.setVisible(true);

        towerNum = towerNum + 1;
        System.out.println(towerNum);

        Image image = new Image("https://i.groupme.com/64x64.gif.74e091c7b74847769a9780675db31ce9");

        if (towerView1.getImage() != null) {
            System.out.println("ran2");
            towerView = towerView2;
        }

        if (towerSelected) {
            image = new Image("https://i.groupme.com/800x800.gif.6286be0801934dc7bc62f81db12bbfd7.large");
            towerSelected = false;
        }

        if (towerSelected1) {
            image = new Image("https://i.groupme.com/224x224.gif.32f5cd8a8a2043218e85072c486ac688.large");
            towerSelected1 = false;
        }

        if (towerSelected2) {
            image = new Image("https://i.groupme.com/800x800.gif.84823063173c4b1c89d9a23838eba7f4");
            towerSelected2 = false;
        }

        if (difficulty == 0) {
            changeMoney(-2500);
        }

        if (difficulty == 1) {
            changeMoney(-750);
        }

        if (difficulty == 2) {
            changeMoney(-500);
        }

        towerView.setImage(image);
        System.out.println(towerView.imageProperty());
        towerView.setFitHeight(100);
        towerView.setFitWidth(100);
        towerView.setLayoutX(mouseX - 50);
        System.out.println(mouseX);
        towerView.setLayoutY(mouseY - 50);
        System.out.println(mouseY);
    }

    public int getTowerNum() {
        return towerNum;
    }

    public void changeMoney(int change) {
        System.out.println(money);
        money += change;
        System.out.println(money);
        moneyDisplay.setText("money: " + money);
    }

    public boolean enoughMoney() {
        if (difficulty == 0 && money >= 2500) {
            return true;
        }
        if (difficulty == 1 && money >= 750) {
            return true;
        }
        if (difficulty == 2 && money >= 500) {
            return true;
        }
        System.out.println("no money");
        return false;
    }

    public boolean usernameValid(TextField text) {
        CharSequence chars = text.getCharacters();
        if (chars.length() == 0) {
            return false;
        }
        for (int i = 0; i < chars.length(); i++) {
            if (chars.charAt(i) != ' ') {
                return true;
            }
        }
        return false;
    }

    public boolean validCoord(double x, double y) {
        if ((x > path1.getLayoutX() && x < path1.getLayoutX() + path1.getFitWidth())
                && (y > path1.getLayoutY() && y < path1.getLayoutY() + path1.getFitHeight())) {
            System.out.println("returned false");
            return false;
        }
        if ((x > path2.getLayoutX() && x < path2.getLayoutX() + path2.getFitWidth())
                && (y > path2.getLayoutY() && y < path2.getLayoutY() + path2.getFitHeight())) {
            System.out.println("returned false");
            return false;
        }
        if ((x > path3.getLayoutX() && x < path3.getLayoutX() + path3.getFitWidth())
                && (y > path3.getLayoutY() && y < path3.getLayoutY() + path3.getFitHeight())) {
            System.out.println("returned false");
            return false;
        }
        if ((x > path4.getLayoutX() && x < path4.getLayoutX() + path4.getFitWidth())
                && (y > path4.getLayoutY() && y < path4.getLayoutY() + path4.getFitHeight())) {
            System.out.println("returned false");
            return false;
        }
        if ((x > path5.getLayoutX() && x < path5.getLayoutX() + path5.getFitWidth())
                && (y > path5.getLayoutY() && y < path5.getLayoutY() + path5.getFitHeight())) {
            System.out.println("returned false");
            return false;
        }
        if ((x > path6.getLayoutX() && x < path6.getLayoutX() + path6.getFitWidth())
                && (y > path6.getLayoutY() && y < path6.getLayoutY() + path6.getFitHeight())) {
            System.out.println("returned false");
            return false;
        }
        if ((x > path7.getLayoutX() && x < path7.getLayoutX() + path7.getFitWidth())
                && (y > path7.getLayoutY() && y < path7.getLayoutY() + path7.getFitHeight())) {
            System.out.println("returned false");
            return false;
        }
        if ((x > path8.getLayoutX() && x < path8.getLayoutX() + path8.getFitWidth())
                && (y > path8.getLayoutY() && y < path8.getLayoutY() + path8.getFitHeight())) {
            System.out.println("returned false");
            return false;
        }
        if ((x > path9.getLayoutX() && x < path9.getLayoutX() + path9.getFitWidth())
                && (y > path9.getLayoutY() && y < path9.getLayoutY() + path9.getFitHeight())) {
            System.out.println("returned false");
            return false;
        }
        if ((x > path10.getLayoutX() && x < path10.getLayoutX() + path10.getFitWidth())
                && (y > path10.getLayoutY() && y < path10.getLayoutY() + path10.getFitHeight())) {
            System.out.println("returned false");
            return false;
        }
        if ((x > path11.getLayoutX() && x < path11.getLayoutX() + path11.getFitWidth())
                && (y > path11.getLayoutY() && y < path11.getLayoutY() + path11.getFitHeight())) {
            System.out.println("returned false");
            return false;
        }
        if ((x > path12.getLayoutX() && x < path12.getLayoutX() + path12.getFitWidth())
                && (y > path12.getLayoutY() && y < path12.getLayoutY() + path12.getFitHeight())) {
            System.out.println("returned false");
            return false;
        }
        return true;
    }
}