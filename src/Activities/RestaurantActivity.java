package Activities;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class RestaurantActivity implements Initializable {

    public TextField nameText;
    public TextField locationText;
    public TextArea infoText;
    public TextField startPrice;
    public TextField endPrice;
    public ComboBox currencySelectionButton;
    public HBox imageContainer;
    public Button freeWifi;
    public Button freeParking;
    public Button freeYard;
    public TextField mailContact;
    public TextField phoneContact;
    public TextField facebookContact;
    public TextField telegramContact;
    public TextField siteContact;
    public Button addInfoButton ;
    public Button backButton;
    public Label usernameText;
    public GridPane objectPane;
    public TextField nameOfFoodText;
    public TextField PriceOfFoodText;
    public Button addFoodButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addImage(ActionEvent event) {

    }

    public void amenityPressed(ActionEvent event) {

    }

    public void switchPane(ActionEvent event) {

    }

    public void addRestaurant(ActionEvent event) {

    }
}
