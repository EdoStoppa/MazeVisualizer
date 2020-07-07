package org.example.Model;

import org.example.Observ.Observable;
import org.example.Observ.Observer;
import org.example.View.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model extends Observable<Object> implements Observer<Move> {
    private final ExecutorService mazeGenerator;
    private Maze maze;
    protected static int delay;

    public Model(View view){
        this.mazeGenerator = Executors.newFixedThreadPool(4);
        addObserver(view);
    }

    public void generateMaze(Maze maze, Runnable mazeGeneratorAlgorithm){
        this.maze = maze;
        maze.addObserver(this);
        mazeGenerator.submit(mazeGeneratorAlgorithm);
    }

    protected Maze getMaze(){
        return this.maze;
    }

    @Override
    public void update(Move message) {
        //The mazeGenerator has done a move, need to render it
    }

}
