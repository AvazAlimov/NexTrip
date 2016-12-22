package Activities;

import Classes.*;
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
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@SuppressWarnings("Duplicates")
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
    public GridPane containerPane;
    public VBox container;
    public Button actionButton;
    private Client client;
    private ArrayList<String> imagePaths;

    private int id;

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
            case "Edit Object":
                loadObjects();
                backButton.setText("Back");
                mainPane.setVisible(false);
                containerPane.setVisible(true);
                break;
            case "Back":
                if (objectPane.isVisible()) {
                    objectPane.setVisible(false);
                    addChoicePane.setVisible(true);
                } else if (containerPane.isVisible()) {
                    containerPane.setVisible(false);
                    mainPane.setVisible(true);
                    backButton.setText("Log Out");
                } else {
                    backButton.setText("Log Out");
                    addChoicePane.setVisible(false);
                    mainPane.setVisible(true);
                }
            default:
                break;
        }
    }

    public void switchObjectPane(ActionEvent event) throws IOException {
        String text = ((Button) event.getSource()).getText();
        switch (text) {
            case "Hotel":
                actionButton.setText("Add Hotel");
                break;
            case "Restaurant":
                Parent parent = FXMLLoader.load(getClass().getResource("../FXML/AddRestaurantWindow.fxml"));
                Scene scene = new Scene(parent);
                Main.stage.hide();
                Main.stage.setScene(scene);
                Main.stage.show();
                break;
            case "Entertaining":
                parent = FXMLLoader.load(getClass().getResource("../FXML/EntertainingClientWindow.fxml"));
                scene = new Scene(parent);
                Main.stage.hide();
                Main.stage.setScene(scene);
                Main.stage.show();
                break;
            case "Things To Do":
                parent = FXMLLoader.load(getClass().getResource("../FXML/AddThingsToDoWindow.fxml"));
                scene = new Scene(parent);
                Main.stage.hide();
                Main.stage.setScene(scene);
                Main.stage.show();
                break;
            default:
                break;
        }
        addChoicePane.setVisible(false);
        objectPane.setVisible(true);
    }

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

        if (actionButton.getText().equals("Add Hotel"))
            SQLDataBase.addHotel(hotel, client);
        else if (actionButton.getText().equals("Edit Hotel")){
            hotel.setId(id);
            SQLDataBase.editHotel(hotel);
        }

        Tools.hotels.clear();
        SQLDataBase.loadHotels();

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
            btn.setStyle("-fx-background-color: transparent; -fx-content-display: top;");
        } else {
            btn.setId("y");
            btn.setStyle("-fx-background-color: greenyellow; -fx-content-display: top;");
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

    private void loadObjects() {
        container.getChildren().clear();
        String[] objects = client.getObjectId();
        for (String object : objects) {
            if (object.charAt(0) == 'H') {
                int id = Integer.parseInt(object.substring(1, object.length()));
                for (Hotel hotel : Tools.hotels)
                    if (hotel.getId() == id)
                        container.getChildren().add(fillHotelItem(hotel));
            } else if (object.charAt(0) == 'R') {
                int id = Integer.parseInt(object.substring(1, object.length()));
                for (Restaurant restaurant : Tools.restaurants)
                    if (restaurant.getId() == id)
                        container.getChildren().add(fillRestaurantItem(restaurant));
            } else if (object.charAt(0) == 'E') {
                int id = Integer.parseInt(object.substring(1, object.length()));
                for (Entertaining entertaining : Tools.entertainings)
                    if (entertaining.getId() == id)
                        container.getChildren().add(fillEntertainingItem(entertaining));
            } else if (object.charAt(0) == 'T') {
                int id = Integer.parseInt(object.substring(1, object.length()));
                for (ThingsToDo thingsToDo : Tools.thingsToDos)
                    if (thingsToDo.getId() == id)
                        container.getChildren().add(fillThingsToDoItem(thingsToDo));
            }
        }
    }

    private GridPane fillHotelItem(Hotel hotel) {
        GridPane item = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.SOMETIMES);
        ColumnConstraints col3 = new ColumnConstraints();
        item.getColumnConstraints().addAll(col1, col2, col3);
        item.setHgap(10);
        item.setStyle("-fx-padding: 10; -fx-background-color: rgba(0, 100, 100, 0.5);");

        URL url = null;
        try {
            url = new File(hotel.getPhotos().get(0)).toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Image value = null;
        if (url != null)
            value = new Image(url.toString(), 100.0, 100.0, false, true);

        ImageView image = new ImageView(value);
        Circle circle = new Circle(50.0);
        circle.setCenterX(50.0);
        circle.setCenterY(50.0);
        image.setClip(circle);
        item.add(image, 0, 0);

        Label info = new Label("Name: " + hotel.getName() + "\nPrice: " + hotel.getStartingPrice() + "$ - " + hotel.getEndingPrice() + "$\nLocation: " + hotel.getLocation());
        info.setStyle("-fx-font-size: 24; -fx-alignment: center-left;");
        item.add(info, 1, 0);

        HBox ratingBox = new HBox(5.0);
        ratingBox.setStyle("-fx-alignment: center;");

        int maxRate = 5;
        for (int i = 0; i < hotel.getRating(); i++) {
            Button star = new Button();
            star.setPrefSize(40.0, 40.0);
            star.setStyle("-fx-shape: " + Tools.filledStar);
            star.setDisable(true);
            ratingBox.getChildren().add(star);
            maxRate--;
        }
        for (int i = 0; i < maxRate; i++) {
            Button star = new Button();
            star.setPrefSize(40.0, 40.0);
            star.setStyle("-fx-shape: " + Tools.emptyStar);
            star.setDisable(true);
            ratingBox.getChildren().add(star);
        }
        item.add(ratingBox, 2, 0);

        item.setOnMouseClicked(event -> {
            isFilledError.setVisible(false);

            id = hotel.getId();
            nameText.setText(hotel.getName());
            locationText.setText(hotel.getLocation());
            infoText.setText(hotel.getInfo());
            startPrice.setText(hotel.getStartingPrice() + "");
            endPrice.setText(hotel.getEndingPrice() + "");
            setContacts(hotel);
            setAmenities(hotel);
            setPhotos(hotel);
            actionButton.setText("Edit Hotel");

            containerPane.setVisible(false);
            objectPane.setVisible(true);
        });

        return item;
    }

    private GridPane fillRestaurantItem(Restaurant restaurant) {
        GridPane item = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.SOMETIMES);
        ColumnConstraints col3 = new ColumnConstraints();
        item.getColumnConstraints().addAll(col1, col2, col3);
        item.setHgap(10);
        item.setStyle("-fx-padding: 10; -fx-background-color: rgba(0, 100, 100, 0.5);");

        URL url = null;
        try {
            url = new File(restaurant.getPhotos().get(0)).toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Image value = null;
        if (url != null)
            value = new Image(url.toString(), 100.0, 100.0, false, true);

        ImageView image = new ImageView(value);
        Circle circle = new Circle(50.0);
        circle.setCenterX(50.0);
        circle.setCenterY(50.0);
        image.setClip(circle);
        item.add(image, 0, 0);

        Label info = new Label("Name: " + restaurant.getName() + "\nType: " + restaurant.getType().get(0) + "\t Number of seats: " + restaurant.getNumberOfSeats() + "\nLocation: " + restaurant.getLocation());
        info.setStyle("-fx-font-size: 24; -fx-alignment: center-left;");
        item.add(info, 1, 0);

        HBox ratingBox = new HBox(5.0);
        ratingBox.setStyle("-fx-alignment: center;");

        int maxRate = 5;
        for (int i = 0; i < restaurant.getRating(); i++) {
            Button star = new Button();
            star.setPrefSize(40.0, 40.0);
            star.setStyle("-fx-shape: " + Tools.filledStar);
            star.setDisable(true);
            ratingBox.getChildren().add(star);
            maxRate--;
        }
        for (int i = 0; i < maxRate; i++) {
            Button star = new Button();
            star.setPrefSize(40.0, 40.0);
            star.setStyle("-fx-shape: " + Tools.emptyStar);
            star.setDisable(true);
            ratingBox.getChildren().add(star);
        }
        item.add(ratingBox, 2, 0);

        item.setOnMouseClicked(event -> {

        });

        return item;
    }

    private GridPane fillThingsToDoItem(ThingsToDo thingsToDo) {
        GridPane item = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.SOMETIMES);
        ColumnConstraints col3 = new ColumnConstraints();
        item.getColumnConstraints().addAll(col1, col2, col3);
        item.setHgap(10);
        item.setStyle("-fx-padding: 10; -fx-background-color: rgba(0, 100, 100, 0.5);");

        URL url = null;
        try {
            url = new File(thingsToDo.getPhotos().get(0)).toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Image value = null;
        if (url != null)
            value = new Image(url.toString(), 100.0, 100.0, false, true);

        ImageView image = new ImageView(value);
        Circle circle = new Circle(50.0);
        circle.setCenterX(50.0);
        circle.setCenterY(50.0);
        image.setClip(circle);
        item.add(image, 0, 0);

        Label info = new Label("Name: " + thingsToDo.getName() + "\nPrice: " + thingsToDo.getPrice() + " $\t" + "\nLocation: " + thingsToDo.getLocation());
        info.setStyle("-fx-font-size: 24; -fx-alignment: center-left;");
        item.add(info, 1, 0);

        HBox ratingBox = new HBox(5.0);
        ratingBox.setStyle("-fx-alignment: center;");

        int maxRate = 5;
        for (int i = 0; i < thingsToDo.getRating(); i++) {
            Button star = new Button();
            star.setPrefSize(40.0, 40.0);
            star.setStyle("-fx-shape: " + Tools.filledStar);
            star.setDisable(true);
            ratingBox.getChildren().add(star);
            maxRate--;
        }
        for (int i = 0; i < maxRate; i++) {
            Button star = new Button();
            star.setPrefSize(40.0, 40.0);
            star.setStyle("-fx-shape: " + Tools.emptyStar);
            star.setDisable(true);
            ratingBox.getChildren().add(star);
        }
        item.add(ratingBox, 2, 0);

        item.setOnMouseClicked(event -> {

        });

        return item;
    }

    private GridPane fillEntertainingItem(Entertaining entertaining) {
        GridPane item = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.SOMETIMES);
        ColumnConstraints col3 = new ColumnConstraints();
        item.getColumnConstraints().addAll(col1, col2, col3);
        item.setHgap(10);
        item.setStyle("-fx-padding: 10; -fx-background-color: rgba(0, 100, 100, 0.5);");

        URL url = null;
        try {
            url = new File(entertaining.getPhotos().get(0)).toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Image value = null;
        if (url != null)
            value = new Image(url.toString(), 100.0, 100.0, false, true);

        ImageView image = new ImageView(value);
        Circle circle = new Circle(50.0);
        circle.setCenterX(50.0);
        circle.setCenterY(50.0);
        image.setClip(circle);
        item.add(image, 0, 0);

        Label info = new Label("Name: " + entertaining.getName() + "\nPrice: " + entertaining.getPrice() + " $\t" + "\nLocation: " + entertaining.getLocation());
        info.setStyle("-fx-font-size: 24; -fx-alignment: center-left;");
        item.add(info, 1, 0);

        HBox ratingBox = new HBox(5.0);
        ratingBox.setStyle("-fx-alignment: center;");

        int maxRate = 5;
        for (int i = 0; i < entertaining.getRating(); i++) {
            Button star = new Button();
            star.setPrefSize(40.0, 40.0);
            star.setStyle("-fx-shape: " + Tools.filledStar);
            star.setDisable(true);
            ratingBox.getChildren().add(star);
            maxRate--;
        }
        for (int i = 0; i < maxRate; i++) {
            Button star = new Button();
            star.setPrefSize(40.0, 40.0);
            star.setStyle("-fx-shape: " + Tools.emptyStar);
            star.setDisable(true);
            ratingBox.getChildren().add(star);
        }
        item.add(ratingBox, 2, 0);

        item.setOnMouseClicked(event -> {

        });

        return item;
    }

    private void setContacts(Hotel hotel) {
        ArrayList<Contact> contacts = hotel.getContacts();
        for (Contact contact : contacts) {
            if (contact.getType() == Contact.Type.Facebook)
                facebookContact.setText(contact.getSource());
            else if (contact.getType() == Contact.Type.PhoneNumber)
                phoneContact.setText(contact.getSource());
            else if (contact.getType() == Contact.Type.Mail)
                mailContact.setText(contact.getSource());
            else if (contact.getType() == Contact.Type.Site)
                siteContact.setText(contact.getSource());
            else if (contact.getType() == Contact.Type.Telegram)
                telegramContact.setText(contact.getSource());
        }
    }

    private void setAmenities(Hotel hotel) {
        ArrayList<String> amenities = hotel.getAmenties();
        for (String amenity : amenities) {
            if (amenity.equals(freeParking.getText())) {
                freeParking.setId("y");
                freeParking.setStyle("-fx-background-color: greenyellow; -fx-content-display: top;");
            } else if (amenity.equals(freeWifi.getText())) {
                freeWifi.setId("y");
                freeWifi.setStyle("-fx-background-color: greenyellow; -fx-content-display: top;");
            } else if (amenity.equals(freeYard.getText())) {
                freeYard.setId("y");
                freeYard.setStyle("-fx-background-color: greenyellow; -fx-content-display: top;");
            }
        }
    }

    private void setPhotos(Hotel hotel) {
        imagePaths.clear();
        imageContainer.getChildren().remove(1,imageContainer.getChildren().size());

        ArrayList<String> photos = hotel.getPhotos();
        for (String photo : photos) {
            try {
                URL url = new File(photo).toURI().toURL();
                Runnable run = () -> {
                    Image image = new Image(url.toString(), 200.0, 200.0, true, true);
                    ImageView imageView = new ImageView(image);
                    imageContainer.getChildren().add(imageView);
                    imagePaths.add(photo);
                };
                run.run();
            } catch (Exception ignored) {
            }
        }
    }
}