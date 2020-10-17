package org.example.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum Direction {
    UP(0, new Position(-1,0), "UP"),
    RIGHT(1, new Position(0,1), "RIGHT"),
    DOWN(2, new Position(1,0), "DOWN"),
    LEFT(3, new Position(0,-1), "LEFT");

    private final int directionInt;
    private final Position vector;
    private final String name;

    Direction(int dir, Position vec, String name){
        directionInt = dir;
        vector = vec;
        this.name = name;
    }

    public int getDirectionInt(){
        return directionInt;
    }
    public int getOppositeDirectionInt(){
        return (directionInt +2)%4;
    }
    public Position getVector(){
        return vector.clone();
    }
    public String toString(){
        return name;
    }

    public static Direction getDir(int i){
        switch(i){
            case 0:
                return Direction.UP;
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.DOWN;
            case 3:
                return Direction.LEFT;
            default:
                return Direction.UP;
        }
    }
    public static List<Direction> getAllDir(){
        List<Direction> list = new ArrayList<>();

        list.add(Direction.UP);
        list.add(Direction.RIGHT);
        list.add(Direction.DOWN);
        list.add(Direction.LEFT);

        return list;
    }
    public static Direction nextDir(Direction dir){
        switch(dir){
            case UP:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.DOWN;
            case DOWN:
                return Direction.LEFT;
            case LEFT:
                return Direction.UP;
            default:
                return UP ;
        }

    }
    public static Direction nextDirRand(List<Direction> dir){
        return dir.get((new Random()).nextInt(dir.size()));
    }
}
