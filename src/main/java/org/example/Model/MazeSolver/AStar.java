package org.example.Model.MazeSolver;

import org.example.Model.Direction;
import org.example.Model.Maze;
import org.example.Model.Position;

import java.util.*;

/*
* Algorithm high-level description (from Wikipedia: https://en.wikipedia.org/wiki/A*_search_algorithm)
*
* All the magic of the Algorithm is based on a simple formula:
*       f(n) = g(n) + h(n)
* Where g(n) is the distance from the root (= start) of the maze, and h(n) is the Manhattan distance from the goal.
* f(n) represents a guess about how much long is the path from start to goal if we follow the path from start to the current position + the remaining
* unknown path to the goal. We will chose which will be the next node to be expanded from the frontier of the graph (that is the maze) based on the value of f(n) for every node.
*
* A* Solver
* - Using a priority queue push the start node
* - While the queue isn't empty:
*       - Pop the first element of the queue
*       - If the element found is the goal:
*           - End the while
*       - Else:
*           - If the current position wasn't already visited:
*               - For every direction possible:
*                   - Explore the new cell and if it is acceptable (in the maze and not blocked by a wall):
*                       - Add the new cell in the queue specifying the distance from start and the manhattan distance from the goal
*               - Set the current cell as visited
* - The last element popped (cur) is the node containing the goal, so while cur isn't null:
*      - Add to the solution path the current position
*      - Set cur as the its father
*/
public class AStar implements MazeSolver{
    private static class NodeComparator implements Comparator<Node>{
        @Override
        public int compare(Node o1, Node o2) {
            int n1 = o1.getDistRoot()+o1.getDistGuess(), n2 = o2.getDistRoot()+o2.getDistGuess();

            if(n1 == n2)
                return 0;

            return (n1 > n2 ? 1 : -1);
        }
    }

    @Override
    public void solveMaze(Maze maze) {
        Queue<Node> nodeQueue = new PriorityQueue<>(new NodeComparator());
        Node cur = null;
        Position curPos, nextPos, goal = maze.getGoal();
        List<Direction> dirList;
        LinkedList<Position> solution = new LinkedList<>();

        maze.resetMazeVisited();

        nodeQueue.add(new Node(maze.getStart(), null, 0, manhattanDist(maze.getStart(), goal.clone())));

        while(!nodeQueue.isEmpty()){
            cur = nodeQueue.poll();
            curPos = cur.getPos();

            if(curPos.equals(goal))
                break;
            else {
                if(!maze.cellWasVisited(curPos)){
                    dirList = Direction.getAllDir();
                    for(Direction dir : dirList){
                        nextPos = curPos.add(dir.getVector());
                        if(maze.isAcceptablePos(nextPos) && maze.canMoveTo(curPos, dir)){
                            nodeQueue.add(new Node(nextPos, cur, rootDist(cur), manhattanDist(curPos, goal.clone())));
                        }
                    }
                    maze.setCellAsVisited(curPos);
                }
            }
        }

        while(cur != null){
            solution.addFirst(cur.getPos());
            cur = cur.getFather();
        }

        maze.setSolution(solution);
    }

    private int rootDist(Node father){
        return father.getDistRoot()+1;
    }
    private int manhattanDist(Position pos, Position goal){
        return (Math.abs(pos.getPosX() - goal.getPosX()) + Math.abs(pos.getPosY() - goal.getPosY()));
    }

    @Override
    public String toString(){
        return "A* Search Solver";
    }
}
