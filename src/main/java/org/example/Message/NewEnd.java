package org.example.Message;

import org.example.Model.Position;
import org.example.View.View;

public class NewEnd implements Message{
    private final Position pos;

    public NewEnd(Position pos) {
        this.pos = pos;
    }

    @Override
    public void renderGUI() {
        View.mazePane.removeEnd();
        View.mazePane.setEnd(pos);
        View.mazePane.showEnd();
    }

    public Position getPos() {
        return pos;
    }
}
