module com.example.boardinghouse {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.example.boardinghouse to javafx.fxml;
    exports com.example.boardinghouse;
}