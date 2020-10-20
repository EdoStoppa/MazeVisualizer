package org.example.Model.MazeSolver;

import org.example.Model.Position;

public class Node {
    private final Position pos;
    private final Node father;

    public Node(Position pos, Node father){
        this.pos = pos;
        this.father = father;
    }

    public Position getPos() {
        return pos;
    }
    public Node getFather(){
        return father;
    }
}
