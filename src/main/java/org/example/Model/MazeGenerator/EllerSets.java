package org.example.Model.MazeGenerator;

import org.example.Model.Direction;
import org.example.Model.Maze;
import org.example.Model.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/*
* Algorithm's high-level description (from "The Buckblog": https://weblog.jamisbuck.org/2010/12/29/maze-generation-eller-s-algorithm)
*
* Eller's Algorithm:
* - Start with a maze full of walls
* - For every row of the maze (except the last one):
*       - Initialize every cell that isn't already part of a set assigning them a new set
*       - Now, randomly join adjacent cells, but only if they are not in the same set. When joining adjacent cells, merge the cells of both sets into a single set
*       - For each set, randomly create vertical connections downward to the next row. Each remaining set must have at least one vertical connection
*       - Fill out the next row by putting any cells without a set into their own new set
* - For the last row, join all adjacent cells that do not share a set, and omit the vertical connections
*/
public class EllerSets implements MazeGenerator{

    @Override
    public void generateMaze(Maze maze) {
        int lastId = 0, temp;
        HashMap<Integer, Integer> setsMap = new HashMap<>(), copyMap;
        List<Position> needExpandList = new ArrayList<>();
        Random rand = new Random();

        // Generate a sort of compressed representation of the maze that's keeping track of which set is a particular cell part of.
        int[][] compressedMaze = new int[maze.getDimension()][maze.getDimension()];
        for(int i=0; i<maze.getDimension(); i++) { for (int j = 0; j < maze.getDimension(); j++) { compressedMaze[i][j] = 0; } }

        // The algorithm starts really here
        for(int row=0; row<maze.getDimension(); row++){
            setsMap = new HashMap<>();

            // First thing is to assign to every cell a set, so if the cell isn't any of the already present sets, create a new one)
            for(int col=0; col<maze.getDimension(); col++){
                if(compressedMaze[row][col] == 0){
                    lastId++;
                    compressedMaze[row][col] = lastId;
                }
                setsMap.put(compressedMaze[row][col], compressedMaze[row][col]);
            }

            // Then randomly (50% probability) merge two adjacent sets if they're different, and in doing so break the wall between the two cell involved
            for(int col=0; col<maze.getDimension()-1; col++){
                if(rand.nextBoolean()){
                    if(compressedMaze[row][col] != compressedMaze[row][col+1]){
                        setsMap.remove(compressedMaze[row][col+1]);
                        temp = compressedMaze[row][col+1];
                        compressedMaze[row][col+1] = compressedMaze[row][col];
                        for(int cl=col+2; cl<maze.getDimension(); cl++){
                            if(compressedMaze[row][cl] == temp)
                                compressedMaze[row][cl] = compressedMaze[row][col];
                        }
                        maze.breakWalls(new Position(row, col), Direction.RIGHT);
                    }
                }
            }

            // Now expand the corridor going one row down at least once per sets (expansion's probability is 30%)
            if(row != maze.getDimension()-1){
                copyMap = new HashMap<>(setsMap);
                for(int col=0; col<maze.getDimension(); col++){
                    if(rand.nextInt(10)<3){
                        copyMap.remove(compressedMaze[row][col]);
                        compressedMaze[row+1][col] = compressedMaze[row][col];
                        maze.breakWalls(new Position(row, col), Direction.DOWN);
                    }
                }

                // TODO: fix this part
                // If some sets are still present is the copy of the alive ones, I need to expand at least one cell from each of these remaining sets
                if(!copyMap.isEmpty()){
                    for(int aliveSetId : copyMap.keySet()){
                        for(int column=0; column<maze.getDimension(); column++){
                            if(compressedMaze[row][column] == aliveSetId)
                                needExpandList.add(new Position(row, column));
                        }

                        maze.breakWalls(needExpandList.get(rand.nextInt(needExpandList.size())), Direction.DOWN);
                        needExpandList = new ArrayList<>();
                    }
                }

            }
        }

        // For the last row simply merge every couple of cell that are from different sets
        int lastRow = maze.getDimension()-1;
        for(int col=0; col<maze.getDimension()-1; col++){
            if(compressedMaze[lastRow][col] != compressedMaze[lastRow][col+1]){
                temp = compressedMaze[lastRow][col+1];
                compressedMaze[lastRow][col+1] = compressedMaze[lastRow][col];
                for(int col2=col+2; col2<maze.getDimension(); col2++){
                    if(compressedMaze[lastRow][col2] == temp)
                        compressedMaze[lastRow][col2] = compressedMaze[lastRow][col];
                }
                maze.breakWalls(new Position(lastRow, col), Direction.RIGHT);
            }
        }

    }

    @Override
    public boolean wallSetup() {
        return true;
    }

    @Override
    public String toString() {
        return "Eller's Algorithm";
    }
}
