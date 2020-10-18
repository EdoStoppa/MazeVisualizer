package org.example.Model.MazeGenerator;

import org.example.Model.Direction;
import org.example.Model.Maze;
import org.example.Model.Model;
import org.example.Model.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MultiplePaths implements MazeGenerator{
    @Override
    public void generateMaze(Model model, Maze maze) {

        int dimension = maze.getDimension();
        HashMap<String, Position> startSet = new HashMap<>(), goalSet = new HashMap<>();
        HashMap<String, WallOpt> mixedSet = new HashMap<>();
        Random rand = new Random();
        Position pos;
        String wallString;
        WallOpt wall;
        int endCounter = dimension*dimension - 2;

        pos = new Position(rand.nextInt(maze.getDimension()),rand.nextInt(maze.getDimension()));
        maze.setCellAsVisited(pos);
        startSet.put(pos.toString(), pos);

        pos = new Position(rand.nextInt(maze.getDimension()), rand.nextInt(maze.getDimension()));
        maze.setCellAsVisited(pos);
        goalSet.put(pos.toString(), pos);

        while(endCounter > 0){
            if(!addNewPos(maze, startSet, goalSet, mixedSet))
                endCounter--;

            if(!addNewPos(maze, goalSet, startSet, mixedSet))
                endCounter--;
        }

        // The two sets are created, now it' time to add multiple path
        ArrayList<String> keyList = new ArrayList<>(mixedSet.keySet());
        for(int i=0; i<3; i++){
            wallString = keyList.get(rand.nextInt(keyList.size())); // extracted a random position
            wall = mixedSet.get(wallString); // got the direction where to destroy the wall
            maze.breakWalls(wall.getCellPos(), wall.getWallDir()); // created a new path
            keyList.remove(wallString);
        }

        maze.createStartEnd();
    }

    private boolean addNewPos(Maze maze, HashMap<String, Position> searchSet, HashMap<String, Position> checkSet, HashMap<String, WallOpt> mixed){
        Random rand = new Random();
        Position cur, next;
        ArrayList<Position> listPos;
        Direction dir;
        boolean searching = true;
        List<Direction> dirList;

        listPos = new ArrayList<>(searchSet.values());

        // Try to find a new position to add in the searchSet, until something is found or no position is available
        while(!listPos.isEmpty() && searching){
            dirList = Direction.getAllDir();                     // Get all direction to ensure randomness
            cur = listPos.get(rand.nextInt(listPos.size()));     // Extract a random cell in the searchSet
            dir = Direction.getDir(rand.nextInt(4));      // Decided the first direction to check
            next = cur.add(dir.getVector()); // got the first new position to check

            // if the position isn't in the searchSet (the same as the cur) and it isn't in the checkSet (the "enemy" set)
            // then a new position is found -> add it to searchSet, break all the walls and set it as visited.
            // In the end, check if any of the new position's walls is adjacent to some position in checkSet
            for(int i=0; i<4; i++){
                if(maze.isAcceptablePos(next)){
                    if(!searchSet.containsKey(next.toString())) {
                        if (!checkSet.containsKey(next.toString())) {
                            maze.breakWalls(cur, dir);
                            maze.setCellAsVisited(next);
                            searchSet.put(next.toString(), next);
                            searching = false;
                            addBorder(next, checkSet, mixed);
                            break;
                        }
                    }
                }

                // these are useful only if the next pos isn't good
                dir = Direction.nextDirRand(dirList);
                dirList.remove(dir);
                next = cur.add(dir.getVector());
            }

            // this is useful only when no new position is found for every direction of cur
            // At the cycle's end, we remove the position from the list to let the algorithm converge
            listPos.remove(cur);

        }

        return searching;

    }

    private void addBorder(Position pos, HashMap<String, Position> checkSet, HashMap<String, WallOpt> mixed){
        Direction dir = Direction.UP;
        Position other;
        WallOpt wall;

        for(int i=0; i<4; i++){
            other = pos.add(dir.getVector());

            if(checkSet.containsKey(other.toString())){
                wall = new WallOpt(pos, dir);
                if(!mixed.containsKey(wall.toString()))
                    mixed.put(wall.toString(), wall);
            }

            dir = Direction.nextDir(dir);
        }
    }

    @Override
    public boolean wallSetup(){
        return true;
    }

    @Override
    public String toString(){
        return "Multiple Paths Algorithm";
    }
}
