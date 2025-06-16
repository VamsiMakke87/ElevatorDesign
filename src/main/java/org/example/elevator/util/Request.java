package org.example.elevator.util;

public class Request {

    private int source;

    private int destination;

    public Request(int source, int destination) {
        this.source = source;
        this.destination = destination;
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public Direction getDirection() {
        return source > destination ? Direction.DOWN : Direction.UP;
    }
}
