package sample;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tower {

    private Image image;
    private ImageView imageView;

    public Tower(int id, int difficulty) {
        if (id == 0) {
            image = new Image("https://i.groupme.com/800x800.gif.6286be0801934dc7bc62f81db12bbfd7.large");
            imageView = new ImageView(image);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
        }
    }

    public ImageView getImageView() {
        return imageView;
    }
}
