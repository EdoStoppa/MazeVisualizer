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
        m = new Maze(10);
        m.addObserver(r);
    }

    @Test
    void setCurPosBasic(){
        assertTrue(m.getCurPos().equals(new Position(0,0)), "The starting position is initialized incorrectly");

        m.setCurPos(new Position(1,1));
        assertTrue(m.getCurPos().equals(new Position(1,1)), "The position should be changed");
        assertTrue(((Move)r.getMessage()).getOldPos().equals(new Position(0,0)), "The oldPos received should be the initial pos");
        assertTrue(((Move)r.getMessage()).getNewPos().equals(new Position(1,1)), "The newPos received should be the actual pos");
        assertFalse(((Move) r.getMessage()).getWasVisited(), "The new cell shouldn't be visited");
    }

    @Test
    void canMoveToGeneral(){
        m.setCurPos(new Position(0,0));
        isEveryWallUp();

        m.setCurPos(new Position(0, m.getDimension()-1));
        isEveryWallUp();

        m.setCurPos(new Position(m.getDimension()-1, 0));
        isEveryWallUp();

        m.setCurPos(new Position(m.getDimension()-1, m.getDimension()-1));
        isEveryWallUp();

        m.setCurPos(new Position(3, 5));
        isEveryWallUp();
    }

    @Test
    void breakWallsBasic(){
        Position currPos = m.getCurPos();
        m.breakWalls(currPos, Direction.DOWN);

        assertTrue(m.canMoveTo(currPos, Direction.DOWN), "This wall should be broken");
        m.setCurPos(currPos.add(Direction.DOWN.getVector()));
        assertTrue(m.canMoveTo(m.getCurPos(), Direction.UP), "This wall should be broken");
    }

    void isEveryWallUp(){
        Position currPos = m.getCurPos();
        assertFalse(m.canMoveTo(currPos, Direction.DOWN), "The wall should be up");
        assertFalse(m.canMoveTo(currPos, Direction.RIGHT), "The wall should be up");
        assertFalse(m.canMoveTo(currPos, Direction.LEFT), "The wall should be up");
        assertFalse(m.canMoveTo(currPos, Direction.UP), "The wall should be up");
    }

}