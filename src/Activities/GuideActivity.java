package Activities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GuideActivity implements Initializable {
    public ImageView imageView;
    public Button skipButton;
    public Button prevImage;
    public Button nextImage;
    private String[] texts;
    private String[] paths;
    private int index;
    public Label text;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prevImage.setText("<");
        nextImage.setText(">");

        paths = new String[]{String.valueOf(getClass().getClassLoader().getResource("Resources/1.png")),
                String.valueOf(getClass().getClassLoader().getResource("Resources/2.png")),
                String.valueOf(getClass().getClassLoader().getResource("Resources/3.png"))};
        texts = new String[]{"Easy to find", "Rate as you want", "Find contacts and leave comments"};
        index = 0;
        imageView.setImage(new Image(paths[index]));
        text.setText(texts[index]);
    }

    public void switchPrevImage() {
        if (index == 0)
            index = 3;
        index--;
        imageView.setImage(new Image(paths[index]));
        text.setText(texts[index]);
    }

    public void switchNextImage() {
        if (index == 2)
            index = -1;
        index++;
        imageView.setImage(new Image(paths[index]));
        text.setText(texts[index]);
    }

    public void switchImage(ActionEvent event) {
        String id = ((Control) event.getSource()).getId();
        index = Integer.parseInt(id);
        imageView.setImage(new Image(paths[index]));
        text.setText(texts[index]);
    }

    public void nextWindow() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/MainWindow.fxml"));
        Scene scene = new Scene(parent);
        Main.stage.hide();
        Main.stage.setScene(scene);
        Main.stage.show();
    }
}
