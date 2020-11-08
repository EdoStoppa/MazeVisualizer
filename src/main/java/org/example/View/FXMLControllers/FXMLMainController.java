package org.example.View.FXMLControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.example.Model.Maze;
import org.example.Model.MazeGenerator.MazeGenerator;
import org.example.Model.MazeSolver.MazeSolver;
import org.example.View.MazeView.MazePane;
import org.example.View.SceneFactory;
import org.example.View.View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLMainController implements Initializable {
    ObservableList<MazeGenerator> genList = FXCollections.observableArrayList(Maze.getAllGenerators());
    ObservableList<MazeSolver> solversList = FXCollections.observableArrayList(Maze.getAllSolvers());

    @FXML private VBox rightGen;
    @FXML private VBox rightSolve;

    @FXML private Spinner<Integer> startXSpinner;
    @FXML private Spinner<Integer> startYSpinner;
    @FXML private Spinner<Integer> endXSpinner;
    @FXML private Spinner<Integer> endYSpinner;
    @FXML private Spinner<Integer> spinnerDim;

    @FXML private ChoiceBox<MazeGenerator> choiceGen;
    @FXML private ChoiceBox<MazeSolver> choiceSolve;

    @FXML private Button genBtn;
    @FXML private Button goToSolveBtn;
    @FXML private Button solveBtn;
    @FXML private Button goToNewBtn;

    @FXML private Pane mazePaneBox;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        rightGen.setVisible(true);
        rightSolve.setVisible(false);

        View.mazePane = new MazePane(20, true);
        mazePaneBox.getChildren().add(View.mazePane);

        spinnerDim.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 50, 20));

        choiceGen.setItems(genList);
        choiceSolve.setItems(solversList);
    }

    public void launchCreation(ActionEvent actionEvent) {
        if(choiceGen.getValue() != null){
            // Set invisible the Generate Button
            genBtn.setVisible(false);
            goToSolveBtn.setVisible(false);

            // Create mew mazePane with correct dimension and correct walls values
            View.mazePane = new MazePane(spinnerDim.getValue(), choiceGen.getValue().wallSetup());

            mazePaneBox.getChildren().clear();
            mazePaneBox.getChildren().add(View.mazePane);

            // Start maze's creation
            View.controller.createMaze(spinnerDim.getValue(), choiceGen.getValue());

            // Re-enable genBtn + enable go to solve button
            genBtn.setVisible(true);
            goToSolveBtn.setVisible(true);
        }
    }
    public void goToSolve(ActionEvent actionEvent) {


        // Prepare new rightPane
        startXSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, spinnerDim.getValue(), 0));
        startYSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, spinnerDim.getValue(), 0));
        endXSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, spinnerDim.getValue()-1, spinnerDim.getValue()-1));
        endYSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, spinnerDim.getValue()-1, spinnerDim.getValue()-1));

        // Set visible correct buttons!
        solveBtn.setVisible(true);

        // Set visible new rightPane
        rightGen.setVisible(false);
        rightSolve.setVisible(true);

        View.mazePane.showStart();
        View.mazePane.showEnd();

    }

    public void launchSolving(ActionEvent actionEvent){
        if(choiceSolve.getValue() != null){
            // Set invisible the solve button
            solveBtn.setVisible(false);
            goToNewBtn.setVisible(false);
            View.mazePane.resetMazeVisited();

            // Solve the maze
            View.controller.solveMaze(choiceSolve.getValue());
        }
    }
    public void goToNew(ActionEvent actionEvent) throws IOException{
        Scene scene = SceneFactory.mainScene();
        View.primaryStage.setScene(scene);
    }
    public void enableSolveBtns(){
        solveBtn.setVisible(true);
        goToNewBtn.setVisible(true);
    }

    public void setStart(ActionEvent actionEvent) {
        if(!(startXSpinner.getValue().equals(endXSpinner.getValue()) && startYSpinner.getValue().equals(endYSpinner.getValue()))){
            View.controller.setStart(startYSpinner.getValue(), startXSpinner.getValue());
        }
    }
    public void setEnd(ActionEvent actionEvent) {
        if(!(startXSpinner.getValue().equals(endXSpinner.getValue()) && startYSpinner.getValue().equals(endYSpinner.getValue()))){
            View.controller.setEnd(endYSpinner.getValue(), endXSpinner.getValue());
        }
    }

}
