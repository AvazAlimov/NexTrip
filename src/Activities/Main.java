package Activities;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Locale;

public class Main extends Application {
    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale.setDefault(Locale.ENGLISH);
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/StartWindow.fxml"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        SQLDataBase.disconnect();
        super.stop();
    }

    public static void main(String[] args) {
        initialize();

        launch(args);
    }

    private static void initialize() {
        Tools.init();
        SQLDataBase.connect();
        SQLDataBase.loadClients();
        SQLDataBase.loadHotels();
        SQLDataBase.loadRestaurant();
        SQLDataBase.loadEntertaining();
        SQLDataBase.loadThingsToDo();
    }
}