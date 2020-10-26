package org.example.Model.MazeSolver;

import org.example.Model.Direction;
import org.example.Model.Maze;
import org.example.Model.Model;
import org.example.Model.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/*
 * Algorithm's high-level description (from Wikipedia: https://en.wikipedia.org/wiki/Maze_solving_algorithm)
 *
 * Recursive Solver
 * - start from the entry of the maze and call the recursive function:
 *      - if the position given is at the exit of the maze
 *          - return true
 *      - if the cell at the given position was already visited
 *          - return false
 *      - set the cell as visited if no previous condition was true
 *      - for every direction (UP, RIGHT, DOWN, LEFT), if the move to that direction is possible
 *          - call the recursive function on the new pos
 *          - if that returns true
 *              - add the position to the solution list
 *              - return true
 *      - if no other condition is met, return false
 * - if you want the solution list in the correct order, reverse the list obtained from recursion
 */
public class RecursiveBasic implements MazeSolver{
    @Override
    public void solveMaze(Maze maze) {
        List<Position> solution = new ArrayList<>();
        recursiveSolve(maze, maze.getStart().clone(), maze.getGoal().clone(), solution);

        Collections.reverse(solution);

        maze.resetMazeVisited();
        maze.setSolution(solution);
    }

    private boolean recursiveSolve(Maze maze, Position pos, Position goal, List<Position> solution){
        List<Direction> dirList = Direction.getAllDir();
        Direction dir;
        Random rand = new Random();

        if(pos.equals(goal)){
            solution.add(pos);
            return true;
        }

        if(maze.cellWasVisited(pos))
            return false;

        maze.setCellAsVisited(pos);

        // choosing at random every step to expand ensures an unbiased search + it's less code :)
        dir = dirList.get(rand.nextInt(4));
        while(!dirList.isEmpty()){
            if(maze.canMoveTo(pos, dir)){
                if(recursiveSolve(maze, pos.add(dir.getVector()), goal, solution)){
                    solution.add(pos);
                    return true;
                }
            }

            dirList.remove(dir);
            if(!dirList.isEmpty())
                dir = Direction.nextDirRand(dirList);
        }

        return false;
    }

    @Override
    public String toString() {
        return "Basic Recursive Solver";
    }
}
