package org.example.View.FXMLControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import org.example.Model.Maze;
import org.example.Model.MazeGenerator.MazeGenerator;
import org.example.View.SceneFactory;
import org.example.View.View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLController {

    public void startScene(ActionEvent actionEvent) throws IOException {
        Scene scene = SceneFactory.startScene();
        View.primaryStage.setScene(scene);
    }

    public void mainScene(ActionEvent actionEvent) throws IOException {
        Scene scene = SceneFactory.mainScene();

        View.primaryStage.setScene(scene);
    }

    public void infoScene(ActionEvent actionEvent) throws IOException {
        Scene scene = SceneFactory.infoScene();
        View.primaryStage.setScene(scene);
    }
}
