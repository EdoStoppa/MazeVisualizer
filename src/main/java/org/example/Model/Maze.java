package org.example.Model;

import org.example.Message.Message;
import org.example.Message.Visited;
import org.example.Message.WallBreak;
import org.example.Message.WallRaise;
import org.example.Model.MazeGenerator.*;
import org.example.Model.MazeSolver.*;
import org.example.Observ.Observable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Maze extends Observable<Message> {
    private final Cell[][] maze;
    private final int dimension;
    private Position start, goal;
    private List<Position> solution;

    public Maze(int dimension, boolean isWallsUp){
        this.dimension = dimension;
        this.start = new Position(0,0);
        this.goal = new Position(dimension-1, dimension-1);
        this.maze = new Cell[dimension][dimension];
        this.solution = null;

        for(int i=0; i<dimension; i++){
            for(int j=0; j<dimension; j++){
                this.maze[i][j] = new Cell(isWallsUp);

                if(!isWallsUp){
                    if(i==dimension-1){
                        this.maze[i][j].raiseWall(Direction.DOWN.getDirectionInt());
                    }
                    if(j==dimension-1){
                        this.maze[i][j].raiseWall(Direction.RIGHT.getDirectionInt());
                    }
                }
            }
        }

    }

    public void createStartEnd(){
        maze[0][0].breakWall(Direction.UP.getDirectionInt());
        maze[dimension-1][dimension-1].breakWall(Direction.UP.getDirectionInt());
    }
    public Position getStart() {
        return this.start.clone();
    }
    public Position getGoal() {
        return this.goal.clone();
    }
    public void setStart(Position p) {
        this.start = p.clone();
    }
    public void setGoal(Position p) {
        this.goal = p.clone();
    }

    //----------- Methods used to interacts with a maze's cell given the position and/or movement direction -----------
    public void setCellAsVisited(Position p, boolean sendNotify){
        this.maze[p.getPosX()][p.getPosY()].setAsVisited();
        if(sendNotify)
            notify(new Visited(p.clone()));
    }
    public boolean cellWasVisited(Position p){
        return this.maze[p.getPosX()][p.getPosY()].hasBeenVisited();
    }
    public void breakWalls(Position pos, Direction dir) {
        Position oldP = pos.clone();
        Position nextP = pos.add(dir.getVector());

        maze[oldP.getPosX()][oldP.getPosY()].breakWall(dir.getDirectionInt());
        maze[nextP.getPosX()][nextP.getPosY()].breakWall(dir.getOppositeDirectionInt());

        notify(new WallBreak(oldP, nextP, dir));
    }
    public boolean isAcceptablePos(Position pos){
        return (pos.getPosX()<dimension && pos.getPosX()>=0 && pos.getPosY()<dimension && pos.getPosY()>=0) ;
    }
    public boolean canMoveTo(Position pos, Direction dir){
        Position next = pos.add(dir.getVector());

        if(isAcceptablePos(next))
            return !maze[pos.getPosX()][pos.getPosY()].isWallUp(dir.getDirectionInt());

        return false;
    }
    public void raiseWall(int x, int y, Direction wall){
        maze[x][y].raiseWall(wall.getDirectionInt());
        notify(new WallRaise(new Position(x,y), wall));
    }
    public void resetMazeVisited(){
        for(int i=0; i<dimension; i++){
            for(int j=0; j<dimension; j++){
                maze[i][j].resetVisited();
            }
        }
    }

    //----------- Methods to get which generator and which solver are available -----------
    static public List<MazeGenerator> getAllGenerators(){
        List<MazeGenerator> generatorList = new ArrayList<>();

        // Add all implemented generators
        generatorList.add(new IterativeBacktracking());
        generatorList.add(new RandomizedPrim());
        generatorList.add(new RecursiveDivision());
        generatorList.add(new MultiplePaths());
        generatorList.add(new EllerSets());

        return generatorList;
    }
    static public List<MazeSolver> getAllSolvers(){
        List<MazeSolver> solverList = new ArrayList<>();

        // Add all implemented solvers
        solverList.add(new RecursiveBasic());
        solverList.add(new BreadthFirstSearch());
        solverList.add(new AStar());
        solverList.add(new BidirectionalSearch());

        return solverList;
    }

    //----------- Miscellaneous -----------
    public int getDimension(){
        return dimension;
    }
    public void setSolution(List<Position> solution){
        this.solution = solution;
    }
    public List<Position> getSolution(){
        return this.solution;
    }

    public void print(){
        String HOLE = "     ";
        String HOLE_FOUND = "  X  ";
        String needed;
        HashMap<String, Position> posMap = null;

        String ORIZ = "\u2500";
        String ORIZ_LONG = ORIZ + ORIZ + ORIZ + ORIZ + ORIZ;

        String VERT = "\u2502";
        String MID_RIGHT = "\u251C";
        String MID_LEFT = "\u2524";
        String MID_DOWN = "\u252C";
        String MID_UP = "\u2534";

        String C_LEFT_TOP = "\u250C";
        String C_RIGHT_TOP = "\u2510";
        String C_LEFT_BOTTOM = "\u2514";
        String C_RIGHT_BOTTOM = "\u2518";

        if(solution != null){
            posMap = new HashMap<>();
            for(Position p : solution)
                posMap.put(p.toString(), p);
        }

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
                if(posMap != null)
                    needed = posMap.containsKey(new Position(line, col1).toString())? HOLE_FOUND : HOLE;
                else
                    needed = HOLE;
                System.out.print(needed + (maze[line][col1].isWallUp(Direction.RIGHT.getDirectionInt()) ? VERT : " "));
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
        for(int i=0; i<dimension-1; i++)
            System.out.print(ORIZ_LONG + ( maze[dimension-1][i].isWallUp(Direction.RIGHT.getDirectionInt()) ? MID_UP : ORIZ));

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
