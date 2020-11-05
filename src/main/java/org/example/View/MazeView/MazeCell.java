package org.example.View.MazeView;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MazeCell extends Pane {
    private final int dim;
    private final List<Line> wallList = new ArrayList<>();

    public MazeCell(int dim, boolean wallUp){
        this.dim = dim;
        this.setMaxSize(dim, dim);
        this.setMinSize(dim, dim);

        wallList.add(new Line(0,0, dim,0));
        wallList.add(new Line(dim,0, dim, dim));
        wallList.add(new Line(0, dim, dim, dim));
        wallList.add(new Line(0,0, 0,dim));

        for(Line l : wallList){
            int rem = 120/(800/dim) + 1;
            l.setStrokeWidth(rem);
            l.setStroke(Color.SLATEGREY);
            l.setVisible(wallUp);
            this.getChildren().add(l);
        }

    }

    public void setWallVisible(int wall, boolean b){
        wallList.get(wall).setVisible(b);
    }

    public void showStart() {
        Rectangle container = new Rectangle(this.dim, this.dim);
        container.setFill(new ImagePattern(MazePane.imageMap.get("start")));
        this.getChildren().add(container);
    }
    public void showEnd() {
        Rectangle container = new Rectangle(this.dim, this.dim);
        container.setFill(new ImagePattern(MazePane.imageMap.get("end")));
        this.getChildren().add(container);
    }


}
