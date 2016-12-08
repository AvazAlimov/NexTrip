package Activities;


import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class HotelActivity implements Initializable{


    public ImageView hotelImageView;
    public Label hotelInfoText;
    public Button prevImage;
    public Button nextImage;
    public Button backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prevImage.setText("<");
        nextImage.setText(">");

        hotelInfoText.setText("This is hotel's name");



    }

    public void switchPrevImage(ActionEvent event) {

    }

    public void switchNextImage(ActionEvent event) {

    }
}
