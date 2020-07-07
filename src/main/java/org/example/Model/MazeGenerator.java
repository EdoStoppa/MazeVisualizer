package org.example.Model;

import java.util.Stack;

public class MazeGenerator {

    //this is the first (and the most basic) algorithm to generate a maze: Recursive Backtracking
    public static void recursiveBacktracking(Model model){
        Stack<Position> stackPos = new Stack<>();
        Maze maze = model.getMaze();
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

}
