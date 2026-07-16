module com.example.boardinghouse {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.boardinghouse to javafx.fxml;
    exports com.example.boardinghouse;
}