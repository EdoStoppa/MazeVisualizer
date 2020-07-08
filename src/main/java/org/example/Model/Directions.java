package org.example.Model;

import java.util.ArrayList;
import java.util.List;

public enum Directions {
    UP(0, new Position(0,-1)),
    RIGHT(1, new Position(1,0)),
    DOWN(2, new Position(0,1)),
    LEFT(3, new Position(-1,0));

    int direction;
    Position vector;

    Directions(int dir, Position vec){
        direction = dir;
        vector = vec;
    }

    public int getDirection(){
        return direction;
    }
    public Position getVector() {
        return vector;
    }

    public static List<Directions> getAllDirections(){
        List<Directions> listPos = new ArrayList<>();
        listPos.add(UP);
        listPos.add(RIGHT);
        listPos.add(DOWN);
        listPos.add(LEFT);
        return listPos;
    }
}
