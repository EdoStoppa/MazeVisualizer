package org.example.Model;

import org.example.Observ.Observable;
import org.example.View.View;

public class Maze extends Observable<Object> {
    Cell[][] maze;

    public Maze(View view, int dimension){
        this.maze = new Cell[dimension][dimension];
        addObserver(view);
    }
}
