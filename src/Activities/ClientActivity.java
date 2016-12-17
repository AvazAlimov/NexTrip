package Activities;

import Classes.Client;
import Classes.Contact;
import Classes.Hotel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientActivity implements Initializable {
    public Label usernameText;
    public Button backButton;
    public GridPane addChoicePane;
    public GridPane objectPane;
    public GridPane mainPane;
    public TextField nameText;
    public TextField locationText;
    public TextArea infoText;
    public TextField startPrice;
    public TextField endPrice;
    public TextField facebookContact;
    public TextField telegramContact;
    public TextField mailContact;
    public TextField phoneContact;
    public TextField siteContact;
    public Button freeWifi;
    public Button freeParking;
    public Button freeYard;
    public HBox imageContainer;
    public Label creditErrorMessage;
    public Label isFilledError;
    private Client client;
    private ArrayList<String> imagePaths;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imagePaths = new ArrayList<>();
        client = Tools.client;
        usernameText.setText(client.getUsername());
    }

    public void switchPane(ActionEvent event) throws IOException {
        String text = ((Button) event.getSource()).getText();
        isFilledError.setVisible(false);

        switch (text) {
            case "Log Out":
                Parent parent = FXMLLoader.load(getClass().getResource("../FXML/MainWindow.fxml"));
                Scene scene = new Scene(parent);
                Main.stage.hide();
                Main.stage.setScene(scene);
                Main.stage.show();
                break;
            case "Add Object":
                if (!client.canAddObject()) {
                    addChoicePane.setDisable(true);
                    creditErrorMessage.setVisible(true);
                } else {
                    addChoicePane.setDisable(false);
                    creditErrorMessage.setVisible(false);
                }
                backButton.setText("Back");
                mainPane.setVisible(false);
                addChoicePane.setVisible(true);
                break;
            case "Back":
                if (objectPane.isVisible()) {
                    objectPane.setVisible(false);
                    addChoicePane.setVisible(true);
                } else {
                    backButton.setText("Log Out");
                    addChoicePane.setVisible(false);
                    mainPane.setVisible(true);
                }
            default:
                break;
        }
    }

    public void switchObjectPane(ActionEvent event) {
        String text = ((Button) event.getSource()).getText();
        addChoicePane.setVisible(false);
        objectPane.setVisible(true);

        switch (text) {
            case "Hotel":
                break;
            case "Restaurant":
                break;
            case "Entertaining":
                break;
            case "Things To Do":
                break;
            default:
                break;
        }
    }

    //TODO:check for filled
    public void addHotel() {

        if (!isFilled()) {
            isFilledError.setVisible(true);
            return;
        }
        isFilledError.setVisible(false);

        Hotel hotel = new Hotel();
        hotel.setName(nameText.getText());
        hotel.setLocation(locationText.getText());
        hotel.setInfo(infoText.getText());
        hotel.setStartingPrice(Double.parseDouble(startPrice.getText()));
        hotel.setEndingPrice(Double.parseDouble(endPrice.getText()));
        hotel.setAmenities(getAmenitites());
        hotel.setContacts(getContacts());
        hotel.setNumberOfRooms(0);
        hotel.setPhotos(imagePaths);
        SQLDataBase.addHotel(hotel, client);
        Tools.hotels.add(hotel);
        imagePaths.clear();

        objectPane.setVisible(false);
        addChoicePane.setVisible(true);
    }

    private boolean isFilled() {
        return !(nameText.getText().isEmpty() || locationText.getText().isEmpty() || infoText.getText().isEmpty());
    }

    public void amenityPressed(ActionEvent event) {
        Button btn = (Button) event.getSource();
        if (btn.getId().equals("y")) {
            btn.setId("");
            btn.setStyle("-fx-background-color: transparent;");
        } else {
            btn.setId("y");
            btn.setStyle("-fx-background-color: greenyellow;");
        }
    }

    private ArrayList<String> getAmenitites() {
        ArrayList<String> string = new ArrayList<>();
        if (freeParking.getId().equals("y"))
            string.add(freeParking.getText());
        if (freeWifi.getId().equals("y"))
            string.add(freeWifi.getText());
        if (freeYard.getId().equals("y"))
            string.add(freeYard.getText());
        return string;
    }

    private ArrayList<Contact> getContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();
        if (!facebookContact.getText().isEmpty())
            contacts.add(new Contact(facebookContact.getText(), Contact.Type.Facebook));
        if (!mailContact.getText().isEmpty())
            contacts.add(new Contact(mailContact.getText(), Contact.Type.Mail));
        if (!telegramContact.getText().isEmpty())
            contacts.add(new Contact(telegramContact.getText(), Contact.Type.Telegram));
        if (!siteContact.getText().isEmpty())
            contacts.add(new Contact(siteContact.getText(), Contact.Type.Site));
        if (!phoneContact.getText().isEmpty())
            contacts.add(new Contact(phoneContact.getText(), Contact.Type.PhoneNumber));
        return contacts;
    }

    public void addImage() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose Image");

        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG files (*.jpg)(*.jpeg)(*.png)", "*.jpg", "*.jpeg", "*.png"));
        try {
            File file = chooser.showOpenDialog(Main.stage.getScene().getWindow());
            String path = file.getPath();
            URL url = new File(path).toURI().toURL();
            Runnable run = () -> {
                Image image = new Image(url.toString(), 200.0, 200.0, true, true);
                ImageView imageView = new ImageView(image);
                imageContainer.getChildren().add(imageView);
                imagePaths.add(path);
            };
            run.run();
        } catch (Exception ignored) {
        }
    }
}