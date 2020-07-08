package org.example.Model.MazeGenerator;

import org.example.Model.Maze;
import org.example.Model.Model;
import org.example.Model.Position;

import java.util.Stack;

public class RecursiveBacktracking implements MazeGenerator{
    //this is the first (and the most basic) algorithm to generate a maze: Recursive Backtracking
    @Override
    public void generateMaze(Model model, Maze maze){
        Stack<Position> stackPos = new Stack<>();
        int direction;
        maze.setStart();
        stackPos.push(maze.getCurPos());

        /*while(!stack.empty()){
            direction = chooseDirection(cur, maze);
            if(direction != -1){
                maze[cur.getY()][cur.getX()].breakWall(direction);
                stack.push(maze[cur.getY()][cur.getX()]);
                cur.move(direction);
                maze[cur.getY()][cur.getX()].setVisited();
                maze[cur.getY()][cur.getX()].breakWall(oppositeDirection(direction));

            } else {
                previousPos = stackPos.pop();
                maze.setCurPos(previousPos)
            }
        }*/
    }

    @Override
    public Position getNextPos(Maze maze) {
        return null;
    }

}
