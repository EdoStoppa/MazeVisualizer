package org.example.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import org.example.Model.Maze;
import org.example.Model.MazeGenerator.MazeGenerator;
import org.example.View.MazeView.MazePane;

import java.io.IOException;

public class SceneFactory {

    public static Scene startScene() throws IOException {
        Parent root = FXMLLoader.load(SceneFactory.class.getResource("/FXML/startScene.fxml"));
        return new Scene(root);
    }

    public static Scene infoScene() throws IOException{
        Parent root = FXMLLoader.load(SceneFactory.class.getResource("/FXML/infoScene.fxml"));
        return new Scene(root);
    }

    public static Scene mainScene(){
        BorderPane layout = new BorderPane();

        MazePane mazePane = new MazePane();
        BorderPane.setMargin(mazePane, new Insets(20));
        mazePane.setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        // Build the right panel needed to control the generation/solving
        VBox rightPanel = new VBox();
        BorderPane.setMargin(rightPanel, new Insets(20));
        rightPanel.setPrefWidth(200);
        rightPanel.setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        // Build the component used to set the maze dimension
        VBox mazeDimBox = new VBox();
        VBox.setMargin(mazeDimBox, new Insets(50, 10, 50, 10));
        Label dimQuestion = new Label("Maze's\ndimension");
        dimQuestion.setPadding(new Insets(5));
        dimQuestion.setFont(new Font(25));
        dimQuestion.setTextAlignment(TextAlignment.CENTER);
        VBox.setMargin(dimQuestion, new Insets(10, 0, 10, 0));
        Spinner<Integer> dimSpinner = new Spinner<>();
        dimSpinner.setPrefSize(80,30);
        dimSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 100, 20));
        VBox.setMargin(dimSpinner, new Insets(10, 0, 10, 0));
        mazeDimBox.setAlignment(Pos.CENTER);
        mazeDimBox.getChildren().addAll(dimQuestion, dimSpinner);

        // Build the generation algorithm selector
        VBox genAlgBox = new VBox();
        genAlgBox.setPadding(new Insets(50, 0, 50, 0));
        Label genAlgQuestion = new Label("Generation\nalgorithm");
        genAlgQuestion.setFont(new Font(25));
        genAlgQuestion.setTextAlignment(TextAlignment.CENTER);
        genAlgQuestion.setPadding(new Insets(10, 0, 10, 0));
        ChoiceBox<MazeGenerator> genChoiceBox = new ChoiceBox<>();
        for(MazeGenerator mzGen : Maze.getAllGenerators())
            genChoiceBox.getItems().add(mzGen);
        genAlgBox.setAlignment(Pos.CENTER);
        genAlgBox.getChildren().addAll(genAlgQuestion, genChoiceBox);

        // Button that creates
        VBox expBox = new VBox();
        expBox.setPadding(new Insets(100, 0, 0, 0));
        Button btn = new Button("Generate\nMaze!");
        btn.setFont(new Font(20));
        btn.setTextAlignment(TextAlignment.CENTER);
        btn.setOnAction(e -> {
            int dim = dimSpinner.getValue();
            MazeGenerator gen = genChoiceBox.getValue();
            System.out.println(mazePane.getWidth() + " " + mazePane.getHeight());
            if(gen != null){
                View.mazePane = new MazePane(dim, gen.wallSetup());
                View.controller.createMaze(dim, gen);

                rightPanel.getChildren().remove(2);
                rightPanel.getChildren().add(getEvolvBtns());
            }
        });
        expBox.setAlignment(Pos.CENTER);
        expBox.getChildren().addAll(btn);

        // Add everything to the right panel
        rightPanel.getChildren().addAll(mazeDimBox, genAlgBox, expBox);

        layout.setRight(rightPanel);
        layout.setCenter(mazePane);

        return new Scene(layout, View.getWidth(), View.getHeight());
    }

    private static HBox getEvolvBtns(){
        HBox res = new HBox();
        res.setPadding(new Insets(100, 0, 0, 0));
        res.setAlignment(Pos.CENTER);

        Button stop = new Button("Stop"), faster = new Button("Faster");
        VBox stopB = new VBox(stop), fasterB = new VBox(faster);
        stopB.setPadding(new Insets(0,10,0,10));
        fasterB.setPadding(new Insets(0,10,0,10));
        res.getChildren().addAll(stopB, fasterB);

        return res;
    }


}
