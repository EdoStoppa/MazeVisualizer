package org.example.Model.MazeSolver;

import org.example.Model.Direction;
import org.example.Model.Maze;
import org.example.Model.Model;
import org.example.Model.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecursiveBasic implements MazeSolver{
    @Override
    public void solveMaze(Model model, Maze maze) {
        List<Position> solution = new ArrayList<>();
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
}
