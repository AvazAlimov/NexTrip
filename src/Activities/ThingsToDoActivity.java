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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

@SuppressWarnings("Duplicates")
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
    public ScrollPane mainLayout;
    public Label rulesText;
    public Label dateLabel;

    private ArrayList<Image> images;
    private int imageIndex;
    private ThingsToDo thingsToDo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        images = new ArrayList<>();
        imageIndex = 0;

        this.thingsToDo = Tools.thingsToDo;

        for (String path : thingsToDo.getPhotos()) {
            try {
                URL url = new File(path).toURI().toURL();
                images.add(new Image(url.toString()));
            } catch (MalformedURLException ignored) {
            }
        }

        dateLabel.setText(dateToString(thingsToDo.getStartDate()) + " - " + dateToString(thingsToDo.getEndDate()));

        if (!images.isEmpty())
            imageView.setImage(images.get(0));

        nameLabel.setText(thingsToDo.getName());
        locationLabel.setText(thingsToDo.getLocation());
        loadRating();
        infoText.setText(thingsToDo.getInfo());

        for (String amenity : thingsToDo.getAmenties()) {
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

        rulesText.setText(thingsToDo.getRules());

        for (Contact contact : thingsToDo.getContacts()) {
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
        }

        deleteNullContacts();

        thingsToDo.getComments().forEach(this::addCommentItem);
    }

    private String dateToString(Date date){
        return date.getDay() + " " + date.getMonthName() + " " + date.getYear();
    }

    private void loadRating() {
        for (int i = 0; i < thingsToDo.getRating(); i++) {
            stars.getChildren().get(i).setStyle("-fx-shape: " + Tools.filledStar + "; -fx-background-color: #FFC107;");
        }

        for (int i = 4; i >= thingsToDo.getRating(); i--)
            stars.getChildren().get(i).setStyle("-fx-shape: " + Tools.emptyStar + "; -fx-background-color: #FFC107;");

        numberRating.setText(thingsToDo.getRatings().size() + "");
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

    public void addComment() {
        if (commentText.getText().isEmpty())
            return;

        Comment comment = new Comment();
        comment.setGuest(new Guest("", "", "Guest"));
        comment.setWrittenDate(new Date(LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getMonth().getValue(), LocalDateTime.now().getYear()));
        comment.setComment(commentText.getText());
        addCommentItem(comment);
        thingsToDo.addComment(comment);
        SQLDataBase.editThingsToDo(thingsToDo);
        Tools.thingsToDos.clear();
        SQLDataBase.loadThingsToDo();
    }

    private void addCommentItem(Comment comment) {
        VBox item = new VBox();
        Label username = new Label(comment.getGuest().getUsername());
        username.setStyle("-fx-font-size: 20; -fx-font-weight: bolder; -fx-text-fill: #FFC107;");
        Label source = new Label(comment.getComment());
        source.setStyle("-fx-font-size: 20;");
        Label date = new Label(comment.getWrittenDate().toString());
        date.setStyle("-fx-font-size: 18; -fx-alignment: bottom-right");
        item.getChildren().add(username);
        item.getChildren().add(source);
        item.getChildren().add(date);
        item.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-padding: 10;");
        commentContainer.getChildren().add(item);
        commentText.setText("");
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

    public void backToMain() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../FXML/MainWindow.fxml"));
        Scene scene = new Scene(parent);
        Main.stage.hide();
        Main.stage.setScene(scene);
        Main.stage.show();
    }

    public void fillStars(MouseEvent mouseEvent) {
        int limit = Integer.parseInt(((Button) mouseEvent.getSource()).getId());

        for (int i = 0; i < limit; i++)
            stars.getChildren().get(i).setStyle("-fx-shape: " + Tools.filledStar + "; -fx-background-color: #FFC107;");

        for (int i = 4; i >= limit; i--)
            stars.getChildren().get(i).setStyle("-fx-shape: " + Tools.emptyStar + "; -fx-background-color: #FFC107;");
    }

    public void restoreStars() {
        loadRating();
    }

    public void closeMessageLayout() {
        message.setText("");
        messageLayout.setVisible(false);
    }

    public void rateThingsToDo(ActionEvent event) {
        int rating = Integer.parseInt(((Button) event.getSource()).getId());
        thingsToDo.addRating(rating);
        stars.setDisable(true);
        SQLDataBase.editThingsToDo(thingsToDo);
        Tools.thingsToDos.clear();
        SQLDataBase.loadThingsToDo();
    }

    public void showSource(ActionEvent event) {
        Button btn = (Button) event.getSource();
        message.setText(btn.getId());
        messageLayout.setVisible(true);
    }
}
