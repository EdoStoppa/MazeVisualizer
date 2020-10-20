package org.example.Model;

import org.example.Message.Message;
import org.example.Message.Move;
import org.example.Observ.Observer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {
    private class Receiver implements Observer<Message>{
        private Message message;

        @Override
        public void update(Message message) {
            this.message = message;
        }

        public Message getMessage(){
            return this.message;
        }
    }

    Receiver r;
    Maze m;

    @BeforeEach
    void init(){
        r = new Receiver();
        m = new Maze(10, true);
        m.addObserver(r);
    }

    @Test
    void mazeConstructor(){
        // Every wall is up
        m = new Maze(10, true);
        for(int i=0; i<m.getDimension(); i++){
            for(int j=0; j<m.getDimension(); j++){
                checkWalls(new Position(i,j), true);
            }
        }

        // Every internal wall is down + perimeter wall are up
        m = new Maze(10, false);
        for(int i=1; i<m.getDimension()-1; i++){
            for(int j=1; j<m.getDimension()-1; j++){
                checkWalls(new Position(i,j), false);
            }
        }

        // Check correct wall formation of maze's edges
        for(int k=0; k<m.getDimension(); k++){
            Position p1 = new Position(0, k);
            assertTrue(m.canMoveTo(p1, Direction.DOWN), "The wall should be at value: " + true);
            assertEquals(!m.canMoveTo(p1, Direction.RIGHT), k==m.getDimension()-1, "The wall should be at value: " + (k==m.getDimension()-1));
            assertEquals(!m.canMoveTo(p1, Direction.LEFT), k==0, "The wall should be at value: " + (k==0));
            assertFalse(m.canMoveTo(p1, Direction.UP), "The wall should be at value: " + false);

            Position p2 = new Position(m.getDimension()-1, k);
            assertFalse(m.canMoveTo(p2, Direction.DOWN), "The wall should be at value: " + false);
            assertEquals(!m.canMoveTo(p2, Direction.RIGHT), k == m.getDimension()-1, "The wall should be at value: " + (k==m.getDimension()-1));
            assertEquals(!m.canMoveTo(p2, Direction.LEFT), k==0, "The wall should be at value: " + (k==0));
            assertTrue(m.canMoveTo(p2, Direction.UP), "The wall should be at value: " + true);

            Position p3 = new Position(k, 0);
            assertFalse(m.canMoveTo(p3, Direction.LEFT), "The wall should be at value: " + false);
            assertTrue(m.canMoveTo(p3, Direction.RIGHT), "The wall should be at value: " + true);
            assertEquals(!m.canMoveTo(p3, Direction.UP), k==0, "The wall should be at value: " + (k==0));
            assertEquals(!m.canMoveTo(p3, Direction.DOWN), k == m.getDimension()-1, "The wall should be at value: " + (m.getDimension()-1));

            Position p4 = new Position(k, m.getDimension()-1);
            assertFalse(m.canMoveTo(p4, Direction.RIGHT), "The wall should be at value: " + false);
            assertTrue(m.canMoveTo(p4, Direction.LEFT), "The wall should be at value: " + (k==m.getDimension()-1));
            assertEquals(!m.canMoveTo(p4, Direction.UP), k==0, "The wall should be at value: " + (k==0));
            assertEquals(!m.canMoveTo(p4, Direction.DOWN), k == m.getDimension()-1, "The wall should be at value: " + (m.getDimension()-1));
        }
    }


    @Test
    void canMoveToGeneral(){
        Position p;

        p = new Position(0,0);
        checkWalls(p, true);

        p = new Position(0, m.getDimension()-1);
        checkWalls(p, true);

        p = new Position(m.getDimension()-1, 0);
        checkWalls(p, true);

        p = new Position(m.getDimension()-1, m.getDimension()-1);
        checkWalls(p, true);

        p = new Position(3, 5);
        checkWalls(p, true);
    }

    @Test
    void breakWallsBasic(){
        Position currPos = new Position(0,0);
        m.breakWalls(currPos, Direction.DOWN);

        assertTrue(m.canMoveTo(currPos, Direction.DOWN), "This wall should be broken");

        currPos = currPos.add(Direction.DOWN.getVector());

        assertTrue(m.canMoveTo(currPos, Direction.UP), "This wall should be broken");
    }

    void checkWalls(Position p, boolean up){
        assertEquals(!m.canMoveTo(p, Direction.DOWN), up, "The wall should be at value: " + up);
        assertEquals(!m.canMoveTo(p, Direction.RIGHT), up, "The wall should be at value: " + up);
        assertEquals(!m.canMoveTo(p, Direction.LEFT), up, "The wall should be at value: " + up);
        assertEquals(!m.canMoveTo(p, Direction.UP), up, "The wall should be at value: " + up);
    }

}