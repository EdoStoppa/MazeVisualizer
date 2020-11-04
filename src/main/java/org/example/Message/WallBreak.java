package org.example.Message;

import javafx.application.Platform;
import org.example.Model.Direction;
import org.example.Model.Position;
import org.example.View.MazeView.MazePane;
import org.example.View.View;

public class WallBreak implements Message{
    private final Position oldPos, newPos;
    private final Direction dir;

    public WallBreak(Position oldPos, Position newPos, Direction dir){
        this.oldPos = oldPos;
        this.newPos = newPos;
        this.dir = dir;
    }

    public Position getOldPos(){
        return oldPos;
    }
    public Position getNewPos() {
        return newPos;
    }
    public Direction getDir(){
        return dir;
    }

    @Override
    public void renderGUI() {
        Platform.runLater(() -> {
            MazePane mazePane = View.mazePane;
            mazePane.changeWall(oldPos, dir, false);
            mazePane.changeWall(newPos, Direction.getDir(dir.getOppositeDirectionInt()), false);
        });
    }
}
