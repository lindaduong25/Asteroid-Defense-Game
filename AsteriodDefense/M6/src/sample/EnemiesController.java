package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EnemiesController implements Initializable {

    @FXML
    private Pane enemies;

    @FXML
    private ArrayList<Enemy> enemyList;

    private Thread enemyWaveThread;

    private int difficulty;

    private boolean start = true;

    private boolean newRound = false;

    private boolean healthDecreased = false;

    private ObservableList<Node> enemiesList;

    private Task<Integer> enemyWaveTask;

    private boolean allSpawned;


    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (start) {
            difficulty = Main.getConfigController().getDifficulty();
            start = false;
        }
        if (newRound) {
            allSpawned = false;
            enemyWaveTask = createEnemyWaveTask();
            enemyWaveThread = new Thread(enemyWaveTask);
            enemyWaveThread.setDaemon(true);
            if (!Main.getGameController().getTowersController().getTowersShootTask().isRunning()) {
                Main.getGameController().getTowersController().startTowersShootTask();
            }
            enemies.getChildren().setAll(enemiesList);
            for (Enemy enemy : enemyList) {
                enemies.getChildren().add(enemy.getEnemyHealthDisplay());
                enemy.translateXProperty().addListener(new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number> observable,
                                        Number oldValue, Number newValue) {
                        enemy.getEnemyHealthDisplay().setTranslateX((Double) newValue + 15);
                    }
                });
                enemy.translateYProperty().addListener(new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number> observable,
                                        Number oldValue, Number newValue) {
                        enemy.getEnemyHealthDisplay().setTranslateY((Double) newValue - 100);
                    }
                });
            }
            Main.getGameController().getTowersController().setEnemies(getEnemies());
            startEnemyWaveTask();
            Main.getGameController().getPlayButton().setDisable(true);
            newRound = false;
        }
        if (healthDecreased) {
            healthDecreased = false;
            ObservableList<Node> toRemove = FXCollections.observableArrayList();
            Platform.runLater(() -> {
                for (Node enemy : enemies.getChildren()) {
                    Enemy enemyCast;
                    if (enemy instanceof Enemy) {
                        enemyCast = (Enemy) enemy;
                        //System.out.println("RUN");
                        enemyCast.getEnemyHealthDisplay().setText(
                                String.valueOf(enemyCast.getHealthProp().intValue()));
                        if (enemyCast.getHealth() <= 0) {
                            enemyCast.setHealth(0);
                            enemy.setVisible(false);
                            enemyCast.getPathTransition().stop();
                            toRemove.add(enemyCast);
                            toRemove.add(enemyCast.getEnemyHealthDisplay());
                            enemyCast.getEnemyHealthDisplay().setVisible(false);
                        }
                    }
                }
                if (allSpawned) {
                    //System.out.println("Hello");
                    enemies.getChildren().removeAll(toRemove);
                }
                if (enemies.getChildren().size() == 0) {
                    endEnemyWaveTask();
                    Main.getGameController().getPlayButton().setDisable(false);
                }
            });
        }
    }

    private void startEnemyWaveTask() {
        enemyWaveThread.start();
    }

    private void endEnemyWaveTask() {
        enemyWaveTask.cancel();
    }

    @FXML
    public void spawnEnemies(int roundCounter) {
        enemyList = new ArrayList<>();
        //change this for loop to change # of enemies that spawn on a given round.
        int enemiesAmount = 10;
        if (roundCounter == 3) {
            enemiesAmount = 1;
        }
        for (int i = 0; i < enemiesAmount; i++) {
            Enemy enemy = new Enemy();
            if (roundCounter == 3) {
                enemy.setImage(new Image("https://i.groupme.com/56x56.gif.8a247c309e574de3bb5d274246bc87b0"));
                enemy.setHealth(100);
            }
            enemy.getHealthProp().addListener(new ChangeListener<Number>() {
                public void changed(ObservableValue<? extends Number> observable,
                                    Number oldValue, Number newValue) {
                    if (newValue.intValue() <= 0) {
                        oldValue = 0;
                    }
                    if (newValue.intValue() <= 0) {
                        newValue = 0;
                    }
                    //enemy.getEnemyHealthDisplay().textProperty().setValue(String.valueOf(0));
                    healthDecreased = true;
                    // removing enemy if health drops to 0 or below
                    initialize(null, null);

                }
            });
            enemy.getPathTransition().setNode(enemy);
            enemy.getPathTransition().setOnFinished(e -> {
                Main.getGameController().setHealth(Main.getGameController().getHealth()
                        - enemy.getHealth());
                enemies.getChildren().remove(enemy);
                enemies.getChildren().remove(enemy.getEnemyHealthDisplay());
                //System.out.println(enemies.getChildren().size());
                if (enemies.getChildren().size() == 0) {
                    endEnemyWaveTask();
                    Main.getGameController().getPlayButton().setDisable(false);
                }
            });
            enemyList.add(enemy);
        }
        enemiesList = FXCollections.observableArrayList(enemyList);
        newRound = true;
        initialize(null, null);
    }

    public ArrayList<Enemy> getEnemies() {
        enemiesList = enemies.getChildren();
        ArrayList<Enemy> returnArray = new ArrayList<Enemy>();
        for (Node enemy : enemiesList) {
            if (enemy instanceof Enemy) {
                returnArray.add((Enemy) enemy);
            }
        }
        return returnArray;
    }

    @FXML
    public Pane getAsteroids() {
        return enemies;
    }

    public Task<Integer> createEnemyWaveTask() {
        Task<Integer> enemyWaveTask = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                //System.out.println("hi");
                int numLeft = enemies.getChildren().size();
                while (!isCancelled()) {
                    for (Node enemy : enemies.getChildren()) {
                        if (numLeft == 0) {
                            //System.out.println("hello");
                            allSpawned = true;
                        }
                        Enemy enemyCast;
                        if (enemy instanceof Enemy) {
                            enemyCast = (Enemy) enemy;
                            enemyCast.getPathTransition().play();
                            Thread.sleep(1000);
                        }
                        numLeft--;
                    }
                }
                return 1;
            }
        };
        return enemyWaveTask;
    }
}