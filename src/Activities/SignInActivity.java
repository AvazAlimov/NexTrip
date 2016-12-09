package Activities;

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
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInActivity implements Initializable {
    public Button continueButton;
    public Button signInButton;
    public Button signUpButton;
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

        switch (id) {
            case "back":
                Parent parent = FXMLLoader.load(getClass().getResource("../FXML/MainWindow.fxml"));
                Scene scene = new Scene(parent);
                Main.stage.hide();
                Main.stage.setScene(scene);
                Main.stage.show();
                break;
            case "signIn":
                animate(true);
                actionButton.setText("Sign In");
                break;
            case "signUp":
                animate(true);
                actionButton.setText("Sign Up");
                break;
            case "action":
                //TODO: register and open main Window
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

    private void nextWindow() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("FXML/SignInWindow.fxml"));
        Scene scene = new Scene(parent);
        Main.stage.setScene(scene);
    }
}
