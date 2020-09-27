package org.example.Model;

import java.util.ArrayList;
import java.util.List;

public enum Direction {
    UP(0, new Position(0,-1)),
    RIGHT(1, new Position(1,0)),
    DOWN(2, new Position(0,1)),
    LEFT(3, new Position(-1,0));

    private int directionInt;
    private Position vector;

    Direction(int dir, Position vec){
        directionInt = dir;
        vector = vec;
    }

    public int getDirectionInt(){
        return directionInt;
    }
    public int getOppositeDirectionInt(){
        return (directionInt +2)%4;
    }
    public Position getVector(){
        return vector;
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

    public static List<Direction> getAllDirections(){
        List<Direction> listPos = new ArrayList<>();
        listPos.add(UP);
        listPos.add(RIGHT);
        listPos.add(DOWN);
        listPos.add(LEFT);
        return listPos;
    }
}
