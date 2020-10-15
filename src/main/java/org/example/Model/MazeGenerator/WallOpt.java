package org.example.Model.MazeGenerator;

import org.example.Model.Direction;
import org.example.Model.Position;

/*
* This class is special, because it represents a Wall only for some of the generation algorithms
*/
public class WallOpt {
    final Position cellPos;
    final Direction wallDir;

    public WallOpt(Position cellPos, Direction wallDir){
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

    public String toString(){
        return (cellPos.toString() + " -> " + wallDir.toString());
    }
}
