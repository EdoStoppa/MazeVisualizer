package org.example.Model.MazeSolver;

import org.example.Model.Direction;
import org.example.Model.Maze;
import org.example.Model.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * Algorithm's high-level description (from Wikipedia: https://en.wikipedia.org/wiki/Breadth-first_search)
 *
 * The algorithm implemented is a minor variation of the pure breadth-first search, because instead of generating a spanning tree of the maze,
 * while I explore it I generate a sort of "light" spanning tree. Every node in this tree knows its father but not its children. This is because I
 * don't have an already formed spanning tree + I'm only interested in tracing back all th node involved in finding the goal. Of this tree I store
 * the nodes that are currently the possible goal node, the moment I found out that they aren't the solution I simply forget them
 *
 * Breadth-first search Solver
 * - Initialize the spanning tree using the start position as root
 * - Using a queue (FIFO policy) push the root of the tree
 * - While the queue isn't empty:
 *      - Pop the first element of the queue
 *      - If the element found is the goal:
 *          - End the while
 *      - Else:
 *          - If the current position wasn't already visited:
 *              - For every direction possible:
 *                  - Explore the new cell and if it is acceptable (in the maze and not blocked by a wall):
 *                      - Add the new cell in the queue
 *              - Set the current cell as visited
 * - The last element popped (cur) is the node containing the goal, so while cur isn't null:
 *      - Add to the solution path the current position
 *      - Set cur as the its father
 * - Reverse the obtained Position list (obtaining a list from Start to Goal)
 */
public class BreadthFirstSearch implements MazeSolver{
    @Override
    public void solveMaze(Maze maze) {
        // Generate a spanning tree from start Position. A cell is considered visited only if it was already expanded in the tree
        Node cur=null;
        Position curPos, nextPos, goal = maze.getGoal();
        LinkedList<Node> nodeFifo = new LinkedList<>(); // used to implement a queue with FIFO policy
        List<Direction> dirList;
        List<Position> solution = new ArrayList<>();

        nodeFifo.addLast(new Node(maze.getStart(), null));

        // create the list of possible path to the end
        while(!nodeFifo.isEmpty()){
            cur = nodeFifo.removeFirst();
            curPos = cur.getPos();

            if(curPos.equals(goal)) {
                maze.setCellAsVisited(curPos, true);
                break;
            } else {
                if(!maze.cellWasVisited(curPos)){
                    dirList = Direction.getAllDir();
                    for(Direction dir : dirList){
                        nextPos = curPos.add(dir.getVector());
                        if(maze.isAcceptablePos(nextPos) && maze.canMoveTo(curPos, dir) && !maze.cellWasVisited(nextPos)){
                            nodeFifo.addLast(new Node(nextPos, cur));
                        }
                    }
                    maze.setCellAsVisited(curPos, true);
                }
            }
        }

        // select the shortest path to the end
        while(cur != null){
            solution.add(cur.getPos());
            cur = cur.getFather();
        }

        maze.resetMazeVisited();
        Collections.reverse(solution);
        maze.setSolution(solution);
    }

    @Override
    public String toString(){
        return "Breadth-first Search";
    }
}
