package org.example.Model;

public class Position {
    private final int posX;
    private final int posY;

    public Position(int x, int y){
        this.posX = x;
        this.posY = y;
    }

    public int getPosX(){
        return this.posX;
    }
    public int getPosY(){
        return this.posY;
    }

    public Position add(Position other){
        return new Position(posX + other.getPosX(), posY + getPosY());
    }
    public String toString(){
        return this.posX + "," + this.posY;
    }
}
