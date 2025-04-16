package src.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import src.models.Client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController {
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TableView<Client> clientTable;
    @FXML private TableColumn<Client, String> nameCol;
    @FXML private TableColumn<Client, String> emailCol;
    @FXML private TableColumn<Client, String> phoneCol;

    private ObservableList<Client> localList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        localList.setAll(MainController.clientList);
        clientTable.setItems(localList);
    }

    @FXML
    private void addClient() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();

        if(name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            showAlert("All fields are required");
            return;
        }

        Client newClient = new Client(name, email, phone);
        MainController.clientList.add(newClient);
        localList.add(newClient);
        clearFields();
    }

    private void clearFields() {
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        nameField.requestFocus();
    }

    @FXML
    private void backToMain() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) clientTable.getScene().getWindow();
            stage.setTitle("Equipment Rental System");
            stage.setScene(new Scene(root));
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
