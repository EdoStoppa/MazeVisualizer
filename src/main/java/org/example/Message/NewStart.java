package org.example.Message;

import org.example.Model.Position;
import org.example.View.View;

public class NewStart implements Message{
    private final Position pos;

    public NewStart(Position pos) {
        this.pos = pos;
    }

    @Override
    public void renderGUI() {
        View.mazePane.removeStart();
        View.mazePane.setStart(pos);
        View.mazePane.showStart();
    }

    public Position getPos() {
        return pos;
    }
}
