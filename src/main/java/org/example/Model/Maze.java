package org.example.Model;

import org.example.Message.Message;
import org.example.Message.Move;
import org.example.Message.WallBreak;
import org.example.Observ.Observable;

public class Maze extends Observable<Message> {
    private Cell[][] maze;
    private Position curPos;
    private final int dimension;

    public Maze(int dimension){
        this.dimension = dimension;
        this.maze = new Cell[dimension][dimension];
        this.curPos = new Position(0,0);
    }

    public void setStart(){
        Position start = new Position(0,0);
        maze[start.getPosX()][start.getPosY()].breakWall(Direction.UP.getDirectionInt());
        setCellAsVisited(start);
        setCurPos(start);
    }

    //----------- Methods used to interact with the cursor used to explore the maze -----------
    public void setCurPos(Position p){
        Position oldPos = curPos.clone();
        this.curPos = p;
        notify(new Move(oldPos, curPos, cellWasVisited(curPos.clone())));
    }
    public Position getCurPos(){
        return this.curPos.clone();
    }

    //----------- Methods used to interacts with a maze's cell given the position -----------
    public void setCellAsVisited(Position p){
        this.maze[p.getPosX()][p.getPosY()].setAsVisited();
    }
    public boolean cellWasVisited(Position p){
        return this.maze[p.getPosX()][p.getPosY()].hasBeenVisited();
    }
    public void breakWalls(Direction dir) {
        Position oldP = curPos.clone();
        Position nextP = curPos.add(dir.getVector());

        maze[oldP.getPosX()][oldP.getPosY()].breakWall(dir.getDirectionInt());
        maze[nextP.getPosX()][nextP.getPosY()].breakWall(dir.getOppositeDirectionInt());

        notify(new WallBreak());
    }

    //----------- Miscellaneous -----------
    public int getDimension(){
        return dimension;
    }
    public void print(){
        String ORIZ = "\u2500\u2500\u2500\u2500\u2500";
        String VERT = "\u2502";
        String HOLE = "     ";

        System.out.println("\n");
        System.out.println("  \u250C" + ORIZ + "\u2510");
        System.out.println("  " + VERT + HOLE + VERT);
        System.out.println("  \u251C" + ORIZ + "\u2524");
        System.out.println("  " + VERT + HOLE + VERT);
        System.out.println("  \u2514" + ORIZ + "\u2518");
    }
}
