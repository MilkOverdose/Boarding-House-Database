package com.example.boardinghouse;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigationFacade {

    public static void switchScene(Node currentNode, String fxmlFile, String title) {
        try {
            Stage stage = (Stage) currentNode.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(NavigationFacade.class.getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}