package org.example.elevator.util;

public class Request {

    private int source;

    private int destination;

    private Direction direction;

    public Request(int source, int destination) {
        this.source = source;
        this.destination = destination;
        this.direction=source > destination ? Direction.DOWN : Direction.UP;
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }
}
