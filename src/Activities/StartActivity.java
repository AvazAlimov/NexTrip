package Activities;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class StartActivity implements Initializable {

    public ImageView imageLogo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Timeline animation = new Timeline(new KeyFrame(new Duration(10), event -> animate()));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
        /*new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        animation.stop();
                    }
                },
                10000
        );*/
    }

    private void animate() {
        imageLogo.getTransforms().add(new Rotate(0.3, 300, 300));
    }
}
