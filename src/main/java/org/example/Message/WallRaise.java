package org.example.Message;

import org.example.Model.Direction;
import org.example.Model.Position;

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

    }
}
