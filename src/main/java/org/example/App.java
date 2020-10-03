package org.example;

import org.example.Model.Maze;
import org.example.Model.MazeGenerator.RecursiveBacktracking;
import org.example.Model.Model;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Maze m = new Maze(15);
        //m.print();
        (new RecursiveBacktracking()).generateMaze(null, m);
    }
}
