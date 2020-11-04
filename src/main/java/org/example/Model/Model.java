package org.example.Model;

import org.example.Message.EndSolving;
import org.example.Message.Message;
import org.example.Message.NewEnd;
import org.example.Message.NewStart;
import org.example.Model.MazeGenerator.MazeGenerator;
import org.example.Model.MazeSolver.MazeSolver;
import org.example.Observ.Observable;
import org.example.Observ.Observer;
import org.example.Controller.Controller;

public class Model extends Observable<Message> implements Observer<Message> {
    private Maze maze;

    public Model(Controller controller){
        addObserver(controller);
    }

    public void createMaze(int dimension, MazeGenerator generator) {
        this.maze = new Maze(dimension, generator.wallSetup());
        maze.addObserver(this);
        generator.generateMaze(this.maze);
    }
    public void solveMaze(MazeSolver solver){
        solver.solveMaze(this.maze);
        notify(new EndSolving(maze.getSolution()));
    }

    @Override
    public void update(Message message) {
        //The mazeGenerator has changed the maze and needs to render those changes
        notify(message);
    }

    // Set start/end of the maze
    public void setStart(int x, int y){
        maze.setStart(new Position(x,y));
        notify(new NewStart(new Position(x, y)));
    }
    public void setEnd(int x, int y){
        maze.setGoal(new Position(x,y));
        notify(new NewEnd(new Position(x, y)));
    }

    public void printMaze() {
        if(maze!=null)
            maze.print();
    }
}
