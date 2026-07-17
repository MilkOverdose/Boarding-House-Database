package com.example.boardinghouse;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    private ComboBox<String> cbRole;
    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Label lblMessage;
    boolean success;

    public void onSignInClicked() throws IOException {
        boolean success = false;
        String username = tfUsername.getText();
        String password = pfPassword.getText();

        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement stmt = c.prepareStatement("INSERT INTO users (name,password) VALUES (?,?);")
        ) {
            stmt.setString(1, username);
            stmt.setString(3, password);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println(rowsUpdated + " rows updated");
                success = true;
            } else {
                System.out.println("Failed to insert user");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        if (!success) {
            lblMessage.setText("Invalid username or password");
        } else {
            lblMessage.setText("Successfully logged in");
            tfUsername.clear();
            pfPassword.clear();
        }
    }


    public void onGoToLoginClicked() {
        switchScene("login-view.fxml", "Login");
    }

    private void switchScene(String fxmlFile, String title) {
        try {
            Stage stage = (Stage) tfUsername.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
            lblMessage.setText("Error loading login screen.");
        }

    }
}