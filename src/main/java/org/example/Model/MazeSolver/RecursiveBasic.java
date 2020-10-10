package org.example.Model.MazeSolver;

import org.example.Model.Direction;
import org.example.Model.Maze;
import org.example.Model.Model;
import org.example.Model.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
    public void solveMaze(Model model, Maze maze) {
        List<Position> solution = new ArrayList<>();
        maze.resetMazeVisited();
        recursiveSolve(model, maze, new Position(0,0), solution);

        Collections.reverse(solution);

        maze.setSolution(solution);
        maze.print();
    }

    private boolean recursiveSolve(Model model, Maze maze, Position pos, List<Position> solution){
        if((pos.getPosX() == maze.getDimension()-1) && (pos.getPosY() == maze.getDimension()-1)){
            solution.add(pos);
            return true;
        }

        if(maze.cellWasVisited(pos))
            return false;

        maze.setCellAsVisited(pos);

        if(maze.canMoveTo(pos, Direction.UP)){
            if(recursiveSolve(model, maze, pos.add(Direction.UP.getVector()), solution)){
                solution.add(pos);
                return true;
            }
        }

        if(maze.canMoveTo(pos, Direction.RIGHT)){
            if(recursiveSolve(model, maze, pos.add(Direction.RIGHT.getVector()), solution)){
                solution.add(pos);
                return true;
            }
        }

        if(maze.canMoveTo(pos, Direction.LEFT)){
            if(recursiveSolve(model, maze, pos.add(Direction.LEFT.getVector()), solution)){
                solution.add(pos);
                return true;
            }
        }

        if(maze.canMoveTo(pos, Direction.DOWN)){
            if(recursiveSolve(model, maze, pos.add(Direction.DOWN.getVector()), solution)){
                solution.add(pos);
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "Basic Recursive";
    }
}
