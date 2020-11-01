package org.example.View.MazeView;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.example.Model.Maze;

public class MazePane extends Pane {
    private int widht=720, height=720;
    private int dimension;
    private MazeCell[] maze;

    public MazePane(){
        Label hei = new Label("This is an experiment :)");
        this.getChildren().add(hei);
    }
    public MazePane(int dimension, boolean wallUp){
        super();
        this.dimension = dimension;
    }

    public void setDimension(int dim){
        this.dimension = dim;
    }
}
