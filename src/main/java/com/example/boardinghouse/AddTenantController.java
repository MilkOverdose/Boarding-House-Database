package com.example.boardinghouse;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddTenantController {

    @FXML private TextField tfName;
    @FXML private TextField tfAge;
    @FXML private TextField tfContact;
    @FXML private TextField tfRoomNum;
    @FXML private Label lblMessage;
    private IDatabaseConnection db = new MySQLConnection();

    @FXML
    public void onAddTenantClicked() {
        String name = tfName.getText();
        String contact = tfContact.getText();

        if (name.isEmpty() || tfAge.getText().isEmpty() || tfRoomNum.getText().isEmpty()) {
            lblMessage.setStyle("-fx-text-fill: red;");
            lblMessage.setText("Please fill in all fields.");
            return;
        }

        int age;
        int roomNum;
        try {
            age = Integer.parseInt(tfAge.getText());
            roomNum = Integer.parseInt(tfRoomNum.getText());
        } catch (NumberFormatException e) {
            lblMessage.setStyle("-fx-text-fill: red;");
            lblMessage.setText("Age and Room Number must be numbers.");
            return;
        }

        String query = "INSERT INTO tenants (name, age, contact, room_num) VALUES (?, ?, ?, ?)";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, contact);
            stmt.setInt(4, roomNum);
            stmt.executeUpdate();

            lblMessage.setStyle("-fx-text-fill: green;");
            lblMessage.setText("Tenant added successfully!");

            // Clear fields after successful add
            tfName.clear();
            tfAge.clear();
            tfContact.clear();
            tfRoomNum.clear();

        } catch (SQLException e) {
            lblMessage.setStyle("-fx-text-fill: red;");
            lblMessage.setText("Error adding tenant: " + e.getMessage());
        }
    }

    @FXML
    public void onBackClicked() {
        try {
            Stage stage = (Stage) tfName.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("owner-dashboard-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setTitle("Owner Dashboard");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}