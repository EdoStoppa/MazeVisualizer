package org.example.Model;

public class Cell {
    public final static int UP=0, RIGHT=1, DOWN=2, LEFT=3;

    private boolean[] walls;
    private Cursor cur;
    private boolean visited;

    public Cell(){
        this.walls = new boolean[4];
        for(int i=0; i<4; i++)
            this.walls[i] = true;

        this.cur = null;
        this.visited = false;
    }

    //----------- Methods to interacts with the contained cursor -----------
    public void setCur(Cursor c){
        this.cur = c;
    }
    public Cursor getCur(){
        return this.cur;
    }

    //----------- Methods to check/change if the cell was already visited -----------
    public void setAsVisited(){
        this.visited = true;
    }
    public boolean hasBeenVisited(){
        return this.visited;
    }

    //----------- Methods manage all the walls -----------
    public void brokeWall(int wall){
        this.walls[wall] = false;
    }
    public boolean isWallUp(int wall) {
        return this.walls[wall];
    }
}
