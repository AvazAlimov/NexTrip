package Activities;

import Classes.Client;
import Classes.Date;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminActivity implements Initializable{

    public GridPane mainPane;
    public GridPane addPane;
    public TextField username;
    public TextField password;
    public TextField credits;
    public DatePicker startDate;
    public DatePicker endDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void switchPane(ActionEvent event) {
        String text = ((Button)event.getSource()).getText();
        switch(text){
            case "Add Client":
                mainPane.setVisible(false);
                addPane.setVisible(true);
                break;
            case "Back":
                mainPane.setVisible(true);
                addPane.setVisible(false);
            default:
                break;
        }
    }

    public void addClient(ActionEvent event) throws SQLException {
        Client client = new Client();
        client.setUsername(username.getText());
        client.setPassword(password.getText());
        client.setNumberOfCredits(Integer.parseInt(credits.getText()));
        client.setStartDate(new Date(String.format("%02d/%02d/%04d", startDate.getValue().getDayOfMonth(), startDate.getValue().getMonthValue(), startDate.getValue().getYear())));
        client.setEndDate(new Date(String.format("%02d/%02d/%04d", endDate.getValue().getDayOfMonth(), endDate.getValue().getMonthValue(), endDate.getValue().getYear())));
        SQLDataBase.addClient(client);
    }
}
