package org.example.Model;

public class Move {
    private final Position oldPos;
    private final Position newPos;

    public Move(Position oldP, Position newP){
        this.oldPos = oldP;
        this.newPos = newP;
    }

    public Position getOldPos() {
        return this.oldPos;
    }
    public Position getNewPos() {
        return this.newPos;
    }
}
