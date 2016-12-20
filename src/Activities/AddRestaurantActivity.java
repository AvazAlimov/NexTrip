package Activities;

import Classes.Client;
import Classes.Contact;
import Classes.Restaurant;
import Classes.Menu;
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
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddRestaurantActivity implements Initializable {

    public TextField nameText;
    public TextField locationText;
    public TextArea infoText;
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
    public Button cafeIcon;
    public Button fastFoodIcon;
    public Button cuisineIcon;
    public Button sitDownIcon;
    public TextField numberOfSeatsText;
    public Label isFilledError;
    public VBox menuContainer;
    public Button removeMenuButton;

    private Client client;
    private ArrayList<String> imagePaths;
    private ArrayList<TextField> foodNames;
    private ArrayList<TextField> foodPrices;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.client = Tools.client;
        usernameText.setText(client.getUsername());
        imagePaths = new ArrayList<>();
        foodNames = new ArrayList<>();
        foodPrices = new ArrayList<>();
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

    private ArrayList<Restaurant.Type> getTyes(){
        ArrayList<Restaurant.Type> types = new ArrayList<>();
        if(cafeIcon.getId().equals("y"))
            types.add(Restaurant.Type.valueOf(cafeIcon.getText()));
        if(fastFoodIcon.getId().equals("y"))
            types.add(Restaurant.Type.valueOf(fastFoodIcon.getText()));
        if(cuisineIcon.getId().equals("y"))
            types.add(Restaurant.Type.valueOf(cuisineIcon.getText()));
        if(sitDownIcon.getId().equals("y"))
            types.add(Restaurant.Type.valueOf(sitDownIcon.getText()));
        return types;
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

    public void typePressed(ActionEvent event) {
        Button btn = (Button) event.getSource();
        if (btn.getId().equals("y")) {
            btn.setId("");
            btn.setStyle("-fx-background-color: transparent; -fx-content-display: top;");
        } else {
            btn.setId("y");
            btn.setStyle("-fx-background-color: greenyellow; -fx-content-display: top;");
        }
    }

    public void addRestaurant() {
        if (!isFilled()) {
            isFilledError.setVisible(true);
            return;
        }
        isFilledError.setVisible(false);

        Restaurant restaurant = new Restaurant();
        restaurant.setName(nameText.getText());
        restaurant.setLocation(locationText.getText());
        restaurant.setInfo(infoText.getText());
        restaurant.setMenu(generateMenu());
        restaurant.setType(getTyes());
        restaurant.setNumberOfSeats(Integer.parseInt(numberOfSeatsText.getText()));
        restaurant.setAmenities(getAmenitites());
        restaurant.setContacts(getContacts());
        restaurant.setPhotos(imagePaths);

        SQLDataBase.addRestaurant(restaurant, client);
        Tools.hotels.clear();
        SQLDataBase.loadHotels();
        imagePaths.clear();
    }

    private Menu generateMenu(){
        Menu menu = new Menu();
        for(int i = 0; i<foodNames.size(); i++)
            menu.addItem(foodNames.get(i).getText(), foodPrices.get(i).getText());
        return menu;
    }

    private boolean isFilled() {
        return !(nameText.getText().isEmpty() || locationText.getText().isEmpty() || infoText.getText().isEmpty());
    }

    public void switchPane() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../FXML/ClientWindow.fxml"));
        Scene scene = new Scene(parent);
        Main.stage.hide();
        Main.stage.setScene(scene);
        Main.stage.show();
    }

    public void addMenu() {
        HBox box = new HBox(20.0);
        TextField name = new TextField();
        name.setPromptText("food name");
        name.setId("name");
        name.setPrefSize(300.0,30.0);

        TextField price = new TextField();
        price.setPromptText("price");
        price.setId("name");
        price.setPrefSize(100.0,30.0);

        ImageView icon = new ImageView(new Image("Resources/price_icon(128px).png"));
        icon.setFitHeight(40.0);
        icon.setFitWidth(40.0);

        box.getChildren().add(name);
        box.getChildren().add(price);
        box.getChildren().add(icon);

        foodNames.add(name);
        foodPrices.add(price);
        menuContainer.getChildren().add(menuContainer.getChildren().size() - 1,box);
        removeMenuButton.setVisible(true);
    }

    public void removeMenu() {
        menuContainer.getChildren().remove(menuContainer.getChildren().size() - 2);
        foodNames.remove(foodNames.size() - 1);
        foodPrices.remove(foodPrices.size() - 1);
        if(menuContainer.getChildren().size() == 1)
            removeMenuButton.setVisible(false);
    }
}
