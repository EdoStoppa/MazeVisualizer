package org.example.Model.MazeGenerator;

import org.example.Model.Direction;
import org.example.Model.Maze;
import org.example.Model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
* Algorithm's high-level description (from "The Buckblog": https://weblog.jamisbuck.org/2010/12/29/maze-generation-eller-s-algorithm)
*
*
*/
public class EllerSets implements MazeGenerator{
    @Override
    public void generateMaze(Maze maze) {
        final int max = maze.getDimension();
        int lastId = 0;
        Integer toKill;
        List<Integer> aliveSets = new ArrayList<>();
        Random rand = new Random();

        // Generate a sort of compressed representation of the maze that's keeping track of which set is a particular cell part of.
        int[][] compressedMaze = new int[maze.getDimension()][maze.getDimension()];
        for(int i=0; i<maze.getDimension(); i++) { for (int j = 0; j < maze.getDimension(); j++) { compressedMaze[i][j] = 0; } }

        // The algorithm starts really here
        for(int row=0; row<max; row++){

            // First thing is to assign to every cell a set, so if the cell isn't any of the already present sets, create a new one)
            for(int col=0; col<max; col++){
                if(compressedMaze[row][col] == 0){
                    lastId++;
                    compressedMaze[row][col] = lastId;
                    aliveSets.add(lastId);
                }
            }

            // Then randomly (50% probability) merge two adjacent sets if they're different, and in doing so break the wall between the two cell involved
            for(int col=0; col<max-1; col++){
                if(rand.nextBoolean()){
                    if(compressedMaze[row][col] != compressedMaze[row][col+1]){
                        toKill = compressedMaze[row][col+1];
                        compressedMaze[row][col+1] = compressedMaze[row][col];
                        aliveSets.remove(toKill);
                        maze.breakWalls(new Position(row, col), Direction.RIGHT);
                    }
                }
            }

            // Now expand the corridor going one row down at least once per sets (expansion's probability is 30%)
            if(row != max-1){
                for(int col=0; col<max-1; col++){
                    if(rand.nextInt(10)<3){
                        toKill = compressedMaze[row][col];
                        aliveSets.remove(toKill);
                        compressedMaze[row+1][col] = compressedMaze[row][col];
                        maze.breakWalls(new Position(row, col), Direction.DOWN);
                    }
                }

                if(!aliveSets.isEmpty()){

                    for(int i : aliveSets){
                        // TODO: No luck my friend, you have to expand all the remaining sets :(
                    }
                }
            }

        }

        maze.createStartEnd();

    }

    @Override
    public boolean wallSetup() {
        return true;
    }
}
