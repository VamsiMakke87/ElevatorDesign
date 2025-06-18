package org.example.model;

import org.example.controller.ElevatorController;
import org.example.model.button.internal.*;
import org.example.util.Direction;
import org.example.util.Request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class Elevator {

    private TreeSet<Integer> minHeap = new TreeSet<>(); // to process requests while going up
    private TreeSet<Integer> maxHeap = new TreeSet<>(Collections.reverseOrder()); // to process requests while going down
    private TreeSet<Integer> requestProcessingQueue = new TreeSet<>(); // to process requests in current direction
    private TreeSet<Integer> otherQueue = new TreeSet<>(); // pointing to the queue for other direction


    private List<InternalButton> internalFloorButtons = new ArrayList<>();

    private InternalButton fanButton;

    private InternalButton lightButton;

    private InternalButton emergencyStopButton;
    private int id;
    private Direction direction;
    private int currentFloor;
    private int maxRequests;

    private int maxFloors;

    private ElevatorController elevatorController;

    public Elevator(int id, int maxRequests, int currentFloor, int maxFloors, ElevatorController elevatorController) {
        this.id = id;
        this.maxRequests = maxRequests;
        this.currentFloor = currentFloor;
        this.direction = Direction.IDLE;
        this.maxFloors = maxFloors;
        this.elevatorController = elevatorController;

        for (int i = 0; i < maxFloors; i++)
            internalFloorButtons.add(new FloorNumberInternalButton(this, i)); // creating floor number internal buttons
        // Adding fan, light and emergency stop buttons
        fanButton = new FanButton(this);
        lightButton = new LightButton(this);
        emergencyStopButton = new EmergencyStopButton(this);
    }

    public Direction getDirection() {
        return direction;
    }

    private synchronized void setDirection(Direction direction) {
        this.direction = direction;
        if (direction == Direction.UP) {
            requestProcessingQueue = minHeap;
            otherQueue = maxHeap;
        } else {
            requestProcessingQueue = maxHeap;
            otherQueue = minHeap;
        }
    }

    public int getId() {
        return id;
    }

    private void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public boolean canServeRequests() {
        return requestProcessingQueue.size() < maxRequests;
    }

    private void openDoors() {
        System.out.println("Elevator " + id + " doors open in floor " + currentFloor);
    }

    private void closeDoors() {
        System.out.println("Elevator " + id + " doors close in floor " + currentFloor);
    }

    private Integer getNextFloor() {
        return requestProcessingQueue.isEmpty() ? null : requestProcessingQueue.first();
    }

    public InternalButton getInternalFloorButton(int floorNumber) {
        return internalFloorButtons.get(floorNumber);
    }

    public InternalButton getFanButton() {
        return fanButton;
    }

    public InternalButton getEmergencyStopButton() {
        return emergencyStopButton;
    }

    public InternalButton getLightButton() {
        return lightButton;
    }


    public void addRequest(Request request) {

        if (direction == Direction.IDLE) {
            setDirection(request.getDestinationFloor() > currentFloor ? Direction.UP : Direction.DOWN);
            requestProcessingQueue.add(request.getDestinationFloor());
        } else if (request.getDirection() != direction) {
            otherQueue.add(request.getDestinationFloor());
        } else {
            requestProcessingQueue.add(request.getDestinationFloor());
        }
        synchronized (this) {
            System.out.println("Elevator" + id + " notified");
            notifyAll();
        }
    }

    public void processRequests() {

        while (true) {

            while (!requestProcessingQueue.isEmpty()) {
                int nextFloorDirection = direction == Direction.UP ? 1 : -1;
                System.out.println("Elevator " + id + " in floor " + currentFloor);
                if (currentFloor == requestProcessingQueue.first()) {
                    openDoors();
                    try {
                        Thread.sleep(3000); // simulating door opening and closing movement
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    closeDoors();
                    requestProcessingQueue.removeFirst();
                } else {
                    currentFloor += nextFloorDirection;
                }
                try {
                    Thread.sleep(1000);// simulating elevator floor movement
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if(!otherQueue.isEmpty()){
                setDirection(Direction.UP==direction? Direction.DOWN:Direction.UP);
                continue;
            }

            setElevatorToIdle();
            synchronized (this) {
                try {
                    System.out.println("Elevator " + id + " waiting");
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    private void setElevatorToIdle() {
        setDirection(Direction.IDLE);
        elevatorController.notifyController();

    }

    public void emergencyStop() {
        setCurrentFloor(getNextFloor() + 1);// going to the next floor
        openDoors(); // opening doors
        setDirection(Direction.EMERGENCYSTOP); //stopping at that floor
        requestProcessingQueue.clear();  // clearing all the pending requests
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "id=" + id +
                ", direction=" + direction +
                ", currentFloor=" + currentFloor +
                ", requestProcessingQueue=" + requestProcessingQueue +
                '}';
    }
}
