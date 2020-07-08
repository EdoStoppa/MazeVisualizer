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

    public static List<Direction> getAllDirections(){
        List<Direction> listPos = new ArrayList<>();
        listPos.add(UP);
        listPos.add(RIGHT);
        listPos.add(DOWN);
        listPos.add(LEFT);
        return listPos;
    }
}
