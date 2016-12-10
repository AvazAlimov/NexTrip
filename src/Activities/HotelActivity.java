package Activities;


import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HotelActivity implements Initializable {


    public Button locationButton;
    public Label infoText;
    public Button prevImage;
    public Button nextImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        infoText.setText("This is one of the best hotel in this area. Here you can feel yourself so amazing! We are waiting for you. " +
                "\nWe have many interesting things to do");
        prevImage.setText("<");
        nextImage.setText(">");

    }

    public void switchPrevImage(ActionEvent event) {


    }

    public void switchNextImage(ActionEvent event) {

    }
}
