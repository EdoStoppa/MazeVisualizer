package org.example.View.MazeView;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.example.Model.Direction;
import org.example.Model.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MazePane extends Pane {
    private final int dim;
    private Position start, end;
    private final MazeCell[][] maze;
    static final HashMap<String, Image> imageMap = new HashMap<>();

    public MazePane(int dimension, boolean wallUp){
        super();
        initImages();
        this.dim = dimension;
        this.start = new Position(0,0);
        this.end = new Position(dim-1, dim-1);
        this.maze = new MazeCell[dim][dim];
        Group tileGroup = new Group();

        for(int i=0; i<dim; i++){
            for(int k=0; k<dim; k++){
                float fullDim = 800;
                MazeCell cell = new MazeCell((int) fullDim /dim, wallUp);
                maze[i][k] = cell;
                tileGroup.getChildren().add(cell);
                cell.setLayoutX((fullDim /dim)*i);
                cell.setLayoutY((fullDim /dim)*k);

                if(!wallUp){
                    resetWalls(cell, k, i);
                }
            }
        }


        this.getChildren().add(tileGroup);
    }

    private void resetWalls(MazeCell cell, int i, int k){
        if(i==0)
            cell.setWallVisible(0,true);
        if(k==0)
            cell.setWallVisible(3, true);
        if(i==dim-1)
            cell.setWallVisible(2, true);
        if(k==dim-1)
            cell.setWallVisible(1, true);
    }

    public void changeWall(Position pos, Direction dir, boolean b){
        maze[pos.getPosY()][pos.getPosX()].setWallVisible(dir.getDirectionInt(), b);
    }

    public void showVisited(Position pos){
        maze[pos.getPosY()][pos.getPosX()].setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, new Insets(0))));
    }

    public void showSolution(List<Position> listPos){
        for(Position pos : listPos)
            maze[pos.getPosY()][pos.getPosX()].setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, new Insets(0))));
    }

    public void showStart(){
        maze[start.getPosY()][start.getPosX()].showStart();
    }
    public void showEnd(){
        maze[end.getPosY()][end.getPosX()].showEnd();
    }

    public void removeStart(){
        maze[start.getPosY()][start.getPosX()].getChildren().remove(4);
    }
    public void removeEnd(){
        maze[end.getPosY()][end.getPosX()].getChildren().remove(4);
    }

    public void setStart(Position start) {
        this.start = start;
    }
    public void setEnd(Position end) {
        this.end = end;
    }

    private void initImages() {
        imageMap.put("start", new Image(Objects.requireNonNull(MazeCell.class.getClassLoader().getResourceAsStream("IMG/startPoint.png"))));
        imageMap.put("end", new Image(Objects.requireNonNull(MazeCell.class.getClassLoader().getResourceAsStream("IMG/endPoint.png"))));

    }
}
