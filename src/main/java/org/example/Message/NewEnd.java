package org.example.Message;

import org.example.Model.Position;

public class NewEnd implements Message{
    private final Position pos;

    public NewEnd(Position pos) {
        this.pos = pos;
    }

    @Override
    public void renderGUI() {

    }

    public Position getPos() {
        return pos;
    }
}
