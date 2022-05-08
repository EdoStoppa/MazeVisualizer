<div align="center">
  <img src="https://github.com/EdoStoppa/EdoStoppa/blob/main/imgs/MazeVisualizer/title.png?raw=true" alt="title">
</div>

This app will `generate and solve` small mazes using some of the most famous algorithms known in the domain.
It's coded in Java and features a CLI or GUI interface (done with JavaFX). To make this app I used mainly 2 design patterns: `Strategy` and  `MVC`. The strategy
pattern was needed to be able to add, modify, or remove any generation/solving algorithm whitout modifying any code outside the file needed to implement the
new algorithm. Instead, the MVC pattern was used to manage and coordinate the dat, logic, and representation layer of the application.

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
I've already included the complete .jar ion the repository.

### Run
To run the app, you can simply double click on it if you have a JRE 11+, or you cna run into the terminal the command `java -jar MazeVisualizer.jar`.

## Maze Generation
<div align="center">
  <img src="https://github.com/EdoStoppa/EdoStoppa/blob/main/imgs/MazeVisualizer/generation.png?raw=true" alt="Maze Generation" width="450" height="350">
</div>

### Iterative Backtracking
### Randomized Prim
### Recursive Division
### Multiple Path
### Eller's sets

## Maze Solving
<div align="center">
  <img src="https://github.com/EdoStoppa/EdoStoppa/blob/main/imgs/MazeVisualizer/solving.gif?raw=true" alt="Maze Solving" width="450" height="350">
</div>

### Recursive Solving
### Breadth First Search
### A* Star
### Bidirectional Search



