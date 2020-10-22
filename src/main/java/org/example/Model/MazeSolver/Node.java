package org.example.Model.MazeSolver;

import org.example.Model.Position;

public class Node {
    private final Position pos;
    private final int distRoot, distGuess;
    private final Node father;

    public Node(Position pos, Node father){
        this.pos = pos;
        this.father = father;
        this.distRoot = 0;
        this.distGuess = 0;
    }
    public Node(Position pos, Node father, int distRoot){
        this.pos = pos;
        this.father = father;
        this.distRoot = distRoot;
        this.distGuess = 0;
    }
    public Node(Position pos, Node father, int distRoot, int distGuess){
        this.pos = pos;
        this.father = father;
        this.distRoot = distRoot;
        this.distGuess = distGuess;
    }

    public Position getPos() {
        return pos;
    }
    public int getDistRoot() {
        return this.distRoot;
    }
    public int getDistGuess() {
        return this.distGuess;
    }
    public Node getFather(){
        return father;
    }
}
