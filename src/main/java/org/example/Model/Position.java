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
        int _x = posX + other.getPosX();
        int _y = posY + other.getPosY();

        return (new Position(_x, _y));
    }

    public boolean equals(Position p){
        return (posX==p.getPosX() && posY==p.getPosY());
    }
    public String toString(){
        return this.posX + "," + this.posY;
    }
    public Position clone(){
        return new Position(posX, posY);
    }
}
