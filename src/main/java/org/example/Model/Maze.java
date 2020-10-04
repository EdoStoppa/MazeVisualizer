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

        for(int i=0; i<dimension; i++){
            for(int j=0; j<dimension; j++){
                this.maze[i][j] = new Cell();
            }
        }
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
        notify(new Move(oldPos, curPos, cellWasVisited(p.clone())));
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

        notify(new WallBreak(oldP, nextP, dir));
    }
    public boolean isAcceptablePos(Position pos){
        return (pos.getPosX()<dimension && pos.getPosX()>=0 && pos.getPosY()<dimension && pos.getPosY()>=0) ;
    }
    public boolean canMoveTo(Direction dir){
        Position next = curPos.add(dir.getVector());

        if(isAcceptablePos(next))
            return !maze[curPos.getPosX()][curPos.getPosY()].isWallUp(dir.getDirectionInt());

        return false;
    }

    //----------- Miscellaneous -----------
    public int getDimension(){
        return dimension;
    }
    public void print(){
        String HOLE = "     ";

        //String ORIZ_LONG = "\u2500\u2500\u2500\u2500\u2500";
        String ORIZ = "\u2500";
        String ORIZ_LONG = ORIZ + ORIZ + ORIZ + ORIZ + ORIZ;
        String VERT = "\u2502";

        String MID_RIGHT = "\u251C";
        String MID = "\u253C";
        String MID_LEFT = "\u2524";
        String MID_DOWN = "\u252C";
        String MID_UP = "\u2534";

        String HALF_LEFT = "\u2574";
        String HALF_RIGHT = "\u2576";
        String HALF_TOP = "\u2575";
        String HALF_BOTTOM = "\u2577";


        String C_LEFT_TOP = "\u250C";
        String C_RIGHT_TOP = "\u2510";
        String C_LEFT_BOTTOM = "\u2514";
        String C_RIGHT_BOTTOM = "\u2518";

        String END_BLOCK = ORIZ_LONG + MID_UP;

        System.out.println("\n     \u2193");

        // FIRST LINE
        System.out.print("  " + C_LEFT_TOP);
        System.out.print(HOLE + (maze[0][0].isWallUp(Direction.RIGHT.getDirectionInt()) ? MID_DOWN : ORIZ));
        for(int i=1; i<dimension-1; i++)
            System.out.print(ORIZ_LONG + (maze[0][i].isWallUp(Direction.RIGHT.getDirectionInt()) ? MID_DOWN : ORIZ));
        System.out.println(ORIZ_LONG + C_RIGHT_TOP);

        // MID MAZE
        for(int line=0; line<dimension; line++){
            System.out.print("  " + VERT);
            for(int col1=0; col1<dimension; col1++){
                System.out.print(HOLE + (maze[line][col1].isWallUp(Direction.RIGHT.getDirectionInt()) ? VERT : " "));
            }
            System.out.println();

            if(line < dimension-1){
                System.out.print("  " + (maze[line][0].isWallUp(Direction.DOWN.getDirectionInt()) ? MID_RIGHT : VERT));

                for(int col2=0; col2<dimension-1; col2++){
                    System.out.print((maze[line][col2].isWallUp(Direction.DOWN.getDirectionInt()) ? ORIZ_LONG : HOLE) + chooseMid(line, col2));
                }

                System.out.print((maze[line][dimension-1].isWallUp(Direction.DOWN.getDirectionInt()) ? ORIZ_LONG + MID_LEFT: HOLE + VERT));
                System.out.println();
            }

        }

        // LAST LINE
        System.out.print("  " + C_LEFT_BOTTOM);
        for(int i=0; i<dimension-1; i++){
            System.out.print(ORIZ_LONG + ( maze[dimension-1][i].isWallUp(Direction.RIGHT.getDirectionInt()) ? MID_UP : ORIZ));
        }

        System.out.println(HOLE + C_RIGHT_BOTTOM);
        System.out.print("     ");
        for(int k=0; k<dimension-1; k++)
            System.out.print(HOLE + " ");
        System.out.println( "\u2193\n");
    }
    private String chooseMid(int line, int col){
        boolean left = maze[line][col].isWallUp(Direction.DOWN.getDirectionInt());
        boolean up = maze[line][col].isWallUp(Direction.RIGHT.getDirectionInt());
        boolean right = maze[line][col+1].isWallUp(Direction.DOWN.getDirectionInt());
        boolean down = maze[line+1][col].isWallUp(Direction.RIGHT.getDirectionInt());

        if(left && !up && !right && !down)
            return "\u2574";
        if(left && up && !right && !down)
            return "\u2518";
        if(left && up && right && !down)
            return "\u2534";
        if(left && !up && !right && down)
            return "\u2510";
        if(left && !up && right && down)
            return "\u252C";
        if(left && !up && right && !down)
            return "\u2500";
        if(!left && up && !right && !down)
            return "\u2575";
        if(!left && up && right && !down)
            return "\u2514";
        if(!left && up && right && down)
            return "\u251C";
        if(!left && up && !right && down)
            return "\u2502";
        if(left && up && !right && down)
            return "\u2524";
        if(!left && !up && right && !down)
            return "\u2576";
        if(!left && !up && right && down)
            return "\u250C";
        if(!left && !up && !right && down)
            return "\u2577";


        return "┼";

    }
}
