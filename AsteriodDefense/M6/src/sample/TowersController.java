package sample;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TowersController implements Initializable {
    @FXML
    private Pane towers;

    @FXML
    private Tower tower;

    private ObservableList<Node> towerList;

    private int difficulty;

    private boolean start = true;

    private boolean selected = false;

    private Thread towersShootThread;

    private ArrayList<Enemy> enemies;

    private boolean created = false;

    private boolean towerMove = false;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (start) {
            difficulty = Main.getConfigController().getDifficulty();
            start = false;
            towersShootThread = new Thread(towersShootTask);
            towersShootThread.setDaemon(true);
        }
        if (selected) {
            if (!towers.getChildren().contains(tower)) {
                towers.getChildren().add(tower.getRange());
                towers.getChildren().add(tower);
            }
            selected = false;
        }
    }

    public void selectedTower(int id) {
        tower = new Tower(id, difficulty);
        selected = true;
    }

    @FXML
    public void placeTower(MouseEvent mouseEvent) throws IOException {
        if (towerMove) {
            selected = true;
            towerMove = false;
        }
        for (Tower tower : getTowers()) {
            if (tower.getBoundsInParent().contains(mouseEvent.getX(), mouseEvent.getY())) {
                System.out.println("ran");
                this.tower = tower;
                created = true;
                towerMove = true;
            }
        }
        if (selected) {
            if (validCoord(mouseEvent.getSceneX(), mouseEvent.getSceneY())) {
                if (created || Main.getGameController().purchaseTower()) {
                    created = false;
                    tower.updateCoords(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                    initialize(null, null);
                }
            }
        }
    }




    private boolean validCoord(double x, double y) {
        ObservableList<Node> paths = Main.getGameController().getPaths();
        int yRestraint = 40;
        int xRestraint = 30;
        for (Node path : paths) {
            ImageView pathData = (ImageView) path;
            if ((x > (pathData.getLayoutX() - xRestraint)
                    && x < pathData.getLayoutX() + xRestraint + pathData.getFitWidth())
                    && (y > (pathData.getLayoutY() - yRestraint)
                    && y < pathData.getLayoutY() + yRestraint + pathData.getFitHeight())) {
                return false;
            }
        }
        return true;
    }


    public boolean updateRanges() {
        if (tower != null) {
            tower.getRange().setRadius((int) Math.ceil(tower.getRange().getRadius() * 1.1));
            initialize(null, null);
            return true;
        }
        return false;
    }

    public boolean updateDamage() {
        if (tower != null) {
            tower.setDamage((int) Math.ceil(1.1 * tower.getDamage()));
            initialize(null, null);
            return true;
        }
        return false;
    }

    public ArrayList<Tower> getTowers() {
        towerList = towers.getChildren();
        ArrayList<Tower> returnArray = new ArrayList<>();
        for (Node tower : towerList) {
            if (tower instanceof Tower) {
                returnArray.add((Tower) tower);
            }
        }
        return returnArray;
    }

    private Task<Integer> towersShootTask = new Task<Integer>() {
        @Override protected Integer call() throws Exception {
            while (!isCancelled()) {
                Thread.sleep(750);

                ArrayList<Tower> towers = getTowers();
                ArrayList<Enemy> enemies =
                        Main.getGameController().getEnemiesController().getEnemies();
                for (Enemy enemy : enemies) {
                    //System.out.println(enemy.getHealth());
                    for (Tower tower : towers) {
                        tower.checkShooting();
                        //System.out.println(tower.pointedEnemy);
                        if (((tower.getPointedEnemy() == null
                                || !tower.getPointedEnemy().isVisible()
                                || !tower.enemyInRange(tower.getPointedEnemy()))
                                && tower.enemyInRange(enemy)) || (enemy == tower.getPointedEnemy()
                                && tower.enemyInRange(enemy))) {
                            tower.setPointedEnemy(enemy);
                            tower.updateRotate();
                            tower.doDamage(enemy);
                        }
                    }
                }
                //System.out.println("Tasky boi");
            }
            return 1;
        }
    };
    public void startTowersShootTask() {
        towersShootThread.start();
    }

    public void endTowersShootTask() {
        towersShootTask.cancel();
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public Task<Integer> getTowersShootTask() {
        return towersShootTask;
    }
}

