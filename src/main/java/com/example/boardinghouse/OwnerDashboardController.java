package com.example.boardinghouse;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OwnerDashboardController {

    @FXML private Button btnManageTenants;

    @FXML private Label lblTotalTenants;
    @FXML private Label lblAvailableRooms;
    @FXML private Label lblOccupiedRooms;
    @FXML private Label lblPendingPayments;
    private IDatabaseConnection db = new MySQLConnection();

    @FXML
    public void initialize() {
        if (!SessionManager.isLoggedIn()) {
            // No valid session — force back to login
            redirectToLogin();
            return;
        }

        Session current = SessionManager.getSession();
        System.out.println("Logged in as: " + current.getUsername() + " (" + current.getRole() + ")");

        loadStats();
    }

    private void loadStats() {
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement()) {

            // Total tenants
            ResultSet rs1 = stmt.executeQuery("SELECT COUNT(*) AS total FROM tenants");
            if (rs1.next()) lblTotalTenants.setText(String.valueOf(rs1.getInt("total")));

            // Available rooms
            ResultSet rs2 = stmt.executeQuery("SELECT COUNT(*) AS total FROM rooms WHERE status = 'Available'");
            if (rs2.next()) lblAvailableRooms.setText(String.valueOf(rs2.getInt("total")));

            // Occupied rooms
            ResultSet rs3 = stmt.executeQuery("SELECT COUNT(*) AS total FROM rooms WHERE status = 'Occupied'");
            if (rs3.next()) lblOccupiedRooms.setText(String.valueOf(rs3.getInt("total")));

            // Pending payments
            ResultSet rs4 = stmt.executeQuery("SELECT COUNT(*) AS total FROM payments WHERE status = 'Pending'");
            if (rs4.next()) lblPendingPayments.setText(String.valueOf(rs4.getInt("total")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onManageTenantsClicked() {
        NavigationFacade.switchScene(btnManageTenants, "tenant-view.fxml", "Manage Tenants");
    }

    @FXML
    public void onManageRoomsClicked() {
        NavigationFacade.switchScene(btnManageTenants, "room-view.fxml", "Manage Rooms");
    }

    @FXML
    public void onManagePaymentsClicked() {
        NavigationFacade.switchScene(btnManageTenants, "payment-view.fxml", "Manage Payments");
    }

    @FXML
    public void onManageCaretakersClicked() {
        NavigationFacade.switchScene(btnManageTenants, "caretaker-view.fxml", "Manage Caretakers");
    }

    @FXML
    public void onLogoutClicked() {
        SessionManager.clearSession();
        NavigationFacade.switchScene(btnManageTenants, "login-view.fxml", "Login");
    }

    private void redirectToLogin() {
        try {
            Stage stage = (Stage) btnManageTenants.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchScene(String fxmlFile, String title) {
        try {
            Stage stage = (Stage) btnManageTenants.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}