package src.models;

import java.time.LocalDate;
import java.util.UUID;

public class Rental {
    private String id;
    private Client client;
    private Equipment equipment;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private double totalCost;
    private boolean isReturned;

    public Rental(Client client, Equipment equipment, LocalDate rentalDate, LocalDate returnDate) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.client = client;
        this.equipment = equipment;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.isReturned = false;

        long days = java.time.temporal.ChronoUnit.DAYS.between(rentalDate, returnDate);
        this.totalCost = days * equipment.getDailyRate();

        equipment.setAvailable(false);
    }

    public void returnEquipment() {
        this.isReturned = true;
        equipment.setAvailable(true);
    }

    // Getters for TableView
    public String getId() { return id; }
    public Client getClient() { return client; }
    public Equipment getEquipment() { return equipment; }
    public LocalDate getRentalDate() { return rentalDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public double getTotalCost() { return totalCost; }
    public boolean isReturned() { return isReturned; }
    public String getStatusText() {
        return isReturned ? "Returned" : "Active";
    }

    @Override
    public String toString() {
        return "Rental " + id + " - " + equipment.getName() + " to " + client.getName();
    }
}
