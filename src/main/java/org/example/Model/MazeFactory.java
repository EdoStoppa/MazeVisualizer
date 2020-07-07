package org.example.Model;

import java.util.Stack;

public class MazeFactory {
    public static void recursiveBacktracking(Maze maze){
        Stack<Cell> stack = new Stack<Cell>();
        int direction;
        /*maze[0][0].setVisited();
        stack.push(maze[0][0]);

        while(!stack.empty()){
            direction = chooseDirection(cur, maze);
            if(direction != -1){
                maze[cur.getY()][cur.getX()].breakWall(direction);
                stack.push(maze[cur.getY()][cur.getX()]);
                cur.move(direction);
                maze[cur.getY()][cur.getX()].setVisited();
                maze[cur.getY()][cur.getX()].breakWall(oppositeDirection(direction));

            } else {
                box = stack.pop();
                cur.setY(box.getY());
                cur.setX(box.getX());
            }
        }*/
    }
}
