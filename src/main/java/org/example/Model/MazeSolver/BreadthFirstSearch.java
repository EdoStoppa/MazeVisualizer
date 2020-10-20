package org.example.Model.MazeSolver;

import org.example.Model.Direction;
import org.example.Model.Maze;
import org.example.Model.MazeSolver.Tree.Node;
import org.example.Model.Model;
import org.example.Model.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BreadthFirstSearch implements MazeSolver{
    @Override
    public void solveMaze(Model model, Maze maze) {
        // Generate a spanning tree from  position (0,0). A cell is considered visited only if it was already expanded in the tree
        Node cur=null, root = new Node(new Position(0,0), null);
        Position curPos, nextPos, goal = new Position(maze.getDimension()-1, maze.getDimension()-1);
        LinkedList<Node> nodeFifo = new LinkedList<>(); // used to implement a queue with FIFO policy
        List<Direction> dirList;
        List<Position> solution = new ArrayList<>();

        maze.resetMazeVisited();

        nodeFifo.addLast(root);

        // create the list of possible path to the end
        while(!nodeFifo.isEmpty()){
            cur = nodeFifo.removeFirst();
            curPos = cur.getPos();

            if(curPos.equals(goal))
                break;
            else {
                if(!maze.cellWasVisited(curPos)){
                    dirList = Direction.getAllDir();
                    for(Direction dir : dirList){
                        nextPos = curPos.add(dir.getVector());
                        if(maze.isAcceptablePos(nextPos) && maze.canMoveTo(curPos, dir)){
                            nodeFifo.addLast(new Node(nextPos, cur));
                        }
                    }
                    maze.setCellAsVisited(curPos);
                }
            }
        }

        // select the shortest path to the end
        while(cur != null){
            solution.add(cur.getPos());
            cur = cur.getFather();
        }

        Collections.reverse(solution);
        maze.setSolution(solution);
        maze.print();

    }

    @Override
    public String toString(){
        return "Breadth-first Search Solver";
    }
}
