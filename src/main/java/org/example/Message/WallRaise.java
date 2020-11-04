package org.example.Message;

import javafx.application.Platform;
import org.example.Model.Direction;
import org.example.Model.Position;
import org.example.View.MazeView.MazePane;
import org.example.View.View;

public class WallRaise implements Message{
    private final Position pos;
    private final Direction dir;

    public WallRaise(Position pos, Direction dir) {
        this.pos = pos;
        this.dir = dir;
    }

    public Position getPos() {
        return pos;
    }
    public Direction getDir() {
        return dir;
    }

    @Override
    public void renderGUI() {
        Platform.runLater(() -> {
            // Something
            MazePane mazePane = View.mazePane;
            mazePane.changeWall(pos, dir, true);
            mazePane.changeWall(pos.add(dir.getVector()), Direction.getDir(dir.getOppositeDirectionInt()), true);
        });
    }
}
