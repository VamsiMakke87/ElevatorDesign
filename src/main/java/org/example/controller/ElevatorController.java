package org.example.controller;

import org.example.model.Elevator;
import org.example.model.button.external.DownwardRequestExternalButton;
import org.example.model.button.external.UpwardRequestExternalButton;
import org.example.model.strategy.ElevatorSelectionStrategy;
import org.example.model.strategy.impl.NearestElevatorStrategy;
import org.example.util.Floor;
import org.example.util.Request;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {

    private List<Request> requests = new ArrayList<>();

    private List<Request> pendingRequests=new ArrayList<>();

    private List<Elevator> elevators = new ArrayList<>();
    private List<Floor> floors = new ArrayList<>();

    private int maxFloors = 50;

    private static ElevatorController instance=null;

    private int numberOfElevators;
    private  ElevatorSelectionStrategy elevatorSelectionStrategy;

    private ElevatorController(int maxFloors, int numberOfElevators) {
        this.maxFloors = maxFloors;
        this.numberOfElevators = numberOfElevators;
        this.elevatorSelectionStrategy = new NearestElevatorStrategy();

        for (int i = 0; i < numberOfElevators; i++) {
            Elevator elevator = new Elevator(i, 5, i, maxFloors,this); // Placing elevators in diff floors
            elevators.add(elevator);
            new Thread(elevator::processRequests).start();
        }

        for (int i = 0; i < maxFloors; i++) {
            Floor floor = new Floor(i, new UpwardRequestExternalButton(i,this), new DownwardRequestExternalButton(i,this));
            floors.add(floor);
        }

    }


    private ElevatorController() {
        this.elevatorSelectionStrategy = new NearestElevatorStrategy();
    }

    public static ElevatorController getInstance(int maxFloors, int numberOfElevators){
        if(instance==null){
            instance=new ElevatorController(maxFloors,numberOfElevators);
        }
        return instance;
    }

    public void setElevatorSelectionStrategy(ElevatorSelectionStrategy elevatorSelectionStrategy) {
        this.elevatorSelectionStrategy = elevatorSelectionStrategy;
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public synchronized void addRequest(Request request) {
        requests.add(request);
        notifyAll();

    }


    public synchronized void processRequests() {
        while (true) {
            while (requests.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            while (!requests.isEmpty()) {
                Request currentRequest = requests.removeFirst();
                Elevator elevator = elevatorSelectionStrategy.selectOptimalElevator(elevators, currentRequest);
                System.out.println(currentRequest+";"+elevator);
                if (elevator == null) {
                    pendingRequests.add(currentRequest);
                } else {
                    elevator.addRequest(currentRequest);
                }
            }
        }

    }

    public Elevator getElevator(int elevatorId) {
        return elevators.get(elevatorId);
    }

    public int getMaxFloors() {
        return maxFloors;
    }

    public Floor getFloor(int floorNumber) {
        return floors.get(floorNumber);
    }

    public synchronized void notifyController() {
        if(!pendingRequests.isEmpty()){
            requests.add(pendingRequests.removeFirst());
            notifyAll();
        }
    }
}
