package sample;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class GameController implements Initializable {

    @FXML
    private TowersController towersController;

    @FXML
    private ImageView monument;
    @FXML
    private Pane winScreen;
    @FXML
    private WinScreenController winScreenController;

    @FXML
    private EnemiesController enemiesController;

    @FXML
    private Pane gameOver;

    @FXML
    private GameOverController gameOverController;

    @FXML
    private MenuItem menuItem1;

    @FXML
    private MenuItem menuItem2;

    @FXML
    private MenuItem menuItem3;

    @FXML
    private TextArea moneyDisplay;

    @FXML
    private TextArea healthDisplay;

    @FXML
    private Pane pathPane;

    @FXML
    private Button playButton;

    @FXML
    private Button pauseButton;

    private int difficulty;

    private int price;

    private int money;

    private int health;
    private int ogHealth;

    private String ogMoneyText;

    private String ogHealthText;

    private ObservableList<Node> paths;

    private Path path;

    private int roundCounter;

    public GameController() {
    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initPriceAndMoney();
        Main.setGameOverController(gameOverController);
        health = 100;
        ogHealth = health;
        menuItem1.setText(menuItem1.getText() + price);
        menuItem2.setText(menuItem2.getText() + price);
        menuItem3.setText(menuItem3.getText() + price);
        ogMoneyText = moneyDisplay.getText();
        moneyDisplay.setText(moneyDisplay.getText() + money);
        ogHealthText = healthDisplay.getText();
        healthDisplay.setText(healthDisplay.getText() + health);
        paths = pathPane.getChildren();
        for (Node path : paths) {
            if (!(path instanceof ImageView)) {
                paths.remove(path);
            }
        }
        roundCounter = 0;
        path = new Path();
        MoveTo spawn = new MoveTo(0f, 230f);
        HLineTo line1 = new HLineTo(234f);
        VLineTo line2 = new VLineTo(124f);
        HLineTo line3 = new HLineTo(445f);
        VLineTo line4 = new VLineTo(360f);
        HLineTo line5 = new HLineTo(242f);
        VLineTo line6 = new VLineTo(540f);
        HLineTo line7 = new HLineTo(729f);
        VLineTo line8 = new VLineTo(371f);
        HLineTo line9 = new HLineTo(612f);
        VLineTo line10 = new VLineTo(205f);
        HLineTo line11 = new HLineTo(745f);
        VLineTo line12 = new VLineTo(96f);
        path.getElements().addAll(spawn, line1, line2, line3, line4, line5, line6, line7, line8,
                line9, line10, line11, line12);
    }

    public Path getPath() {
        return path;
    }

    @FXML
    public void getClick(MouseEvent mouseEvent) {
        System.out.println(mouseEvent.getX() + " " + mouseEvent.getY());
    }
    @FXML
    public void startRound() throws InterruptedException {
        roundCounter++;
        System.out.println("this function ran");
        enemiesController.spawnEnemies(roundCounter);
    }

    @FXML
    public boolean getTowers() {
        if (towersController.getTowers().size() == 0) {
            return false;
        }
        Node tower = towersController.getTowers().get(0);
        return tower.isVisible();

    }
    @FXML
    public boolean getAsteroids() {
        if (enemiesController.getAsteroids().getChildren().size() == 0) {
            return false;
        }
        Node enemy = enemiesController.getAsteroids().getChildren().get(0);
        return enemy.isVisible();

    }
    @FXML
    public int asteroidNum() {
        return enemiesController.getAsteroids().getChildren().size();
    }

    @FXML
    public void selectTower1() {
        towersController.selectedTower(0);
    }

    @FXML
    public void selectTower2() {
        towersController.selectedTower(1);
    }

    @FXML
    public void selectTower3() {
        towersController.selectedTower(2);
    }

    @FXML
    public boolean upgradeTower1() {
        if (money - 750 < 0) {
            return false;
        }
        money -= 750;
        towersController.updateRanges();
        moneyDisplay.setText(ogMoneyText + money);
        return true;
    }

    @FXML
    public boolean upgradeTower2() {
        if (money - 750 < 0) {
            return false;
        }
        money -= 750;
        towersController.updateDamage();
        moneyDisplay.setText(ogMoneyText + money);
        return true;
    }

    public void KillFinalBoss() {
        towersController.endTowersShootTask();
        Main.getWinScreenController().setLabelText(this.money, this.health, this.roundCounter);
        displayWinScreen();
    }
    public void displayWinScreen() {

        winScreen.setVisible(true); }


    private void initPriceAndMoney() {
        difficulty = Main.getConfigController().getDifficulty();
        switch (difficulty) {
            case 0:
                price = 2500;
                money = 5000;
                break;
            case 1:
                price = 750;
                money = 1500;
                break;
            case 2:
                price = 500;
                money = 1000;
                break;
            default:
                price = 0;
                money = 0;
        }
    }

    public void updateMoneyFromEnemyDamage(int amount) {
        // amount = enemy's health - damage
        money += amount + 1;
        moneyDisplay.setText("$" + money);
        System.out.println("Money " + money);
    }

    public boolean purchaseTower() {
        if (difficulty == 0) {
            if (money - 2500 < 0) {
                return false;
            }
            money -= 2500;
        } else if (difficulty == 1) {
            if (money - 750 < 0) {
                return false;
            }
            money -= 750;
        } else if (difficulty == 2) {
            if (money - 500 < 0) {
                return false;
            }
            money -= 500;
        }
        moneyDisplay.setText(ogMoneyText + money);
        return true;
    }


    public ObservableList<Node> getPaths() {
        return paths;
    }

    public void displayGameOver() {
        gameOver.setVisible(true);
    }

    public Button getPlayButton() {
        return playButton;
    }

    public int getHealth() {
        return health;
    }

    public int getOGHealth() {
        return ogHealth;
    }

    public void setHealth(int health) {
        System.out.println("Health decreased");
        this.health = Math.max(0, health);
        healthDisplay.setText(ogHealthText + this.health);
        if (this.health <= 0) {
            towersController.endTowersShootTask();
            displayGameOver();
        }
    }

    public ImageView getMonument() {
        return monument;
    }

    public EnemiesController getEnemiesController() {
        return enemiesController;
    }

    public TowersController getTowersController() {
        return towersController;
    }
}
