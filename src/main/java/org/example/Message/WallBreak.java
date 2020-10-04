package org.example.Message;

import org.example.Model.Direction;
import org.example.Model.Position;

public class WallBreak implements Message{
    Position oldPos, newPos;
    Direction dir;

    public WallBreak(Position oldPos, Position newPos, Direction dir){
        this.oldPos = oldPos;
        this.newPos = newPos;
        this.dir = dir;
    }

    @Override
    public void renderGUI() {

    }
}
