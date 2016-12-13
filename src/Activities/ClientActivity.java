package Activities;

import Classes.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientActivity implements Initializable {
    public Label usernameText;
    public Button backButton;
    public GridPane addChoicePane;
    public GridPane objectPane;
    public GridPane mainPane;
    public TextField nameText;
    public TextField locationText;
    private Client client;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        client = Tools.client;
        usernameText.setText(client.getUsername());
    }


    public void switchPane(ActionEvent event) throws IOException {
        String text = ((Button) event.getSource()).getText();

        switch (text) {
            case "Log Out":
                Parent parent = FXMLLoader.load(getClass().getResource("../FXML/MainWindow.fxml"));
                Scene scene = new Scene(parent);
                Main.stage.hide();
                Main.stage.setScene(scene);
                Main.stage.show();
                break;
            case "Add Object":
                backButton.setText("Back");
                mainPane.setVisible(false);
                addChoicePane.setVisible(true);
                break;
            case "Back":
                if(objectPane.isVisible()){
                    objectPane.setVisible(false);
                    addChoicePane.setVisible(true);
                } else {
                    backButton.setText("Log Out");
                    addChoicePane.setVisible(false);
                    mainPane.setVisible(true);
                }
            default:
                break;
        }
    }

    public void switchObjectPane(ActionEvent event) {
        String text = ((Button) event.getSource()).getText();
        addChoicePane.setVisible(false);
        objectPane.setVisible(true);

        switch (text) {
            case "Hotel":
                break;
            case "Restaurant":
                break;
            case "Entertaining":
                break;
            case "Things To Do":
                break;
            default:
                break;
        }
    }
}