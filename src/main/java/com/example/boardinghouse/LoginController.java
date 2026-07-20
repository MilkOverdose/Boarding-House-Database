package com.example.boardinghouse;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {
    static String username_login;
    public TextField tfUsername;
    public PasswordField pfPassword;
    public Label lblError;
    static boolean isDark = false;



    public void onLoginClicked() throws IOException {
        String username = tfUsername.getText();
        String password = pfPassword.getText();
        boolean success = false;
        String ownerQuery = "SELECT * FROM owners WHERE username = ? AND password = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(ownerQuery)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                success = true;
                lblError.setText("");

                int userID = rs.getInt("ownerId");
                Session session = new Session(userID, username, "Owner");
                SessionManager.createSession(session);

                switchScene("owner-dashboard-view.fxml", "Owner Dashboard");
                return;
            }

        } catch (SQLException e) {
            System.out.println("SQLException (owners): " + e.getMessage());
        }

        String caretakerQuery = "SELECT * FROM caretakers WHERE username = ? AND password = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(caretakerQuery)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                success = true;
                lblError.setText("");

                int userID = rs.getInt("id"); // assuming your owners/caretakers table has an "id" column
                Session session = new Session(userID, username, "Caretaker");
                SessionManager.createSession(session);

                switchScene("caretaker-dashboard-view.fxml", "Caretaker Dashboard");
                return;
            }

        } catch (SQLException e) {
            System.out.println("SQLException (caretakers): " + e.getMessage());
        }


        if (!success) {
            lblError.setText("Invalid username or password.");
        }
    }

    public void onPasswordKeyPressed(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            onLoginClicked();
        }
    }


    @FXML
    public void onGoToRegisterClicked() {
        switchScene("register-view.fxml", "Register");
    }

    private void switchScene(String fxmlFile, String title) {
        try {
            Stage stage = (Stage) tfUsername.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
            lblError.setText("Error loading screen.");
        }
    }
}
