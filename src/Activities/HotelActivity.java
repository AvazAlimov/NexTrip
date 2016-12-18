package Activities;


import Classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sun.util.resources.LocaleData;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class HotelActivity implements Initializable {
    public Button locationButton;
    public TextArea infoText;
    public Button prevImage;
    public Button nextImage;
    public ImageView imageView;
    public Label nameLabel;
    public Label locationLabel;
    public Button freeWiFi;
    public Button freeParking;
    public Button freeYard;
    public Label priceLabel;
    public Button mail_icon;
    public Button phone_icon;
    public Button facebook_icon;
    public Button telegram_icon;
    public Button web_icon;
    public HBox contactContainer;
    public GridPane messageLayout;
    public Label message;
    public ScrollPane mainLayout;
    public TextField commentText;
    public VBox commentContainer;

    private ArrayList<Image> images;
    private int imageIndex;

    private Hotel hotel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        images = new ArrayList<>();
        imageIndex = 0;

        this.hotel = Tools.hotels.get(1);
        for (String path : hotel.getPhotos()) {
            try {
                URL url = new File(path).toURI().toURL();
                images.add(new Image(url.toString()));
            } catch (MalformedURLException ignored) {
            }
        }

        if (!images.isEmpty())
            imageView.setImage(images.get(0));

        nameLabel.setText(hotel.getName());
        locationLabel.setText(hotel.getLocation());
        infoText.setText(hotel.getInfo());
        for (String amenity : hotel.getAmenties()) {
            switch (amenity) {
                case "Free WiFi":
                    freeWiFi.setVisible(true);
                    break;
                case "Free Parking":
                    freeParking.setVisible(true);
                    break;
                case "Children Yard":
                    freeYard.setVisible(true);
                    break;
            }
        }
        priceLabel.setText(hotel.getStartingPrice() + " - " + hotel.getEndingPrice());

        for (Contact contact : hotel.getContacts()) {
            if (contact.getType() == Contact.Type.Facebook) {
                facebook_icon.setVisible(true);
                facebook_icon.setId(contact.getSource());
            }
            if (contact.getType() == Contact.Type.Telegram) {
                telegram_icon.setVisible(true);
                telegram_icon.setId(contact.getSource());
            }
            if (contact.getType() == Contact.Type.Mail) {
                mail_icon.setVisible(true);
                mail_icon.setId(contact.getSource());
            }
            if (contact.getType() == Contact.Type.PhoneNumber) {
                phone_icon.setVisible(true);
                phone_icon.setId(contact.getSource());
            }
            if (contact.getType() == Contact.Type.Site) {
                web_icon.setVisible(true);
                web_icon.setId(contact.getSource());
            }
            deleteNullContacts();


        }
    }

    private void deleteNullContacts() {
        if (!facebook_icon.isVisible())
            contactContainer.getChildren().remove(facebook_icon);
        if (!telegram_icon.isVisible())
            contactContainer.getChildren().remove(telegram_icon);
        if (!mail_icon.isVisible())
            contactContainer.getChildren().remove(mail_icon);
        if (!phone_icon.isVisible())
            contactContainer.getChildren().remove(phone_icon);
        if (!web_icon.isVisible())
            contactContainer.getChildren().remove(web_icon);
    }

    public void switchPrevImage() {
        if (imageIndex == 0)
            imageIndex = images.size();
        imageIndex--;
        imageView.setImage(images.get(imageIndex));
    }

    public void switchNextImage() {
        if (imageIndex == images.size() - 1)
            imageIndex = -1;
        imageIndex++;
        imageView.setImage(images.get(imageIndex));
    }

    public void showSource(ActionEvent event) {
        Button btn = (Button) event.getSource();
        message.setText(btn.getId());
        messageLayout.setVisible(true);
    }

    public void closeMessageLayout() {
        message.setText("");
        messageLayout.setVisible(false);
    }

    public void addComment(ActionEvent event) {
        Comment comment = new Comment();
        comment.setGuest(new Guest("", "", "Guest"));
        comment.setWrittenDate(new Date(LocalDateTime.now().getDayOfWeek().getValue(), LocalDateTime.now().getMonth().getValue(), LocalDateTime.now().getYear()));
        comment.setComment(commentText.getText());

        VBox item = new VBox();
        Label username = new Label(comment.getGuest().getUsername());
        username.setStyle("-fx-font-size: 20; -fx-font-weight: bolder;");
        Label source = new Label(comment.getComment());
        source.setStyle("-fx-font-size: 18;");
        Label date = new Label(comment.getWrittenDate().toString());
        date.setStyle("-fx-font-size: 14; -fx-alignment: bottom-right");
        item.getChildren().add(username);
        item.getChildren().add(source);
        item.getChildren().add(date);
        commentContainer.getChildren().add(item);
        commentText.setText("");
        hotel.addComment(comment);

        SQLDataBase.editHotel(hotel);
        Tools.hotels.clear();
        SQLDataBase.loadHotels();
    }
}
