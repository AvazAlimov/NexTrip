package Activities;


import Classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@SuppressWarnings("Duplicates")
public class AddEntertainingActivity implements Initializable {

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
    public Button backButton;
    public Label usernameText;
    public Label isFilledError;
    public ComboBox<String> ageLimit;

    private Client client;
    private ArrayList<String> imagePaths;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imagePaths = new ArrayList<>();
        client = Tools.client;
        usernameText.setText(client.getUsername());

        ageLimit.getItems().add("0+");
        ageLimit.getItems().add("12+");
        ageLimit.getItems().add("18+");
        ageLimit.getItems().add("21+");
    }

    public void addEntertaining() {
        if (!isFilled()) {
            isFilledError.setVisible(true);
            return;
        }

        isFilledError.setVisible(false);

        Entertaining entertaining = new Entertaining();
        entertaining.setName(nameText.getText());
        entertaining.setLocation(locationText.getText());
        entertaining.setInfo(infoText.getText());
        entertaining.setPrice(Double.parseDouble(priceText.getText()));
        entertaining.setAmenities(getAmenitites());
        entertaining.setContacts(getContacts());
        entertaining.setRules(rulesText.getText());

        entertaining.setAgeLimit(ageInt(ageLimit.getSelectionModel().getSelectedItem()));
        entertaining.setPhotos(imagePaths);

        SQLDataBase.addEntertaining(entertaining, client);
        Tools.entertainings.clear();
        SQLDataBase.loadEntertaining();
        imagePaths.clear();

        try {
            closeWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int ageInt(String string){
        int index = string.indexOf('+');
        return Integer.parseInt(string.substring(0,index));
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

    private boolean isFilled() {
        return !(nameText.getText().isEmpty() || locationText.getText().isEmpty() || infoText.getText().isEmpty() || priceText.getText().isEmpty());
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

    public void amenityPressed(ActionEvent event) {
        Button btn = (Button) event.getSource();
        if (btn.getId().equals("y")) {
            btn.setId("");
            btn.setStyle("-fx-background-color: transparent; -fx-content-display: top;");
        } else {
            btn.setId("y");
            btn.setStyle("-fx-background-color: greenyellow; -fx-content-display: top;");
        }
    }

    public void closeWindow() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../FXML/ClientWindow.fxml"));
        Scene scene = new Scene(parent);
        Main.stage.hide();
        Main.stage.setScene(scene);
        Main.stage.show();
    }
}
