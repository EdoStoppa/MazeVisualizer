package org.example.Model.MazeGenerator;

import org.example.Model.Direction;
import org.example.Model.Maze;
import org.example.Model.Model;
import org.example.Model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Algorithm's high-level description (from Wikipedia: https://en.wikipedia.org/wiki/Maze_generation_algorithm)
 *
 * Randomized Prim's Algorithm:
 *
 * - Start with a grid full of walls
 * - Pick a cell, mark it as part of the maze. Add the walls of the cell to the wall list
 * - While there are walls in the list:
 *       - Pick a random wall from the list. If only one of the two cells that the wall divides is visited, then:
 *           - Make the wall a passage and mark the unvisited cell as part of the maze
 *           - Add the neighboring walls of the cell to the wall list
 *       - Remove the wall from the list
 */
public class RandomizedPrim implements MazeGenerator{
    /*
    * Private class needed to store all the walls in a list structure
    * */
    private class Wall{
        final Position cellPos;
        final Direction wallDir;

        public Wall(Position cellPos, Direction wallDir){
            this.cellPos = cellPos;
            this.wallDir = wallDir;
        }

        public Position getCellPos() {
            return this.cellPos;
        }
        public Position getAdjacentCellPos() {
            return cellPos.add(wallDir.getVector());
        }
        public Direction getWallDir() {
            return this.wallDir;
        }
    }


    @Override
    public void generateMaze(Model model, Maze maze) {
        List<Wall> wallList = new ArrayList<>();
        Random rand = new Random();
        Position nextPos, curPos = new Position(rand.nextInt(maze.getDimension()), rand.nextInt(maze.getDimension()));
        Wall pickedWall;
        maze.setCellAsVisited(curPos);
        addWalls(maze, curPos, wallList);

        while(!wallList.isEmpty()){
            pickedWall = wallList.get(rand.nextInt(wallList.size()));
            curPos = pickedWall.getCellPos();
            nextPos = pickedWall.getAdjacentCellPos();

            if(maze.isAcceptablePos(nextPos)){
                if(maze.cellWasVisited(curPos) && !maze.cellWasVisited(nextPos)){
                    maze.breakWalls(curPos, pickedWall.getWallDir());
                    maze.setCellAsVisited(nextPos);
                    addWalls(maze, nextPos, wallList);
                }
            }

            wallList.remove(pickedWall);

        }

        maze.createStartEnd();
        maze.print();
    }

    public void addWalls(Maze m, Position p, List<Wall> list){
        for(int i=0; i<4; i++){
            if(!m.canMoveTo(p, Direction.getDir(i)))
                list.add(new Wall(p, Direction.getDir(i)));
        }

    }

}
