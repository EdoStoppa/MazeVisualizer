package org.example.Message;

import org.example.Model.Position;

public class Move implements Message{
    private final Position oldPos;
    private final Position newPos;
    private final boolean wasVisited;

    public Move(Position oldP, Position newP, boolean wasVisited){
        this.oldPos = oldP;
        this.newPos = newP;
        this.wasVisited = wasVisited;
    }

    public Position getOldPos() {
        return this.oldPos;
    }
    public Position getNewPos() {
        return this.newPos;
    }
    public boolean getWasVisited() {
        return this.wasVisited;
    }

    @Override
    public void renderGUI() {

    }
}
