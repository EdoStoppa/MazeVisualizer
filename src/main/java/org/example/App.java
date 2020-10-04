package org.example;

import org.example.Model.MazeGenerator.IterativeBacktracking;
import org.example.Model.Model;
import org.example.View.View;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        View view = new View();
        Model model = new Model(view);

        model.createMaze(12, new IterativeBacktracking());
        model.killAll();
    }
}
