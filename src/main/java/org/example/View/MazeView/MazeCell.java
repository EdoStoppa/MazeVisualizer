package org.example.View.MazeView;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class MazeCell extends Pane {
    private List<Line> wallList = new ArrayList<>();

    public MazeCell(int dim, boolean wallUp){
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

        //this.setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    public void setWallVisible(int wall, boolean b){
        wallList.get(wall).setVisible(b);
    }
}
