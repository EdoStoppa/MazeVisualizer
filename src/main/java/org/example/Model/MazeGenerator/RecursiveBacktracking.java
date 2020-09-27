package org.example.Model.MazeGenerator;

import org.example.Model.Direction;
import org.example.Model.Maze;
import org.example.Model.Model;
import org.example.Model.Position;

import java.util.Stack;

public class RecursiveBacktracking implements MazeGenerator {
    @Override
    public void generateMaze(Model model, Maze maze){
        Stack<Position> stackPos = new Stack<>();
        Direction nextDir;
        Position p;
        maze.setStart();
        stackPos.push(maze.getCurPos());

        while(!stackPos.empty()){
            nextDir = getNextDir(maze);
            if(nextDir != null){
                maze.breakWalls(nextDir);
                stackPos.push(maze.getCurPos());
                maze.setCurPos(maze.getCurPos().add(nextDir.getVector()));
                maze.setCellAsVisited(maze.getCurPos());
            } else {
                p = stackPos.pop();
                maze.setCurPos(p);
            }
        }

        maze.print();
    }

    private Direction getNextDir(Maze maze) {
        return null;
    }
}
