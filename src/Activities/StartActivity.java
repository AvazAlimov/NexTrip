package Activities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartActivity implements Initializable {

    public ImageView imageLogo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), null));
        timeline.setCycleCount(3);
        timeline.setOnFinished(event -> {
            Main.stage.setMaximized(true);
            try {
                nextWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        timeline.play();
    }

    private void nextWindow() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../FXML/GuideWindow.fxml"));
        Scene scene = new Scene(parent);
        Main.stage.hide();
        Main.stage.setScene(scene);
        Main.stage.show();
    }
}
