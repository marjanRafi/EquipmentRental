
# README – Equipment Rental Management System

## 📝 Project Summary

This is a JavaFX-based desktop application for managing clients, equipment, and rentals for an equipment rental service. Designed for academic and educational purposes, the project demonstrates key object-oriented programming concepts, GUI design with JavaFX, and database integration using JDBC and MySQL.

---

## 🎯 Key Features

- ✅ Add and manage **client** information
- ✅ Add and delete **equipment** with category and daily rate
- ✅ Create and process **rental transactions** between clients and equipment
- ✅ Return rented items and update availability
- ✅ Dynamic **dashboard** overview showing key statistics
- ✅ Form **validation** and alert dialogs for user guidance
- ✅ **MySQL integration** using JDBC for persistent data handling

---

## 🛠️ Technologies Used

- **Java 11+**
- **JavaFX SDK (21+)**
- **FXML**
- **MySQL** with **MySQL Connector/J (8.0+)**
- **IntelliJ IDEA** or compatible IDE

---

## 📁 Project Structure

```
src/
├── Main.java
├── controllers/
│   ├── MainController.java
│   ├── ClientController.java
│   ├── EquipmentController.java
│   └── RentalController.java
├── models/
│   ├── Client.java
│   ├── Equipment.java
│   └── Rental.java
├── database/
│   └── DBConnection.java
└── resources/
    ├── MainView.fxml
    ├── ClientView.fxml
    ├── EquipmentView.fxml
    └── RentalView.fxml
```

---

## 🗄️ Database Configuration

**Database Name:** `EquipmentRentalDB`

### Setup Instructions
1. Import the provided SQL file (`jdbc.sql`) into your MySQL database:
   ```sql
   SOURCE path/to/jdbc.sql;
   ```

2. Modify `DBConnection.java` with your database credentials:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/EquipmentRentalDB";
   private static final String USER = "root";
   private static final String PASSWORD = "your_password";
   ```

3. Run the test method in `DBConnection` to verify the connection.

---

## ▶️ How to Run the Application

1. Download and install **JavaFX SDK** and **MySQL Connector/J**.
2. Add both libraries to your project's **module path**.
3. Set VM options in IntelliJ (Run > Edit Configurations):
   ```
   --module-path "D:\javafx-sdk-21\lib" --add-modules javafx.controls,javafx.fxml
   ```
4. Compile and run `Main.java`.

5. To see the database connection, run DBConnection
