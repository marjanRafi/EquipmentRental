package src.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import src.models.Client;
import src.models.Equipment;
import src.models.Rental;

import java.io.IOException;

public class MainController {
    @FXML private Label totalClientsLabel;
    @FXML private Label totalEquipmentLabel;
    @FXML private Label activeRentalsLabel;

    // Shared observable lists for the entire app
    public static ObservableList<Client> clientList = FXCollections.observableArrayList();
    public static ObservableList<Equipment> equipmentList = FXCollections.observableArrayList();
    public static ObservableList<Rental> rentalList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // (Optionally add sample data)
        if (clientList.isEmpty() && equipmentList.isEmpty() && rentalList.isEmpty()) {
            clientList.add(new Client("John Doe", "john@example.com", "555-1234"));
            clientList.add(new Client("Jane Smith", "jane@example.com", "555-5678"));

            equipmentList.add(new Equipment("Lawn Mower", "Garden", 25.0));
            equipmentList.add(new Equipment("Pressure Washer", "Cleaning", 35.0));
            equipmentList.add(new Equipment("Drill", "Power Tools", 15.0));
        }
        updateDashboard();
    }

    public void updateDashboard() {
        totalClientsLabel.setText(String.valueOf(clientList.size()));
        totalEquipmentLabel.setText(String.valueOf(equipmentList.size()));
        long activeCount = rentalList.stream().filter(r -> !r.isReturned()).count();
        activeRentalsLabel.setText(String.valueOf(activeCount));
    }

    @FXML
    private void openClientView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClientView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) totalClientsLabel.getScene().getWindow();
            stage.setTitle("Client Management");
            stage.setScene(new Scene(root));
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openEquipmentView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EquipmentView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) totalClientsLabel.getScene().getWindow();
            stage.setTitle("Equipment Management");
            stage.setScene(new Scene(root));
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openRentalView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/RentalView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) totalClientsLabel.getScene().getWindow();
            stage.setTitle("Rental Management");
            stage.setScene(new Scene(root));
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
