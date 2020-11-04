package org.example.Message;

import javafx.application.Platform;
import org.example.Model.Position;
import org.example.View.View;

import java.util.ArrayList;
import java.util.List;

public class EndSolving implements Message{
    private final List<Position> solution;

    public EndSolving(List<Position> solution){
        this.solution = new ArrayList<>(solution);
    }

    @Override
    public void renderGUI() {
        Platform.runLater(() -> {
            View.mazePane.showSolution(solution);
        });
    }
}
