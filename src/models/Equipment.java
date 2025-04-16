package src.models;

import java.util.UUID;

public class Equipment {
    private String id;
    private String name;
    private String category;
    private double dailyRate;
    private boolean isAvailable;

    public Equipment(String name, String category, double dailyRate) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.name = name;
        this.category = category;
        this.dailyRate = dailyRate;
        this.isAvailable = true;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getDailyRate() { return dailyRate; }
    public void setDailyRate(double dailyRate) { this.dailyRate = dailyRate; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return name + " (" + category + ") - $" + dailyRate + "/day";
    }
}
