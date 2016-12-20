package Activities;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


@SuppressWarnings("Duplicates")
public class SettingsActivity implements Initializable{


    public Button imageButton;
    public Button imageButton2;
    public Button imageButton3;
    public Button imageButton4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void backToMenu(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../FXML/MainWindow.fxml"));
        Scene scene = new Scene(parent);
        Main.stage.hide();
        Main.stage.setScene(scene);
        Main.stage.show();
    }
}
