package org.example.Model;

import org.example.Message.Message;
import org.example.Model.MazeGenerator.MazeGenerator;
import org.example.Observ.Observable;
import org.example.Observ.Observer;
import org.example.View.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model extends Observable<Message> implements Observer<Message> {
    private final ExecutorService mazeExecutor;
    private Maze maze;
    //This value represent the delay used to process every step to show on GUI all the maze generation procedurally
    protected static int delay;

    public Model(View view){
        this.mazeExecutor = Executors.newFixedThreadPool(4);
        addObserver(view);
    }

    public void createMaze(int dimension, MazeGenerator generator){
        this.maze = new Maze(dimension);
        maze.addObserver(this);
        mazeExecutor.submit(() -> generator.generateMaze(this, this.maze));
    }

    public void killAll(){
        mazeExecutor.shutdown();
    }

    @Override
    public void update(Message message) {
        //The the mazeGenerator has changed the maze and needs to render those changes
        notify(message);
    }
}
