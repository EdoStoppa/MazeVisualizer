package org.example.Model.MazeSolver;

import org.example.Model.Direction;
import org.example.Model.Maze;
import org.example.Model.Position;
import org.example.Observ.Observer;

import java.lang.module.ResolutionException;
import java.util.*;

/*
* Algorithm high-level description
*
*
* Bidirectional Search
*
* This algorithm is quite different from the other already implemented algorithms. This is a sort of "wrapper" algorithm, because it uses one of the already implemented search algorithm (in this case breadth-first),
* but it exploits parallelism. In fact, this algorithm is based on a double parallel search: from the start point towards the goal and vice versa. The real problem in this algorithm is the ability to control
* accurately the concurrency of the two search thread. The algorithm end when the 2 threads explore the same tile, meaning that exists a path from start to this tile, and from this tile to the goal. So the final
* solution is the "sum" of the two discovered paths.
*
*/
public class BidirectionalSearch implements MazeSolver{
    private class MiddleMan{
        public final Object lock;
        private boolean searching;
        private Position meetingPos;

        public MiddleMan() {
            this.lock = new Object();
            this.searching = true;
        }

        public boolean isSearching() {
            return this.searching;
        }
        public void stopSearch() {
            this.searching = false;
        }

        public Position getMeetingPos() {
            return this.meetingPos;
        }
        public void setMeetingPos(Position meetingPos) {
            this.meetingPos = meetingPos;
        }
    }

    @Override
    public void solveMaze(Maze maze) {
        MiddleMan middleMan = new MiddleMan();

        Thread startThread, goalThread;
        List<Position> completeSolution = new ArrayList<>();

        startThread = new Thread(() -> {
            Node node = searchMaze(maze, true, middleMan);
            List<Position> solution = generateSolution(node, true);
            mergeSolutions(completeSolution, solution, true);
        });
        startThread.start();

        goalThread = new Thread(() -> {
            Node node = searchMaze(maze, false, middleMan);
            List<Position> solution = generateSolution(node, false);
            mergeSolutions(completeSolution, solution, false);
        });
        goalThread.start();

        try {
            startThread.join();
            goalThread.join();
        } catch (InterruptedException e){
            System.err.println("Error during join of start and goal threads");
        }

        maze.resetMazeVisited();
        maze.setSolution(completeSolution);
    }

    private Node searchMaze(Maze maze, boolean id, MiddleMan middleMan){
        Node cur;
        Position start = (id ? maze.getStart() : maze.getGoal());
        Position goal = (id ? maze.getGoal() : maze.getStart());

        Position curPos, nextPos;
        HashMap<String, Node> nodeMap = new HashMap<>();

        LinkedList<Node> nodeFifo = new LinkedList<>();
        List<Direction> dirList;

        nodeMap.put(start.toString(), null);
        nodeFifo.addLast(new Node(start, null));

        // create the list of possible path to the end
        while(middleMan.isSearching()){
            synchronized (middleMan.lock) {
                if(middleMan.getMeetingPos() == null) {
                    cur = nodeFifo.removeFirst();
                    curPos = cur.getPos();

                    if (curPos.equals(goal)) {
                        maze.setCellAsVisited(curPos, true);
                        middleMan.setMeetingPos(curPos.clone());
                        middleMan.stopSearch();
                        return cur;
                    }


                    dirList = Direction.getAllDir();

                    for (Direction dir : dirList) {
                        nextPos = curPos.add(dir.getVector());
                        if (maze.isAcceptablePos(nextPos) && maze.canMoveTo(curPos, dir)) {

                            if (maze.cellWasVisited(nextPos)) {
                                if (!nodeMap.containsKey(nextPos.toString())) {
                                    maze.setCellAsVisited(curPos, true);
                                    middleMan.setMeetingPos(nextPos.clone());
                                    middleMan.stopSearch();
                                    return new Node(nextPos, cur);
                                }
                            } else {
                                nodeMap.put(nextPos.toString(), new Node(nextPos, cur));
                                nodeFifo.addLast(new Node(nextPos, cur));
                            }

                        }
                    }

                    maze.setCellAsVisited(curPos, true);
                }
            }

        }

        if(!middleMan.getMeetingPos().equals(start))
            return nodeMap.get(middleMan.getMeetingPos().toString());

        return null;
    }

    public List<Position> generateSolution(Node cur, boolean isFirstHalf){
        LinkedList<Position> solution = new LinkedList<>();

        while(cur != null){
            if(isFirstHalf)
                solution.addFirst(cur.getPos());
            else
                solution.addLast(cur.getPos());

            cur = cur.getFather();
        }

        return solution;
    }

    public synchronized void mergeSolutions(List<Position> completeSolution, List<Position> solution, boolean isFromStart){
        if(isFromStart){
            solution.addAll(completeSolution);
            completeSolution.clear();
        }
        completeSolution.addAll(solution);
    }

    @Override
    public String toString(){
        return "Bidirectional Search";
    }
}
