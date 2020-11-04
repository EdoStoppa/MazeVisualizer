package org.example.Message;

import org.example.Model.Position;

public class NewStart implements Message{
    private final Position pos;

    public NewStart(Position pos) {
        this.pos = pos;
    }

    @Override
    public void renderGUI() {

    }

    public Position getPos() {
        return pos;
    }
}
