package org.example.Model.MazeGenerator;

import org.example.Model.Direction;
import org.example.Model.Maze;
import org.example.Model.Model;
import org.example.Model.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MultiplePaths implements MazeGenerator{
    @Override
    public void generateMaze(Model model, Maze maze) {
        int dimension = maze.getDimension();
        HashMap<String, Position> startSet = new HashMap<>(), goalSet = new HashMap<>();
        HashMap<Position, Direction> mixedSet = new HashMap<>();
        Random rand = new Random();
        Position pos;
        Direction dir;
        int endCounter = dimension*dimension - 2;

        pos = new Position(0,0);
        maze.setCellAsVisited(pos);
        startSet.put(pos.toString(), pos);

        pos = new Position(dimension-1, dimension-1);
        maze.setCellAsVisited(pos);
        goalSet.put(pos.toString(), pos);

        while(endCounter > 0){
            addNewPos(maze, startSet, goalSet, mixedSet);
            addNewPos(maze, goalSet, startSet, mixedSet);
            endCounter--;
        }

        // The two sets are created, now it' time to add multiple path
        ArrayList<Position> keyList = new ArrayList<>(mixedSet.keySet());
        for(int i=0; i<3; i++){
            pos = keyList.get(rand.nextInt(keyList.size())); // extracted a random position
            dir = mixedSet.get(pos); // got the direction where to destroy the wall
            maze.breakWalls(pos, dir); // created a new path
            keyList.remove(pos);
        }

        maze.createStartEnd();
        maze.print();
    }

    private void addNewPos(Maze maze, HashMap<String, Position> searchSet, HashMap<String, Position> checkSet, HashMap<Position, Direction> mixed){
        Random rand = new Random();
        Position cur, next;
        ArrayList<Position> listPos;
        Direction dir;

        listPos = new ArrayList<>(searchSet.values());
        cur = listPos.get(rand.nextInt(searchSet.size()));     // now I got one of the random cell in the searchSet
        dir = Direction.getDir(rand.nextInt(4));        // now I decided the next direction to check

        // TODO: complete this part, then it's finished
    }

    @Override
    public boolean wallSetup() {
        return true;
    }
}
