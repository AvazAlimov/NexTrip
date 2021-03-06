package Activities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartActivity implements Initializable {
    public ImageView imageLogo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(3200), null));
        timeline.setOnFinished(event -> {
            try {
                nextWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        timeline.play();
    }

    private void nextWindow() throws IOException {
        Main.stage.hide();
        Main.stage = new Stage();
        Main.stage.initStyle(StageStyle.DECORATED);
        Parent parent = FXMLLoader.load(getClass().getResource("../FXML/GuideWindow.fxml"));
        Scene scene = new Scene(parent);
        Main.stage.getIcons().add(new Image("Resources/icon.png"));
        Main.stage.setTitle("NexTrip");
        Main.stage.setMinWidth(600);
        Main.stage.setMinHeight(600);
        Main.stage.setMaximized(true);
        Main.stage.setScene(scene);
        Main.stage.show();
    }
}
