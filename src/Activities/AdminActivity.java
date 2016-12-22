package Activities;

import Classes.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@SuppressWarnings("Duplicates")
public class AdminActivity implements Initializable {
    public GridPane mainPane;
    public GridPane addPane;
    public TextField username;
    public TextField password;
    public TextField credits;
    public DatePicker startDate;
    public DatePicker endDate;
    public Label dateError;
    public TextField search;
    public GridPane searchPane;
    public Button searchButton;
    public Label searchError;
    public Button doButton;
    public GridPane removePane;
    public ComboBox<String> objectSwitcher;
    public VBox container;
    public Button backButton;
    private Client client;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addPane.setStyle("-fx-background-image: url('/Resources/main_background.jpg');");
        objectSwitcher.getItems().add("Hotels");
        objectSwitcher.getItems().add("Restaurants");
        objectSwitcher.getItems().add("Entertaining");
        objectSwitcher.getItems().add("Things To Do");
    }

    public void switchPane(ActionEvent event) throws IOException {
        String text = ((Button) event.getSource()).getText();
        switch (text) {
            case "Add Client":
                mainPane.setVisible(false);
                searchPane.setVisible(false);
                addPane.setVisible(true);
                doButton.setId("add");
                doButton.setText("Add");
                break;
            case "Edit Client":
                searchButton.setId("edit");
                searchPane.setVisible(true);
                mainPane.setVisible(false);
                addPane.setVisible(false);
                doButton.setId("edit");
                doButton.setText("Edit");
                break;
            case "Remove Client":
                searchButton.setId("remove");
                searchPane.setVisible(true);
                mainPane.setVisible(false);
                addPane.setVisible(false);
                doButton.setId("remove");
                doButton.setText("Remove");
                break;
            case "Remove Object":
                mainPane.setVisible(false);
                removePane.setVisible(true);
                backButton.setText("Back");
                break;
            case "Back":
                removePane.setVisible(false);
                mainPane.setVisible(true);
                backButton.setText("Log out");
            case "Cancel":
                searchPane.setVisible(false);
                mainPane.setVisible(true);
                addPane.setVisible(false);
                break;
            case "Log out":
                Parent parent = FXMLLoader.load(getClass().getResource("../FXML/MainWindow.fxml"));
                Scene scene = new Scene(parent);
                Main.stage.hide();
                Main.stage.setScene(scene);
                Main.stage.show();
            default:
                break;
        }
    }

    private void addClient() {
        if (!checkAddFilled())
            return;

        if (endDate.getValue().isBefore(startDate.getValue())) {
            dateError.setText("Start date must be before expiry date");
            return;
        }

        Client client = new Client();
        client.setUsername(username.getText());
        client.setPassword(password.getText());

        try {
            client.setNumberOfCredits(Integer.parseInt(credits.getText()));
        } catch (Exception e) {
            credits.setText("1");
            client.setNumberOfCredits(Integer.parseInt(credits.getText()));
        }

        client.setStartDate(new Date(String.format("%02d/%02d/%04d", startDate.getValue().getDayOfMonth(), startDate.getValue().getMonthValue(), startDate.getValue().getYear())));
        client.setEndDate(new Date(String.format("%02d/%02d/%04d", endDate.getValue().getDayOfMonth(), endDate.getValue().getMonthValue(), endDate.getValue().getYear())));
        SQLDataBase.addClient(client);
        Tools.clients.clear();
        SQLDataBase.loadClients();

        searchPane.setVisible(false);
        mainPane.setVisible(true);
        addPane.setVisible(false);
    }

    private void editClient() {
        if (!checkAddFilled())
            return;

        if (endDate.getValue().isBefore(startDate.getValue())) {
            dateError.setText("Start date must be before expiry date");
            return;
        }

        Client client = new Client();
        client.setUsername(username.getText());
        client.setPassword(password.getText());

        try {
            client.setNumberOfCredits(Integer.parseInt(credits.getText()));
        } catch (Exception e) {
            credits.setText("1");
            client.setNumberOfCredits(Integer.parseInt(credits.getText()));
        }

        client.setStartDate(new Date(String.format("%02d/%02d/%04d", startDate.getValue().getDayOfMonth(), startDate.getValue().getMonthValue(), startDate.getValue().getYear())));
        client.setEndDate(new Date(String.format("%02d/%02d/%04d", endDate.getValue().getDayOfMonth(), endDate.getValue().getMonthValue(), endDate.getValue().getYear())));
        SQLDataBase.editClient(client, this.client.getUsername());
        Tools.clients.clear();
        SQLDataBase.loadClients();

        searchPane.setVisible(false);
        mainPane.setVisible(true);
        addPane.setVisible(false);
    }

    private boolean checkAddFilled() {
        boolean isFilled = true;

        if (username.getText().isEmpty()) {
            username.setStyle("-fx-background-color: #ff6f71; -fx-prompt-text-fill: white;");
            isFilled = false;
        }
        if (password.getText().isEmpty()) {
            password.setStyle("-fx-background-color: #ff6f71; -fx-prompt-text-fill: white;");
            isFilled = false;
        }
        if (credits.getText().isEmpty()) {
            credits.setStyle("-fx-background-color: #ff6f71; -fx-prompt-text-fill: white;");
            isFilled = false;
        }
        if (startDate.getValue() == null) {
            startDate.setStyle("-fx-background-color: #ff6f71; -fx-prompt-text-fill: white;");
            isFilled = false;
        }
        if (endDate.getValue() == null) {
            endDate.setStyle("-fx-background-color: #ff6f71; -fx-prompt-text-fill: white;");
            isFilled = false;
        }
        return isFilled;
    }

    public void keyPressed(KeyEvent keyEvent) {
        ((Control) keyEvent.getSource()).setStyle("-fx-background-color: white; -fx-prompt-text-fill: black;");
    }

    public void datePressed(MouseEvent mouseEvent) {
        dateError.setText("");
        ((Control) mouseEvent.getSource()).setStyle("-fx-background-color: white; -fx-prompt-text-fill: black;");
    }

    public void updateCredits(MouseEvent mouseEvent) {
        int counter = 0;
        try {
            counter = Integer.parseInt(credits.getText());
        } catch (Exception e) {
            credits.setText((counter + 1) + "");
            counter = 1;
        }

        switch (((Button) mouseEvent.getSource()).getId()) {
            case "up":
                credits.setText((counter + 1) + "");
                break;
            case "down":
                if (counter > 1)
                    credits.setText((counter - 1) + "");
                break;
        }
    }

    public void searchClient() {
        client = Tools.searchClient(search.getText());
        if (client == null) {
            searchError.setText("Client with this username was not found");
            searchError.setStyle("-fx-text-fill: white; -fx-font-size: 18;");
            return;
        }
        username.setText(client.getUsername());
        password.setText(client.getPassword());
        credits.setText(client.getNumberOfCredits() + "");
        startDate.setValue(LocalDate.of(client.getStartDate().getYear(), client.getStartDate().getMonth(), client.getStartDate().getDay()));
        endDate.setValue(LocalDate.of(client.getEndDate().getYear(), client.getEndDate().getMonth(), client.getEndDate().getDay()));
        mainPane.setVisible(false);
        searchPane.setVisible(false);
        addPane.setVisible(true);
    }

    public void searchTyped() {
        searchError.setText("");
    }

    public void doAction() {
        switch (doButton.getId()) {
            case "add":
                addClient();
                break;
            case "edit":
                editClient();
                break;
            case "remove":
                SQLDataBase.removeClient(client.getUsername());
                Tools.clients.remove(client);
                break;
        }
    }

    public void refreshContainer() {
        container.getChildren().clear();
        switch(objectSwitcher.getSelectionModel().getSelectedItem()){
            case "Hotels":
                for(Hotel hotel : Tools.hotels)
                    container.getChildren().add(fillHotelItem(hotel));
                break;
            case "Restaurants":
                for(Restaurant restaurant : Tools.restaurants)
                    container.getChildren().add(fillRestaurantItem(restaurant));
                break;
            case "Entertaining":
                for (Entertaining entertaining : Tools.entertainings)
                    container.getChildren().add(fillEntertainingItem(entertaining));
                break;
            case "Things To Do":
                for(ThingsToDo thingsToDo : Tools.thingsToDos)
                    container.getChildren().add(fillThingsToDoItem(thingsToDo));
                break;
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
            SQLDataBase.deleteHotel(hotel.getId() + "");
            Tools.hotels.clear();
            SQLDataBase.loadHotels();
            refreshContainer();
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
            SQLDataBase.deleteRestaurant(restaurant.getId() + "");
            Tools.restaurants.clear();
            SQLDataBase.loadRestaurant();
            refreshContainer();
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
            SQLDataBase.deleteThingsToDo(thingsToDo.getId() + "");
            Tools.thingsToDos.clear();
            SQLDataBase.loadThingsToDo();
            refreshContainer();
        });

        return item;
    }

    private GridPane fillEntertainingItem(Entertaining entertaining){
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
            SQLDataBase.deleteHotel(entertaining.getId() + "");
            Tools.entertainings.clear();
            SQLDataBase.loadEntertaining();
            refreshContainer();
        });

        return item;
    }
}
