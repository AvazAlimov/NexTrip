package Activities;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddThingsToDoActivity implements Initializable {

    public GridPane objectPane;
    public TextField nameText;
    public TextField locationText;
    public TextArea infoText;
    public TextArea rulesText;
    public TextField priceText;
    public HBox imageContainer;
    public Button freeWifi;
    public Button freeParking;
    public Button freeYard;
    public TextField mailContact;
    public TextField phoneContact;
    public TextField facebookContact;
    public TextField telegramContact;
    public TextField siteContact;
    public Button addInfoButton;
    public Button backButton;
    public Label usernameText;
    public DatePicker startDate;
    public DatePicker endDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addImage(ActionEvent event) {

    }

    public void amenityPressed(ActionEvent event) {

    }

    public void switchPane(ActionEvent event) {

    }

    public void datePressed(MouseEvent mouseEvent) {

    }
}
