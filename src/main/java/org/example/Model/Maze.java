package org.example.Model;

import org.example.Observ.Observable;

public class Maze extends Observable<Move> {
    private Cell[][] maze;
    private Position curPos;

    public Maze(int dimension){
        this.maze = new Cell[dimension][dimension];
        this.curPos = new Position(0,0);
    }

    public void setStart(){
        Position start = new Position(0,0);
        setCurPos(start);
        setCellAsVisited(start);
    }

    //----------- Methods used to interact with the cursor used to explore the maze -----------
    public void setCurPos(Position p){
        this.curPos = p;
    }
    public Position getCurPos(){
        return this.curPos;
    }

    //----------- Methods used to interacts with a maze's cell given the position -----------
    public void setCellAsVisited(Position p){
        this.maze[p.getPosX()][p.getPosY()].setAsVisited();
    }
    public boolean cellWasVisited(Position p){
        return this.maze[p.getPosX()][p.getPosY()].hasBeenVisited();
    }
    public int getNextDirection(){
        //this method given the position of the cursor return the next direction randomly
        return 0;
    }
}
