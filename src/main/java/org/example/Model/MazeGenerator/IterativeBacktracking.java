package org.example.Model.MazeGenerator;

import org.example.Model.Direction;
import org.example.Model.Maze;
import org.example.Model.Model;
import org.example.Model.Position;

import java.util.Random;
import java.util.Stack;

/*
 * Algorithm's high-level description (from Wikipedia: https://en.wikipedia.org/wiki/Maze_generation_algorithm)
 *
 * Randomized depth-first search (iterative backtracking):
 *
 * - Choose the initial cell, mark it as visited and push it to the stack
 * - While the stack is not empty
 *     - Pop a cell from the stack and make it a current cell
 *     - If the current cell has any neighbours which have not been visited
 *         - Push the current cell to the stack
 *         - Choose one of the unvisited neighbours
 *         - Remove the wall between the current cell and the chosen cell
 *         - Mark the chosen cell as visited and push it to the stack
 */
public class IterativeBacktracking implements MazeGenerator {

    @Override
    public void generateMaze(Model model, Maze maze){
        Stack<Position> stackPos = new Stack<>();
        Direction nextDir;
        Position p = new Position(0,0);
        maze.setCellAsVisited(p);
        maze.setCurPos(p);
        stackPos.push(maze.getCurPos());

        while(!stackPos.empty()){
            if(model.getDelay() > 0){
                try{
                    Thread.sleep(model.getDelay());
                } catch(IllegalArgumentException | InterruptedException e){
                    System.err.println("Something went wrong in the sleep");
                }
            }

            nextDir = getNextDir(maze);
            if(nextDir != null){
                stackPos.push(maze.getCurPos());
                maze.breakWalls(maze.getCurPos(), nextDir);
                p = maze.getCurPos().add(nextDir.getVector());
                maze.setCellAsVisited(p);
            } else {
                p = stackPos.pop();
            }
            
            maze.setCurPos(p);
        }

        maze.createStartEnd();
        maze.print();
    }

    @Override
    public boolean wallSetup() {
        return true;
    }

    @Override
    public String toString() {
        return "Iterative Backtracking Algorithm";
    }

    private Direction getNextDir(Maze maze) {
        Position curPos, nextPos;
        Direction nextDir;
        Random rand = new Random();

        curPos = maze.getCurPos();
        nextDir = Direction.getDir(rand.nextInt(4));

        for(int start=0; start<4; start++){
            curPos = maze.getCurPos();
            nextPos = curPos.add(nextDir.getVector());

            if(maze.isAcceptablePos(nextPos)){
                if(!maze.cellWasVisited(nextPos)){
                    return nextDir;
                }

            }

            nextDir = Direction.nextDir(nextDir);
        }

        return null;
    }
}
