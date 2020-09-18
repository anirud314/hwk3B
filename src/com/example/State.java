package com.example;

import java.util.ArrayList;
import java.util.List;

enum Position {RIGHT, LEFT}

public class State {
    private int cannibalLeft;
    private int cannibalRight;
    private int missionaryLeft;
    private int missionaryRight;
    private Position boat;

    private State parentState; // pointer to the prev/parent state

    public State (int cannibalLeft, int cannibalRight, int missionaryLeft, int missionaryRight, Position boat) {
        this.cannibalLeft = cannibalLeft;
        this.cannibalRight = cannibalRight;
        this.missionaryLeft = missionaryLeft;
        this.missionaryRight = missionaryRight;
        this.boat = boat;
    }

    /*
    public boolean isGoal() {
        return cannibalLeft == 0 && missionaryLeft == 0;
    }
    */

    public boolean isGoal() {
        return cannibalLeft == 0 && missionaryLeft == 0 && boat == Position.LEFT;
    }

    public boolean isValid() {
        if (missionaryLeft >= 0 && missionaryRight >=0 && cannibalLeft >= 0 && cannibalRight >=0
                && (missionaryLeft == 0 || missionaryLeft >= cannibalLeft)
                && (missionaryRight == 0 || missionaryRight >= cannibalRight)) {
            return true;
        }
        return false;
    }

    public List<State> generateSuccessors() {
        List<State> successors = new ArrayList <State>();
        if (boat == Position.LEFT) {

            testAndAdd(successors, new State (cannibalLeft, cannibalRight, missionaryLeft - 2, missionaryRight + 2, Position.RIGHT ));
            testAndAdd(successors, new State (cannibalLeft - 2, cannibalRight + 2, missionaryLeft, missionaryRight, Position.RIGHT ));
            testAndAdd(successors, new State (cannibalLeft - 1, cannibalRight + 1, missionaryLeft, missionaryRight, Position.RIGHT ));
            testAndAdd(successors, new State (cannibalLeft, cannibalRight, missionaryLeft - 1, missionaryRight + 1, Position.RIGHT ));
            testAndAdd(successors, new State (cannibalLeft - 1, cannibalRight + 1, missionaryLeft - 1, missionaryRight + 1, Position.RIGHT ));

            /*
            testAndAdd(successors, new State (cannibalLeft, cannibalRight, missionaryLeft - 2, missionaryRight, Position.RIGHT ));
            testAndAdd(successors, new State (cannibalLeft - 2, cannibalRight, missionaryLeft, missionaryRight, Position.RIGHT ));
            testAndAdd(successors, new State (cannibalLeft - 1, cannibalRight, missionaryLeft, missionaryRight, Position.RIGHT ));
            testAndAdd(successors, new State (cannibalLeft, cannibalRight, missionaryLeft - 1, missionaryRight, Position.RIGHT ));
            testAndAdd(successors, new State (cannibalLeft - 1, cannibalRight, missionaryLeft - 1, missionaryRight, Position.RIGHT ));
            */
        }
        else {
            /*
            testAndAdd(successors, new State (cannibalLeft, cannibalRight, missionaryLeft + 2, missionaryRight - 2, Position.LEFT ));
            testAndAdd(successors, new State (cannibalLeft + 2, cannibalRight - 2, missionaryLeft, missionaryRight, Position.LEFT ));
            testAndAdd(successors, new State (cannibalLeft + 1, cannibalRight - 1, missionaryLeft, missionaryRight, Position.LEFT ));
            testAndAdd(successors, new State (cannibalLeft, cannibalRight, missionaryLeft + 1, missionaryRight - 1, Position.LEFT ));
            testAndAdd(successors, new State (cannibalLeft + 1, cannibalRight - 1, missionaryLeft + 1, missionaryRight - 1, Position.LEFT ));
            */
            testAndAdd(successors, new State (cannibalLeft, cannibalRight, missionaryLeft, missionaryRight, Position.LEFT ));
            /*

            testAndAdd(successors, new State (cannibalLeft, cannibalRight, missionaryLeft, missionaryRight + 2, Position.LEFT ));
            testAndAdd(successors, new State (cannibalLeft, cannibalRight + 2, missionaryLeft, missionaryRight, Position.LEFT ));
            testAndAdd(successors, new State (cannibalLeft, cannibalRight + 1, missionaryLeft, missionaryRight, Position.LEFT ));
            testAndAdd(successors, new State (cannibalLeft, cannibalRight, missionaryLeft, missionaryRight + 1, Position.LEFT ));
            testAndAdd(successors, new State (cannibalLeft, cannibalRight + 1, missionaryLeft, missionaryRight + 1, Position.LEFT ));
            */
        }
        return successors;
    }

    private void testAndAdd(List<State> successors, State newState) {
        if (newState.isValid()) {
            newState.setParentState(this);
            successors.add(newState);
        }
    }

    public int getCannibalLeft() {
        return cannibalLeft;
    }

    public void setCannibalLeft(int cannibalLeft) {
        this.cannibalLeft = cannibalLeft;
    }

    public int getCannibalRight() {
        return cannibalRight;
    }

    public void setCannibalRight(int cannibalRight) {
        this.cannibalRight = cannibalRight;
    }

    public int getMissionaryRight() {
        return missionaryRight;
    }

    public void setMissionaryRight(int missionaryRight) {
        this.missionaryRight = missionaryRight;
    }

    public void goToLeft() {
        boat = Position.LEFT;
    }

    public void goToRight() {
        boat = Position.RIGHT;
    }

    public boolean isOnLeft() {
        return boat == Position.LEFT;
    }

    public boolean isOnRigth() {
        return boat == Position.RIGHT;
    }

    public State getParentState() {
        return parentState;
    }

    public void setParentState(State parentState) {
        this.parentState = parentState;
    }

    @Override
    public String toString() {
        if (boat == Position.LEFT) {
            return "(" + cannibalLeft + "," + missionaryLeft + ",L,"
                    + cannibalRight + "," + missionaryRight + ")";
        } else {
            return "(" + cannibalLeft + "," + missionaryLeft + ",R,"
                    + cannibalRight + "," + missionaryRight + ")";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof State)) {
            return false;
        }
        State s = (State) obj;
        return (s.cannibalLeft == cannibalLeft && s.missionaryLeft == missionaryLeft
                && s.boat == boat && s.cannibalRight == cannibalRight
                && s.missionaryRight == missionaryRight);
    }
}
