package src.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import src.models.Client;
import src.models.Equipment;
import src.models.Rental;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RentalController implements Initializable {

    @FXML private ComboBox<Client> clientComboBox;
    @FXML private ComboBox<Equipment> equipmentComboBox;
    @FXML private DatePicker rentalDatePicker;
    @FXML private DatePicker returnDatePicker;
    @FXML private TableView<Rental> rentalTable;
    @FXML private TableColumn<Rental, String> idColumn;
    @FXML private TableColumn<Rental, String> clientColumn;
    @FXML private TableColumn<Rental, String> equipmentColumn;
    @FXML private TableColumn<Rental, LocalDate> rentalDateColumn;
    @FXML private TableColumn<Rental, LocalDate> returnDateColumn;
    @FXML private TableColumn<Rental, Double> costColumn;
    @FXML private TableColumn<Rental, String> statusColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Populate combo boxes
        clientComboBox.setItems(MainController.clientList);
        updateEquipmentComboBox();

        // Setup table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
        equipmentColumn.setCellValueFactory(new PropertyValueFactory<>("equipment"));
        rentalDateColumn.setCellValueFactory(new PropertyValueFactory<>("rentalDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusText"));

        rentalTable.setItems(MainController.rentalList);
    }

    private void updateEquipmentComboBox() {
        ObservableList<Equipment> available = FXCollections.observableArrayList(
                MainController.equipmentList.stream()
                        .filter(Equipment::isAvailable)
                        .collect(Collectors.toList())
        );
        equipmentComboBox.setItems(available);
    }

    @FXML
    private void createRental() {
        Client selectedClient = clientComboBox.getSelectionModel().getSelectedItem();
        Equipment selectedEquipment = equipmentComboBox.getSelectionModel().getSelectedItem();
        LocalDate rentalDate = rentalDatePicker.getValue();
        LocalDate returnDate = returnDatePicker.getValue();

        if(selectedClient == null || selectedEquipment == null || rentalDate == null || returnDate == null) {
            showAlert("Please fill all fields");
            return;
        }
        if(returnDate.isBefore(rentalDate) || returnDate.isEqual(rentalDate)) {
            showAlert("Return date must be after rental date");
            return;
        }

        Rental newRental = new Rental(selectedClient, selectedEquipment, rentalDate, returnDate);
        MainController.rentalList.add(newRental);
        updateEquipmentComboBox();
    }

    @FXML
    private void returnEquipment() {
        Rental selectedRental = rentalTable.getSelectionModel().getSelectedItem();
        if(selectedRental == null) {
            showAlert("Please select a rental to return");
            return;
        }
        if(selectedRental.isReturned()){
            showAlert("Equipment already returned");
            return;
        }
        selectedRental.returnEquipment();
        rentalTable.refresh();
        updateEquipmentComboBox();
    }

    @FXML
    private void backToMain() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) clientComboBox.getScene().getWindow();
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
