package org.example.Model;

public class Cell {
    public final static int UP=0, RIGHT=1, DOWN=2, LEFT=3;

    private boolean[] walls;
    private boolean visited;

    public Cell(boolean b){
        this.walls = new boolean[4];
        for(int i=0; i<4; i++)
            this.walls[i] = b;

        this.visited = false;
    }

    //----------- Methods used to check/change if the cell was already visited -----------
    public void setAsVisited(){
        this.visited = true;
    }
    public boolean hasBeenVisited(){
        return this.visited;
    }

    //----------- Methods used to manage all the walls -----------
    public void breakWall(int wall){
        this.walls[wall] = false;
    }
    public void raiseWall(int wall) {
        this.walls[wall] = true;
    }
    public boolean isWallUp(int wall) {
        return this.walls[wall];
    }
}
