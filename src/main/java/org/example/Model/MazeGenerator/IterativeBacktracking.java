package org.example.Model.MazeGenerator;

import org.example.Model.Direction;
import org.example.Model.Maze;
import org.example.Model.Position;

import java.util.ArrayList;
import java.util.List;
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
    public void generateMaze(Maze maze){
        Stack<Position> stackPos = new Stack<>();
        Direction nextDir;
        Position p = maze.getStart().clone();
        maze.setCellAsVisited(p);
        stackPos.push(p);

        while(!stackPos.empty()){

            nextDir = getNextDir(maze, p);
            if(nextDir != null){
                stackPos.push(p);
                maze.breakWalls(p, nextDir);
                p = p.add(nextDir.getVector());
                maze.setCellAsVisited(p);
            } else {
                p = stackPos.pop();
            }
        }

        maze.resetMazeVisited();
    }

    private Direction getNextDir(Maze maze, Position pos) {
        Position nextPos;
        Direction nextDir;
        List<Direction> dirList = new ArrayList<>(Direction.getAllDir());
        Random rand = new Random();

        nextDir = dirList.get(rand.nextInt(dirList.size()));

        for(int start=0; start<4; start++){
            nextPos = pos.add(nextDir.getVector());

            if(maze.isAcceptablePos(nextPos)){
                if(!maze.cellWasVisited(nextPos)){
                    return nextDir;
                }

            }

            dirList.remove(nextDir);
            if(!dirList.isEmpty())
                nextDir = Direction.nextDirRand(dirList);
        }

        return null;
    }

    @Override
    public boolean wallSetup() {
        return true;
    }

    @Override
    public String toString() {
        return "Iterative Backtracking";
    }

}
