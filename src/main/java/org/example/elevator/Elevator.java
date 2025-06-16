package org.example.elevator;

import org.example.elevator.util.Direction;

public class Elevator {

    private static Elevator elevator;

    private int currentFloor;
    private Direction direction;

    private int id;

    public Elevator(int id) {
        this.id = id;
        this.direction = Direction.IDLE;
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
}
