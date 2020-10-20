package org.example.Model;

import org.example.Message.Message;
import org.example.Model.MazeGenerator.MazeGenerator;
import org.example.Model.MazeSolver.MazeSolver;
import org.example.Observ.Observable;
import org.example.Observ.Observer;
import org.example.View.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model extends Observable<Message> implements Observer<Message> {
    private Thread currentThread;
    private Maze maze;
    // This value represent the delay used to process every step to show on GUI all the maze generation procedurally

    public Model(View view){
        addObserver(view);
    }

    public void createMaze(int dimension, MazeGenerator generator){
        this.maze = new Maze(dimension, generator.wallSetup());
        maze.addObserver(this);
        this.currentThread = new Thread(() ->{
            generator.generateMaze(this.maze);
            maze.print();
        });
        currentThread.start();
    }
    public void solveMaze(MazeSolver solver){
        this.currentThread = new Thread(() ->{
            solver.solveMaze(this, this.maze);
        });
        currentThread.start();
    }

    public Thread getCurrentThread(){
        return currentThread;
    }

    @Override
    public void update(Message message) {
        //The the mazeGenerator has changed the maze and needs to render those changes
        notify(message);
    }
}
