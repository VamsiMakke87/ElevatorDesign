package org.example.model;

import org.example.util.Direction;

import java.util.Collections;
import java.util.PriorityQueue;

public class Elevator {

    private PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // to process requests while going up
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // to process requests while going down
    private PriorityQueue<Integer> requestProcessingQueue = new PriorityQueue<>(); // to process requests in current direction

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
}
