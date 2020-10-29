package org.example.Model;

import org.example.Message.Message;
import org.example.Model.MazeGenerator.MazeGenerator;
import org.example.Model.MazeSolver.MazeSolver;
import org.example.Observ.Observable;
import org.example.Observ.Observer;
import org.example.View.Controller;

public class Model extends Observable<Message> implements Observer<Message> {
    private Maze maze;
    // This value represent the delay used to process every step to show on GUI all the maze generation procedurally

    public Model(Controller controller){
        addObserver(controller);
    }

    public void createMaze(int dimension, MazeGenerator generator) {
        this.maze = new Maze(dimension, generator.wallSetup());
        maze.addObserver(this);
        generator.generateMaze(this.maze);
        maze.print();
    }
    public void solveMaze(MazeSolver solver){
        solver.solveMaze(this.maze);
        maze.print();
    }

    @Override
    public void update(Message message) {
        //The the mazeGenerator has changed the maze and needs to render those changes
        notify(message);
    }
}
