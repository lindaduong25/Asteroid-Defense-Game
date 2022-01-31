package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Tower extends ImageView {

    private Circle range = new Circle(200);
    public  Enemy pointedEnemy = null;
    private int damage = 0;
    private int ID = 0;

    public Tower(int id, int difficulty) {
        if (id == 0) {
            this.setImage(new Image("https://i.groupme.com/800x800.gif.0750584f541b406e863f1f8f737ee073.large"));
            this.ID = 1;
            this.damage = 1;
            //this.setImage(new Image("https://i.groupme.com/32x32.gif.4781896dc7e54d37af440086d5f780d1.large"));
        } else if (id == 1) {
            this.setImage(new Image("https://i.groupme.com/224x224.gif.c9d70ec68ab348c986117aa45cf074d6.large"));
            this.damage = 10;
            this.ID = 2;
        } else if (id == 2) {
            this.setImage(new Image("https://i.groupme.com/800x800.gif.d5ce525f84404ce99eb2ef4471a2b91b.large"));
            this.damage = 10;
            this.ID = 3;
        }
        range.setFill(new Color(0f, 0f, 0f, .1));
        range.setStroke(Color.BLACK);
        range.setStrokeWidth(3);
        this.setFitHeight(100);
        this.setFitWidth(100);
        this.setLayoutX(50);
        this.setLayoutY(50);
    }

    public boolean enemyInRange(Enemy enemy) {
        if ((enemy.getTranslateX() - range.getLayoutX()) * (enemy.getTranslateX() - range.getLayoutX()) +
                (enemy.getTranslateY() - range.getLayoutY()) * (enemy.getTranslateY() - range.getLayoutY())
                <= (range.getRadius() * range.getRadius())) {
            pointedEnemy = enemy;
            // testing
            doDamage(enemy);
            return true;
        }
        return false;
    }
    public void doDamage(Enemy e) {
        e.decreaseHealth(this.damage);
        //e.getEnemyHealthDisplay().setText(String.valueOf(e.getHealth()));
        Main.getGameController().updateMoneyFromEnemyDamage(this.damage);
    }

    public void updateCoords(double x, double y) {
        this.setLayoutX(x - 40);
        this.setLayoutY(y - 150);
        range.setLayoutX(x + 10);
        range.setLayoutY(y - 75);
    }

    public Circle getRange() {
        return range;
    }



    public void updateRotate() {
        this.setRotate(((float) Math.toDegrees(Math.atan2(this.pointedEnemy.getTranslateY() - (this.getLayoutY()), this.pointedEnemy.getTranslateX() - (this.getLayoutX())))));
    }
    public void checkShooting() {
        if (this.ID == 1) {
            if (this.pointedEnemy != null) {
                this.setImage(new Image("https://i.groupme.com/800x800.gif.dc4d005243e84554993d4dd14ad9e7db"));
            } else {
                this.setImage(new Image("https://i.groupme.com/800x800.gif.0750584f541b406e863f1f8f737ee073.large"));
            }
        }
        if (this.ID == 2) {
            if (this.pointedEnemy != null) {
                this.setImage(new Image("https://i.groupme.com/224x224.gif.29096a4d46af4832ae06f1a336bce81b"));
            } else {
                this.setImage(new Image("https://i.groupme.com/224x224.gif.c9d70ec68ab348c986117aa45cf074d6.large"));
            }
        }
        if (this.ID == 3) {
            if (this.pointedEnemy != null) {
                this.setImage(new Image("https://i.groupme.com/800x800.gif.4084fec0e29241c6a5c372331ddcba01"));
            } else {
                this.setImage(new Image("https://i.groupme.com/800x800.gif.d5ce525f84404ce99eb2ef4471a2b91b"));
            }
        }
    }
    public Enemy getPointedEnemy() {
        return pointedEnemy;
    }

    public void setPointedEnemy(Enemy e) {
        pointedEnemy = e;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}