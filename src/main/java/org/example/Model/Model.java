package org.example.Model;

import org.example.Model.MazeGenerator.MazeGenerator;
import org.example.Observ.Observable;
import org.example.Observ.Observer;
import org.example.View.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model extends Observable<Object> implements Observer<Move> {
    private final ExecutorService mazeExecutor;
    private Maze maze;
    protected static int delay;

    public Model(View view){
        this.mazeExecutor = Executors.newFixedThreadPool(4);
        addObserver(view);
    }

    public void generateMaze(int dimension, MazeGenerator generator){
        this.maze = new Maze(dimension);
        maze.addObserver(this);
        mazeExecutor.submit(() -> generator.generateMaze(this, this.maze));
    }

    @Override
    public void update(Move message) {
        //The mazeGenerator has done a move, need to render it
    }

}
