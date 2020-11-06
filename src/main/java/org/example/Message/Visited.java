package org.example.Message;

import org.example.Model.Position;
import org.example.View.View;

import java.util.ArrayList;
import java.util.List;

public class Visited implements Message{
    public static final List<Position> listVisited = new ArrayList<>();
    private final Position pos;

    public Visited(Position pos){
        this.pos = pos;
    }

    public Position getPos(){
        return pos;
    }

    @Override
    public void renderGUI() {
        listVisited.add(pos);
    }
}
