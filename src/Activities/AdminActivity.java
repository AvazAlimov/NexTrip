package Activities;

import Classes.Client;
import Classes.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

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
    private Client client;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addPane.setStyle("-fx-background-image: url('/Resources/main_background.jpg');");
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
        Tools.clients.add(client);

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
        Tools.clients.remove(this.client);
        Tools.clients.add(client);

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

        switch (((Rectangle) mouseEvent.getSource()).getId()) {
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
                System.out.println("Hello");
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

    //TODO: remove object
}
