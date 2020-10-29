package org.example.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import org.example.Model.Maze;
import org.example.Model.MazeGenerator.MazeGenerator;

import java.util.List;

public class SceneFactory {

    public static Scene welcomeScene(){
        StackPane layout = new StackPane();

        Label title = new Label("MazeVisualizer");
        Button startButton = new Button("Start!");
        Button resolutionButton = new Button("Set Resolution");


        title.setTranslateY((-View.getWidth()/2.0) * (0.5));
        title.setFont(new Font(View.getWidth()*0.04));

        startButton.setTranslateY((View.getHeight()/2.0) * (0.7));
        startButton.setFont(new Font(View.getWidth()*0.015));
        startButton.setOnAction(e -> {
            View.primaryStage.setScene(mainScene());
            View.primaryStage.show();
        });

        resolutionButton.setFont(new Font(View.getWidth()*0.012));
        StackPane.setAlignment(resolutionButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(resolutionButton, new Insets(10));

        layout.getChildren().addAll(title, startButton, resolutionButton);

        return new Scene(layout, View.getWidth(), View.getHeight());
    }

    public static Scene mainScene(){
        BorderPane layout = new BorderPane();

        GridPane maze = new GridPane();
        BorderPane.setMargin(maze, new Insets(20));
        maze.setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        // Build the right panel needed to control the generation/solving
        VBox rightPanel = new VBox();
        BorderPane.setMargin(rightPanel, new Insets(20));
        rightPanel.setPrefWidth(View.getWidth()*0.2);
        rightPanel.setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        // Build the component used to set the maze dimension
        VBox mazeDimBox = new VBox();
        mazeDimBox.setPadding(new Insets(20, 0, 0, 0));
        Label dimQuestion = new Label("Maze's sides dimension");
        dimQuestion.setPadding(new Insets(10, 0, 10, 0));
        Spinner<Integer> dimSpinner = new Spinner<>();
        dimSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 100, 20));
        mazeDimBox.setAlignment(Pos.CENTER);
        mazeDimBox.getChildren().addAll(dimQuestion, dimSpinner);

        // Build the generation algorithm selector
        VBox genAlgBox = new VBox();
        genAlgBox.setPadding(new Insets(20, 0, 0, 0));
        Label genAlgQuestion = new Label("Choose the generation algorithm");
        genAlgQuestion.setPadding(new Insets(10, 0, 10, 0));
        ChoiceBox<MazeGenerator> genChoiceBox = new ChoiceBox<>();
        for(MazeGenerator mzGen : Maze.getAllGenerators())
            genChoiceBox.getItems().add(mzGen);
        genAlgBox.setAlignment(Pos.CENTER);
        genAlgBox.getChildren().addAll(genAlgQuestion, genChoiceBox);

        // Just an experiment
        VBox expBox = new VBox();
        expBox.setPadding(new Insets(100, 0, 0, 0));
        Button btn = new Button("Experiment");
        btn.setOnAction(e -> {
            List<Node> list = View.primaryStage.getScene().getRoot().getChildrenUnmodifiable();
            ((VBox)list.get(0)).getChildren().clear();

        });
        expBox.setAlignment(Pos.CENTER);
        expBox.getChildren().addAll(btn);

        // Add everything to the right panel
        rightPanel.getChildren().addAll(mazeDimBox, genAlgBox, expBox);

        layout.setRight(rightPanel);
        layout.setCenter(maze);

        return new Scene(layout, View.getWidth(), View.getHeight());
    }
}
