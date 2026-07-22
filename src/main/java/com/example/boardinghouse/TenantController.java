package com.example.boardinghouse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class TenantController {

    @FXML private TextField tfName;
    @FXML private TextField tfAge;
    @FXML private TextField tfContact;
    @FXML private TextField tfRoomNum;
    @FXML private Label lblMessage;
    @FXML private Button btnBack;

    @FXML private TableView<Tenant> tenantTable;
    @FXML private TableColumn<Tenant, Integer> colID;
    @FXML private TableColumn<Tenant, String> colName;
    @FXML private TableColumn<Tenant, Integer> colAge;
    @FXML private TableColumn<Tenant, String> colContact;
    @FXML private TableColumn<Tenant, Integer> colRoomNum;

    private ObservableList<Tenant> tenantList = FXCollections.observableArrayList();
    private Tenant selectedTenant = null;
    private IDatabaseConnection db = new MySQLConnection();

    @FXML
    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("tenantID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colRoomNum.setCellValueFactory(new PropertyValueFactory<>("roomNum"));

        tenantTable.setItems(tenantList);
        loadTenants();

        // READ (part 2) — clicking a row fills the form for editing
        tenantTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedTenant = newVal;
                tfName.setText(newVal.getName());
                tfAge.setText(String.valueOf(newVal.getAge()));
                tfContact.setText(newVal.getContact());
                tfRoomNum.setText(String.valueOf(newVal.getRoomNum()));
            }
        });
    }

    // READ — loads all tenants from DB into the table
    private void loadTenants() {
        tenantList.clear();
        String query = "SELECT * FROM tenants";
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Tenant t = new TenantBuilder()
                        .setName(rs.getString("name"))
                        .setAge(rs.getInt("age"))
                        .setTenantID(rs.getInt("id"))
                        .setRoomNum(rs.getInt("room_num"))
                        .setContact(rs.getString("contact"))
                        .build();
                tenantList.add(t);
            }
        } catch (SQLException e) {
            lblMessage.setStyle("-fx-text-fill: red;");
            lblMessage.setText("Error loading tenants: " + e.getMessage());
        }
    }

    // CREATE
    @FXML
    public void onAddTenantClicked() {
        String name = tfName.getText();
        String contact = tfContact.getText();

        if (name.isEmpty() || tfAge.getText().isEmpty() || tfRoomNum.getText().isEmpty()) {
            lblMessage.setStyle("-fx-text-fill: red;");
            lblMessage.setText("Please fill in all fields.");
            return;
        }

        try {
            int age = Integer.parseInt(tfAge.getText());
            int roomNum = Integer.parseInt(tfRoomNum.getText());

            String query = "INSERT INTO tenants (name, age, contact, room_num) VALUES (?, ?, ?, ?)";
            try (Connection conn = db.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, name);
                stmt.setInt(2, age);
                stmt.setString(3, contact);
                stmt.setInt(4, roomNum);
                stmt.executeUpdate();

                lblMessage.setStyle("-fx-text-fill: green;");
                lblMessage.setText("Tenant added successfully.");
                loadTenants();
                clearForm();
            }
        } catch (NumberFormatException e) {
            lblMessage.setStyle("-fx-text-fill: red;");
            lblMessage.setText("Age and Room Number must be numbers.");
        } catch (SQLException e) {
            lblMessage.setStyle("-fx-text-fill: red;");
            lblMessage.setText("Database error: " + e.getMessage());
        }
    }

    // UPDATE
    @FXML
    public void onUpdateTenantClicked() {
        if (selectedTenant == null) {
            lblMessage.setStyle("-fx-text-fill: red;");
            lblMessage.setText("Please select a tenant to update.");
            return;
        }

        try {
            String name = tfName.getText();
            int age = Integer.parseInt(tfAge.getText());
            String contact = tfContact.getText();
            int roomNum = Integer.parseInt(tfRoomNum.getText());

            String query = "UPDATE tenants SET name = ?, age = ?, contact = ?, room_num = ? WHERE id = ?";
            try (Connection conn = db.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, name);
                stmt.setInt(2, age);
                stmt.setString(3, contact);
                stmt.setInt(4, roomNum);
                stmt.setInt(5, selectedTenant.getTenantID());
                stmt.executeUpdate();

                lblMessage.setStyle("-fx-text-fill: green;");
                lblMessage.setText("Tenant updated successfully.");
                loadTenants();
                clearForm();
            }
        } catch (NumberFormatException e) {
            lblMessage.setStyle("-fx-text-fill: red;");
            lblMessage.setText("Age and Room Number must be numbers.");
        } catch (SQLException e) {
            lblMessage.setStyle("-fx-text-fill: red;");
            lblMessage.setText("Update failed: " + e.getMessage());
        }
    }

    // DELETE
    @FXML
    public void onDeleteTenantClicked() {
        Tenant selected = tenantTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            lblMessage.setStyle("-fx-text-fill: red;");
            lblMessage.setText("Please select a tenant to delete.");
            return;
        }

        String query = "DELETE FROM tenants WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, selected.getTenantID());
            stmt.executeUpdate();

            lblMessage.setStyle("-fx-text-fill: green;");
            lblMessage.setText("Tenant deleted.");
            loadTenants();
            clearForm();
        } catch (SQLException e) {
            lblMessage.setStyle("-fx-text-fill: red;");
            lblMessage.setText("Delete failed: " + e.getMessage());
        }
    }

    @FXML
    public void onClearFormClicked() {
        clearForm();
    }

    private void clearForm() {
        tfName.clear();
        tfAge.clear();
        tfContact.clear();
        tfRoomNum.clear();
        selectedTenant = null;
        tenantTable.getSelectionModel().clearSelection();
    }

    @FXML
    public void onBackToDashboardClicked() {
        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("owner-dashboard-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setTitle("Owner Dashboard");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}