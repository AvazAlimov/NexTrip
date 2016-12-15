package Activities;

import Classes.Hotel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainActivity implements Initializable {
    public VBox container;
    public HBox datePane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Hotel hotel : Tools.hotels)
            container.getChildren().add(fillItem(hotel));
    }

    private GridPane fillItem(Hotel hotel) {
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
        if (url != null) {
            value = new Image(url.toString(), 100.0, 100.0, false, false);
        }

        ImageView image = new ImageView(value);
        Circle circle = new Circle(50.0);
        circle.setCenterX(50.0);
        circle.setCenterY(50.0);
        image.setClip(circle);
        item.add(image, 0, 0);

        Label info = new Label("Price: " + hotel.getStartingPrice() + " - " + hotel.getEndingPrice() + "\n" + hotel.getInfo());
        info.setStyle("-fx-font-size: 24; -fx-alignment: center-left;");
        item.add(info, 1, 0);

        HBox ratingBox = new HBox(5.0);
        ratingBox.setStyle("-fx-alignment: center;");

        for (int i = 0; i < hotel.getRating(); i++) {
            Button star = new Button();
            star.setPrefSize(40.0, 40.0);
            star.setStyle("-fx-shape: " + Tools.filledStar);
            star.setDisable(true);
            ratingBox.getChildren().add(star);
        }
        item.add(ratingBox, 2, 0);
        return item;
    }

    public void switchTabs(ActionEvent event) {
        Button btn = (Button) event.getSource();
        HBox box = (HBox) btn.getParent();
        for (Node item : box.getChildren())
            item.setStyle("-fx-background-color: #FFC107;");


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
            case "Settings":
                //TODO: settings window
                break;
        }
        btn.setStyle("-fx-background-color: #FFA000;");
    }
}
