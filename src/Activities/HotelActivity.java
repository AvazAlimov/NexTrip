package Activities;


import Classes.Hotel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
    }

    public void switchPrevImage() {
        if(imageIndex == 0)
            imageIndex = images.size();
        imageIndex--;
        imageView.setImage(images.get(imageIndex));
    }

    public void switchNextImage() {
        if(imageIndex == images.size() - 1)
            imageIndex = -1;
        imageIndex++;
        imageView.setImage(images.get(imageIndex));
    }
}
