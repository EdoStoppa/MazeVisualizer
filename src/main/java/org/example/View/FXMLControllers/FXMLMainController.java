package org.example.View.FXMLControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;
import org.example.Model.Maze;
import org.example.Model.MazeGenerator.MazeGenerator;
import org.example.View.MazeView.MazePane;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLMainController implements Initializable {
    ObservableList<MazeGenerator> genList = FXCollections.observableArrayList(Maze.getAllGenerators());

    @FXML private Spinner<Integer> spinnerDim;
    @FXML private ChoiceBox<MazeGenerator> choiceGen;
    @FXML private Pane mazePaneBox;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        mazePaneBox.getChildren().add(new MazePane());
        spinnerDim.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 100, 20));
        choiceGen.setItems(genList);
    }

}
