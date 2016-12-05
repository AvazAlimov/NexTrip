package Activities;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartActivity implements Initializable {

    public ImageView imageLogo;
    public Label logoName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Timeline animation = new Timeline(new KeyFrame(new Duration(10), event -> animate()));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    private void animate() {
        imageLogo.getTransforms().add(new Rotate(0.3, 300, 300));
    }

    public void nextWindow() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../FXML/GuideWindow.fxml"));
        Scene scene = new Scene(parent);
        Main.stage.hide();
        Main.stage.setScene(scene);
        Main.stage.show();
    }
}
