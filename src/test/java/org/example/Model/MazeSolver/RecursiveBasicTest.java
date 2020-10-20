package org.example.Model.MazeSolver;

import org.example.Model.Maze;
import org.example.Model.MazeGenerator.IterativeBacktracking;
import org.example.Model.MazeGenerator.MazeGenerator;
import org.example.Model.Model;
import org.example.Model.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecursiveBasicTest {
    Model m;
    Maze maze;
    MazeGenerator gen;
    MazeSolver solver;

    @Test
    void basic(){
        m = new Model(null);
        maze = new Maze(12, true);
        gen = new IterativeBacktracking();
        solver = new RecursiveBasic();


        gen.generateMaze(maze);
        maze.resetMazeVisited();
        solver.solveMaze(maze);
        List<Position> sol = maze.getSolution();
        System.out.println(sol.size());
        for(Position p : sol){
            System.out.println("-> " + p + " ");
        }
    }
}