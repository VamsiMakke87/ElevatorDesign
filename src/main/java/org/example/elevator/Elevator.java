package org.example.elevator;

import org.example.elevator.util.Direction;

public class Elevator {

    private int currentFloor;
    private Direction direction;

    private int maxRequests;

    private int id;

    public Elevator(int id,int maxRequests) {
        this.id = id;
        this.direction = Direction.IDLE;
        this.maxRequests=maxRequests;
    }

    public int getId() {
        return this.id;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }



    public int getRequests() {
        return maxRequests;
    }

    public void addRequest() {
        this.maxRequests = maxRequests-1;
    }
}
