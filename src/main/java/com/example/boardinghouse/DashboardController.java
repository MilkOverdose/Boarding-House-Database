package com.example.boardinghouse;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML private Button btnManageTenants; // optional, only needed if you reference it elsewhere

    @FXML
    public void initialize() {
        // Placeholder for future startup logic (e.g. loading stats)
    }

    @FXML
    public void onManageTenantsClicked() {
        switchScene("tenant-view.fxml", "Manage Tenants");
    }

    @FXML
    public void onManageRoomsClicked() {
        switchScene("room-view.fxml", "Manage Rooms");
    }

    @FXML
    public void onManagePaymentsClicked() {
        switchScene("payment-view.fxml", "Manage Payments");
    }

    @FXML
    public void onLogoutClicked() {
        switchScene("login-view.fxml", "Owner Login");
    }

    // Reusable helper method for switching screens
    private void switchScene(String fxmlFile, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) btnManageTenants.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(title);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}