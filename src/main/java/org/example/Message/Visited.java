package org.example.Message;

import org.example.Model.Position;

public class Visited implements Message{
    private final Position pos;

    public Visited(Position pos){
        this.pos = pos;
    }

    public Position getPos(){
        return pos;
    }

    @Override
    public void renderGUI() {

    }
}