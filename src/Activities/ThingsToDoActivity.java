package Activities;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ThingsToDoActivity implements Initializable {

    public Button prevImage;
    public ImageView imageView;
    public Button nextImage;
    public Label nameLabel;
    public Button locationButton;
    public Label locationLabel;
    public HBox ratingLayout;
    public Label numberRating;
    public HBox stars;
    public TextArea infoText;
    public Button freeWiFi;
    public Button freeParking;
    public Button freeYard;
    public Label priceLabel;
    public Button dollarSign;
    public HBox contactContainer;
    public Button mail_icon;
    public Button phone_icon;
    public Button facebook_icon;
    public Button telegram_icon;
    public Button web_icon;
    public VBox commentContainer;
    public TextField commentText;
    public GridPane messageLayout;
    public Label message;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void backToMain(ActionEvent event) {

    }

    public void switchPrevImage(ActionEvent event) {

    }

    public void switchNextImage(ActionEvent event) {

    }

    public void restoreStars(MouseEvent mouseEvent) {

    }

    public void rateHotel(ActionEvent event) {

    }

    public void fillStars(MouseEvent mouseEvent) {

    }

    public void showSource(ActionEvent event) {

    }

    public void addComment(ActionEvent event) {

    }

    public void closeMessageLayout(MouseEvent mouseEvent) {

    }
}
