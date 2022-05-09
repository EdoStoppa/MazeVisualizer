<div align="center">
  <img src="https://github.com/EdoStoppa/EdoStoppa/blob/main/imgs/MazeVisualizer/title.png?raw=true" alt="title">
</div><br />

This app will `generate and solve` small mazes using some of the most famous algorithms known in the domain.
It's coded in Java and features a CLI or GUI interface (done with JavaFX). To make this app I used mainly 2 design patterns: `Strategy` and  `MVC`. The strategy
pattern was needed to be able to add, modify, or remove any generation/solving algorithm without modifying any code outside the file needed to implement the
new algorithm. Instead, the MVC pattern was used to manage and coordinate the data, logic, and representation layer of the application.

## Prerequisites
```
- Java 11+
- JavaFX 14.0.1
- Maven 4.0.0
- Junit 5.3.1
```
## How to build and run

### Build
To generate the jar file for the application simply run `mvn package`, and you should see the `MazeVisualizer.jar` app in the main folder. For convenience,
I've already included the complete .jar in the repository.

### Run
To run the app, you can simply double click on it if you have a JRE 11+, or you can run into the terminal the command <br />
`java -jar MazeVisualizer.jar`.

## Maze Generation
<div align="center">
  <img src="https://github.com/EdoStoppa/EdoStoppa/blob/main/imgs/MazeVisualizer/generation.png?raw=true" alt="Maze Generation" width="450" height="350">
</div>

### Iterative Backtracking
This is one of the easiest maze generation algorithms. It's also known as `Randomized Depth-first Search`, and I implemented it using a stack. The idea is pretty simple: having a cursor that explores randomly the board choosing always one of the 4 possible directions (UP, RIGHT, DOWN, LEFT). When a movement from one cell to another is completed, then the wall between these cells is eliminated. If starting from a position all the 4 adjacent cells are already been seen, then the algorithm backtracks to the last previously seen cell. This process goes on until the board is completely explored.

### Randomized Prim
The idea of this algorithm is that creating a `frontier composed of walls`, and gradually expanding it till every cell has been visited, will result in a maze. The pseudocode is the following:
```
1) Start with a grid full of walls.
2) Pick a cell, and mark it as part of the maze. Add the walls of the cell to the wall list.
3) While there are walls in the list:
  3.1) Pick a random wall from the list. If only one of the cells that the wall divides is visited, then:
    3.1.1) Make the wall a passage and mark the unvisited cell as part of the maze.
    3.1.2) Add the neighboring walls of the cell to the wall list.
  3.2) Remove the wall from the list.
```

### Recursive Division
The algorithm works by `recursively dividing the maze space into sub-mazes`. The algorithm starts with a maze with no wall, then it places 2 walls that cover the entire maze length: 1 horizontal and 1 vertical. Then, having divided the original maze into 4 sub-mazes, 3 wall segments are chosen randomly, and in each of these 3 segments, a single wall is eliminated. Then the entire process is repeated for all the created sub-mazes.

### Multiple Path
All the algorithms seen till now create mazes that only have 1 right path that leads to the exit. This algorithm instead creates a maze that has `3 possible paths` that solve the maze. To do that we run 2 consecutive instances of the `Randomized Prim's algorithm` having the first instance starting from the start cell, while the second instance starts from the end cell. Each instance will grow its wall boundaries until remains a cell that has not been visited and can be directly reached by that instance. At the end of the algorithm, we will have 2 sub-mazes that span the entire board and are connected by a single frontier. At that point, we randomly choose 3 walls on that frontier and we eliminate them. In this way, we will have created a maze with 3 possible solving paths.

### Eller's sets
This algorithm is able to generate a maze considering only one row of the board each step and finishes its computation in linear time. It does this by building the maze one row at a time, using sets to keep track of which columns are ultimately connected. Here's an overview of the algorithm:
```
1) Initialize the cells of the first row to each exist in their own set.
2) Now, randomly join adjacent cells, but only if they are not in the same set. When joining adjacent cells, merge the cells
   of both sets into a single set, indicating that all cells in both sets are now connected (there is a path that connects
   any two cells in the set).
3) For each set, randomly create vertical connections down to the next row. Each remaining set must have at least one vertical
   connection. The cells in the next row thus connected must share the set of the cell above them.
4) Flesh out the next row by putting any remaining cells into their own sets.
5) Repeat until the last row is reached.
6) For the last row, join all adjacent cells that do not share a set, and omit the vertical connections, and you’re done!
```
The process can be visualized using these first steps of the algorithm:
```
    ___ ___ ___ ___ ___                    ___________________
   |   |   |   |   |   |                  |           |       |
1) | 1 | 2 | 3 | 4 | 5 |               2) | 1   1   1 | 4   4 |       
   |___|___|___|___|___|                  |___________|_______|


    ___________________                    ___________________
   |           |       |                  |           |       |
   | 1   1   1 | 4   4 |                  | 1   1   1 | 4   4 |
3) |    ___    |___    |               4) |    ___    |___    |
   |   |   |   |   |   |                  |   |   |   |   |   |
   | 1 |   | 1 |   | 4 |                  | 1 | 6 | 1 | 7 | 4 |
   |___|   |___|   |___|                  |___|___|___|___|___|      etc...
```

## Maze Solving
<div align="center">
  <img src="https://github.com/EdoStoppa/EdoStoppa/blob/main/imgs/MazeVisualizer/solving.gif?raw=true" alt="Maze Solving" width="450" height="350">
</div>

### Recursive Solving
This algorithm is the dual of the `Recursive Backtracking algorithm` explained in the previous section. From the start cell, the maze is explored using a
randomized Depth-first search method. When the end cell is reached, the complete path is reconstructed relying on the cells still in the stack.

### Breadth-first Search
This solving method is extremely similar to the previous one but relies on `Breadth-first search` instead of Depth-first search. This algorithm guarantees to 
find the shortest path between the start and end cells.

### A*
This algorithm can be seen as an extension of the Breadth-first search approach. Instead of exploring each cell starting from the oldest ones in the queue, to each inserted cell it's attached a value that indicates what is the `expected cost to reach the goal`. Then the queue is explored using first the cell that has the lowest total + expected cost. In this specific implementation, it's used the `Manhattan Distance` between the current cell and the end cell as expected cost function. Again, this algorithm guarantees to find the shortest path between the start and end cells. Furthermore, if the expected cost function is informative enough, it outperforms the Breadth-first search algorithm.

### Bidirectional Search
This last solving algorithm is more an experiment than a truly new algorithm. The idea is that instead of having only the main thread searching for the end cell from the start cell, `another thread is created that from the end cell searches for the start cell`. In this way, when the two "agents" meet each other, they can `reconstruct the solution by merging their partial paths`. The algorithm used for the search is the Breadth-first search algorithm, and the synchronization between the two threads is achieved using a custom-made object that acts at the same time as a lock and as a controller.


