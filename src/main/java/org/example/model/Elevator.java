package org.example.model;

import org.example.util.Direction;

import java.util.Collections;
import java.util.TreeSet;

public class Elevator {

    private TreeSet<Integer> minHeap = new TreeSet<>(); // to process requests while going up
    private TreeSet<Integer> maxHeap = new TreeSet<>(Collections.reverseOrder()); // to process requests while going down
    private TreeSet<Integer> requestProcessingQueue = new TreeSet<>(); // to process requests in current direction

    private int id;
    private Direction direction;
    private int currentFloor;
    private int maxRequests;

    public Elevator(int id, int maxRequests, int currentFloor) {
        this.id = id;
        this.maxRequests = maxRequests;
        this.currentFloor = currentFloor;
        this.direction=Direction.IDLE;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public boolean canServeRequests(){
        return requestProcessingQueue.size()<maxRequests;
    }
}
