package src.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import src.models.Equipment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EquipmentController implements Initializable {

    @FXML private TextField nameField;
    @FXML private TextField categoryField;
    @FXML private TextField rateField;
    @FXML private TableView<Equipment> equipmentTable;
    @FXML private TableColumn<Equipment, String> idColumn;
    @FXML private TableColumn<Equipment, String> nameColumn;
    @FXML private TableColumn<Equipment, String> categoryColumn;
    @FXML private TableColumn<Equipment, Double> rateColumn;
    @FXML private TableColumn<Equipment, Boolean> availableColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("dailyRate"));
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
        availableColumn.setCellFactory(column -> new TableCell<Equipment, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : (item ? "Yes" : "No"));
            }
        });
        equipmentTable.setItems(MainController.equipmentList);
    }

    @FXML
    private void addEquipment() {
        String name = nameField.getText().trim();
        String category = categoryField.getText().trim();
        String rateStr = rateField.getText().trim();

        if(name.isEmpty() || category.isEmpty() || rateStr.isEmpty()){
            showAlert("All fields are required");
            return;
        }
        double rate;
        try {
            rate = Double.parseDouble(rateStr);
            if(rate <= 0){
                showAlert("Daily rate must be greater than zero");
                return;
            }
        } catch (NumberFormatException e){
            showAlert("Daily rate must be a valid number");
            return;
        }
        Equipment newEquipment = new Equipment(name, category, rate);
        MainController.equipmentList.add(newEquipment);
        nameField.clear();
        categoryField.clear();
        rateField.clear();
        nameField.requestFocus();
    }

    @FXML
    private void deleteEquipment() {
        Equipment selected = equipmentTable.getSelectionModel().getSelectedItem();
        if(selected == null) {
            showAlert("Please select equipment to delete");
            return;
        }
        if(!selected.isAvailable()){
            showAlert("Cannot delete equipment that is currently rented");
            return;
        }
        MainController.equipmentList.remove(selected);
    }

    @FXML
    private void backToMain() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.setTitle("Equipment Rental System");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showAlert(String msg){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
