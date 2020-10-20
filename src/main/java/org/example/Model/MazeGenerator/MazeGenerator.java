package org.example.Model.MazeGenerator;

import org.example.Model.Direction;
import org.example.Model.Maze;
import org.example.Model.Model;

public interface MazeGenerator {
    void generateMaze(Maze maze);
    boolean wallSetup();
    String toString();
}
