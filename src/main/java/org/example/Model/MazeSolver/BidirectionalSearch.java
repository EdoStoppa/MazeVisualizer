package org.example.Model.MazeSolver;

import org.example.Model.Maze;
import org.example.Model.Position;

import java.util.LinkedList;
import java.util.List;

/*
* Algorithm high-level description
*
*
* Bidirectional Search
*
* This algorithm is quite different from the other already implemented algorithms. This is a sort of "wrapper" algorithm, because it uses one of the already implemented search algorithm, but it exploits
* parallelism. In fact, this algorithm is based on a double parallel search: from the start point towards the goal and vice versa. The real problem in this algorithm is the ability to control accurately
* the concurrency of the two search thread. The algorithm end when the 2 threads explore the same tile, meaning that exists a path from start to this tile, and from this tile to the goal. So the final
* solution is the "sum" of the two discovered paths.
*
*/
public class BidirectionalSearch implements MazeSolver{
    private class Controller{
        private boolean searching;
        private LinkedList<Position> solution;

        public Controller(){
            this.searching = false;
            this.solution = new LinkedList<>();
        }

        public LinkedList<Position> getSolution() {
            return this.solution;
        }
        public void setSolution(LinkedList<Position> solution) {
            this.solution = solution;
        }

        public boolean isSearching() {
            return this.searching;
        }
        public void setSearching(boolean searching) {
            this.searching = searching;
        }
    }

    @Override
    public void solveMaze(Maze maze) {
        // TODO: Need to create a better common object to synchronize with and to store the cumulative solution
        Controller controller = new Controller();
        Thread startThread, goalThread;

        startThread = new Thread(() -> {

        });
        startThread.start();

        goalThread = new Thread(() -> {

        });
        goalThread.start();

        try {
            startThread.join();
            goalThread.join();
        } catch (InterruptedException e){
            System.err.println("Error during join of start and goal threads");
        }

        maze.setSolution(controller.getSolution());
    }

    @Override
    public String toString(){
        return "Bidirectional Search Solver";
    }
}
