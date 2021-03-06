package Activities;

import Classes.Client;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@SuppressWarnings("Duplicates")
public class SignInActivity implements Initializable {
    public Button continueButton;
    public Button signInButton;
    public TextField usernameText;
    public PasswordField passwordText;
    public Button actionButton;
    public GridPane firstGrid;
    public GridPane secondGrid;

    private Timeline firstAnimation, secondAnimation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void doAction(ActionEvent event) throws IOException {
        String id = ((Control) event.getSource()).getId();
        Parent parent;
        Scene scene;
        switch (id) {
            case "back":
                parent = FXMLLoader.load(getClass().getResource("../FXML/MainWindow.fxml"));
                scene = new Scene(parent);
                Main.stage.hide();
                Main.stage.setScene(scene);
                Main.stage.show();
                break;
            case "signIn":
                animate(true);
                actionButton.setText("Sign In");
                break;
            case "action":
                switch (actionButton.getText()) {
                    case "Sign In":
                        if (usernameText.getText().equals("admin") && passwordText.getText().equals("admin")) {
                            parent = FXMLLoader.load(getClass().getResource("../FXML/AdminWindow.fxml"));
                        } else {
                            Client client = Tools.logInClient(usernameText.getText(), passwordText.getText());
                            if (client == null)
                                return;
                            Tools.client = client;
                            parent = FXMLLoader.load(getClass().getResource("../FXML/ClientWindow.fxml"));
                        }

                        if (parent == null)
                            return;
                        scene = new Scene(parent);
                        Main.stage.hide();
                        Main.stage.setScene(scene);
                        Main.stage.show();
                        break;
                    default:
                        break;
                }
                break;
            case "cancelButton":
                animate(false);
                break;
            default:
                break;
        }
    }

    private void animate(boolean isForward) {
        firstAnimation = new Timeline(new KeyFrame(new Duration(10), actionEvent -> fadeButtons(isForward)));
        secondAnimation = new Timeline(new KeyFrame(new Duration(10), actionEvent -> showButtons(isForward)));
        firstAnimation.setCycleCount(Animation.INDEFINITE);
        secondAnimation.setCycleCount(Animation.INDEFINITE);
        firstAnimation.stop();
        secondAnimation.stop();
        firstAnimation.play();
    }

    private void showButtons(boolean isForward) {
        if (isForward) {
            if (secondGrid.getOpacity() > 1.0) {
                secondAnimation.stop();
            }
            double opacity = secondGrid.getOpacity() + 0.1;
            secondGrid.setOpacity(opacity);
        } else {
            if (firstGrid.getOpacity() > 1.0)
                secondAnimation.stop();
            double opacity = firstGrid.getOpacity() + 0.1;
            firstGrid.setOpacity(opacity);
        }
    }

    private void fadeButtons(boolean isForward) {
        if (isForward) {
            double opacity = firstGrid.getOpacity() - 0.1;
            firstGrid.setOpacity(opacity);
            if (firstGrid.getOpacity() < 0.0) {
                firstGrid.setVisible(false);
                secondGrid.setVisible(true);
                firstAnimation.stop();
                secondAnimation.play();
            }
        } else {
            double opacity = secondGrid.getOpacity() - 0.1;
            secondGrid.setOpacity(opacity);
            if (secondGrid.getOpacity() < 0.0) {
                firstGrid.setVisible(true);
                secondGrid.setVisible(false);
                firstAnimation.stop();
                secondAnimation.play();
            }
        }
    }

    public void enterAction(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            Parent parent;
            Scene scene;
            switch (actionButton.getText()) {
                case "Sign In":
                    if (usernameText.getText().equals("admin") && passwordText.getText().equals("admin")) {
                        parent = FXMLLoader.load(getClass().getResource("../FXML/AdminWindow.fxml"));
                    } else {
                        Client client = Tools.logInClient(usernameText.getText(), passwordText.getText());
                        if (client == null)
                            return;
                        Tools.client = client;
                        parent = FXMLLoader.load(getClass().getResource("../FXML/ClientWindow.fxml"));
                    }

                    if (parent == null)
                        return;
                    scene = new Scene(parent);
                    Main.stage.hide();
                    Main.stage.setScene(scene);
                    Main.stage.show();
                    break;
                default:
                    break;
            }
        }
    }
}
