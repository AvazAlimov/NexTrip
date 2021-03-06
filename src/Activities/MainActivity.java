package Activities;

import Classes.Entertaining;
import Classes.Hotel;
import Classes.Restaurant;
import Classes.ThingsToDo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@SuppressWarnings("Duplicates")
public class MainActivity implements Initializable {
    public VBox container;
    public HBox datePane;
    public ComboBox<String> searchText;
    public DatePicker startDate;
    public DatePicker endDate;
    private String choosenType = "Hotels";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void switchTabs(ActionEvent event) {
        Button btn = (Button) event.getSource();
        HBox box = (HBox) btn.getParent();
        for (Node item : box.getChildren())
            item.setStyle("-fx-background-color: #FFC107;");
        choosenType = btn.getText();
        container.getChildren().clear();
        switch (btn.getText()) {
            case "Hotels":
                datePane.setVisible(false);
                break;
            case "Restaurants":
                datePane.setVisible(false);
                break;
            case "Entertaining":
                datePane.setVisible(false);
                break;
            case "Things To Do":
                datePane.setVisible(true);
                break;
            case "Log In":
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("../FXML/SignInWindow.fxml"));
                    Scene scene = new Scene(parent);
                    Main.stage.hide();
                    Main.stage.setScene(scene);
                    Main.stage.show();
                } catch (IOException e) {
                    System.out.println("FXML file was not founded");
                }
                break;
        }
        btn.setStyle("-fx-background-color: #FFA000;");
    }

    public void searchObject() {
        String text = searchText.getEditor().getText();
        if (text.isEmpty())
            return;

        searchText.getItems().clear();

        switch (choosenType) {
            case "Hotels":
                for (Hotel hotel : Tools.hotels) {
                    if (Tools.contains(hotel.getLocation().toLowerCase(), text.toLowerCase())) {
                        searchText.getItems().add(hotel.getLocation());
                    }
                }
                break;
            case "Entertaining":
                for (Entertaining entertaining : Tools.entertainings)
                    if (Tools.contains(entertaining.getLocation().toLowerCase(), text.toLowerCase())) {
                        searchText.getItems().add(entertaining.getLocation());
                    }
                break;
            case "Restaurants":
                for (Restaurant restaurant : Tools.restaurants) {
                    if (Tools.contains(restaurant.getLocation().toLowerCase(), text.toLowerCase())) {
                        searchText.getItems().add(restaurant.getLocation());
                    }
                }
                break;
            case "Things To Do":
                try {
                    for (ThingsToDo thingsToDo : Tools.thingsToDos)
                        if (Tools.contains(thingsToDo.getLocation().toLowerCase(), text.toLowerCase()) && Tools.checkDate(startDate, endDate, thingsToDo))
                            searchText.getItems().add(thingsToDo.getLocation());

                } catch (Exception ignored) {
                }

                break;
            default:
                break;
        }
        searchText.show();
    }

    public void addObjects() {
        switch (choosenType) {
            case "Hotels":
                addHotels();
                break;
            case "Restaurants":
                addRestaurants();
                break;
            case "Entertaining":
                addEntertaining();
                break;
            case "Things To Do":
                try {
                    addThingsToDo();
                } catch (Exception ignored) {
                }
                break;
            default:
                break;
        }
    }

    private void addHotels() {
        ArrayList<Hotel> hotels = Tools.findHotel(searchText.getEditor().getText());
        container.getChildren().clear();
        for (Hotel hotel : hotels)
            container.getChildren().add(fillHotelItem(hotel));
    }

    private void addRestaurants() {
        ArrayList<Restaurant> restaurants = Tools.findRestaurants(searchText.getEditor().getText());
        container.getChildren().clear();
        for (Restaurant restaurant : restaurants)
            container.getChildren().add(fillRestaurantItem(restaurant));
    }

    private void addThingsToDo() {
        ArrayList<ThingsToDo> thingsToDos = Tools.findThingsToDo(searchText.getEditor().getText(), startDate, endDate);
        container.getChildren().clear();
        for (ThingsToDo thingsToDo : thingsToDos)
            container.getChildren().add(fillThingsToDoItem(thingsToDo));
    }

    private void addEntertaining() {
        ArrayList<Entertaining> entertainings = Tools.findEntertainings(searchText.getEditor().getText());
        container.getChildren().clear();
        for (Entertaining entertaining : entertainings)
            container.getChildren().add(fillEntertainingItem(entertaining));
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

        item.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Tools.hotel = hotel;
                    Parent parent = FXMLLoader.load(getClass().getResource("../FXML/HotelWindow.fxml"));
                    Scene scene = new Scene(parent);
                    Main.stage.hide();
                    Main.stage.setScene(scene);
                    Main.stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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

        item.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Tools.restaurant = restaurant;
                    Parent parent = FXMLLoader.load(getClass().getResource("../FXML/RestaurantWindow.fxml"));
                    Scene scene = new Scene(parent);
                    Main.stage.hide();
                    Main.stage.setScene(scene);
                    Main.stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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

        item.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Tools.thingsToDo = thingsToDo;
                    Parent parent = FXMLLoader.load(getClass().getResource("../FXML/ThingsToDoWindow.fxml"));
                    Scene scene = new Scene(parent);
                    Main.stage.hide();
                    Main.stage.setScene(scene);
                    Main.stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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

        item.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Tools.entertaining = entertaining;
                    Parent parent = FXMLLoader.load(getClass().getResource("../FXML/EntertainingWindow.fxml"));
                    Scene scene = new Scene(parent);
                    Main.stage.hide();
                    Main.stage.setScene(scene);
                    Main.stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return item;
    }
}

