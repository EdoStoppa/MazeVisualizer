package org.example.Model.MazeSolver.Tree;

import org.example.Model.Position;

public class Node {
    private final Position pos;
    private final Node father;
    private final Node[] leafs;

    public Node(Position pos, Node father){
        this.pos = pos;
        this.father = father;
        this.leafs = new Node[4];
    }

    public Position getPos() {
        return pos;
    }
    public Node getFather(){
        return father;
    }
}
