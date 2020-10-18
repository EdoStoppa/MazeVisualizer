package org.example.Model.MazeGenerator;

import org.example.Model.Direction;
import org.example.Model.Maze;
import org.example.Model.Model;
import org.example.Model.Position;

import java.util.Random;

/*
 *  Algorithm's high-level description (from Wikipedia: https://en.wikipedia.org/wiki/Maze_generation_algorithm)
 *
 * Recursive Division:
 * - Begin with the maze's space with no walls (we call this a chamber)
 *
 * - Divide the chamber with two perpendicular line of walls where each semi-line contains a randomly placed hole
 * - Repeat the process with the new generated sub-chambers until you reach the chamber minimum size
 *
 */
public class RecursiveDivision implements MazeGenerator{
    @Override
    public void generateMaze(Model model, Maze maze) {

        recursiveGeneration(new Position(0,0), new Position(maze.getDimension()-1, maze.getDimension()-1), maze);

        maze.createStartEnd();
    }

    void recursiveGeneration(Position origin, Position bound, Maze maze){
        Random rand = new Random();
        int noWallSegment;

        int maxY = bound.getPosY(), minY = origin.getPosY();
        int midY = minY + (maxY - minY)/2;
        int maxX = bound.getPosX(), minX = origin.getPosX();
        int midX = minX + (maxX - minX)/2;

        if(!(origin.equals(bound) || maxY == minY || maxX == minX)){

            raiseWallLine(midY, minX, maxX, false, maze);
            raiseWallLine(midX, minY, maxY, true, maze);

            //chosen at random in 3 of the 4 semi-lines only one wall to destroy
            noWallSegment = rand.nextInt(4);
            if(noWallSegment != Direction.UP.getDirectionInt()){
                maze.breakWalls(new Position(minX + rand.nextInt(midX - minX + 1), midY), Direction.RIGHT);
            }
            if(noWallSegment != Direction.RIGHT.getDirectionInt()){
                maze.breakWalls(new Position(midX, minY + rand.nextInt(midY - minY + 1)), Direction.DOWN);
            }
            if(noWallSegment != Direction.DOWN.getDirectionInt()){
                maze.breakWalls(new Position(midX + 1 + rand.nextInt(maxX - midX), midY), Direction.RIGHT);
            }
            if(noWallSegment != Direction.LEFT.getDirectionInt()){
                maze.breakWalls(new Position(midX, midY +1 + rand.nextInt(maxY- midY)), Direction.DOWN);
            }

            //First Quadrant
            recursiveGeneration(new Position(minX,midY+1), new Position(midX, maxY), maze);
            // Second Quadrant
            recursiveGeneration(origin, new Position(midX, midY), maze);
            // Third Quadrant
            recursiveGeneration(new Position(midX+1, minY), new Position(maxX, midY), maze);
            // Fourth Quadrant
            recursiveGeneration(new Position(midX+1, midY+1), bound, maze);
        }

    }

    public void raiseWallLine(int line, int start, int end, boolean horizontal, Maze maze){
        int index;

        if(horizontal){
            for(index=start; index<=end; index++){
                maze.raiseWall(line, index, Direction.DOWN);
                maze.raiseWall(line+1, index, Direction.UP);
            }
        } else {
            for(index=start; index<=end; index++){
                maze.raiseWall(index, line, Direction.RIGHT);
                maze.raiseWall(index, line+1, Direction.LEFT);
            }
        }

    }

    @Override
    public String toString() {
        return "Recursive Division Algorithm";
    }

    @Override
    public boolean wallSetup() {
        return false;
    }
}
