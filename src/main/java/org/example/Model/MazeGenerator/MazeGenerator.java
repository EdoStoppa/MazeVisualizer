package org.example.Model.MazeGenerator;

import org.example.Model.Maze;
import org.example.Model.Model;
import org.example.Model.Position;

public interface MazeGenerator {
    void generateMaze(Model model, Maze maze);
    Position getNextPos(Maze maze);
}
