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

    private List<Request> requests=new ArrayList<>();

    private List<Elevator> elevators=new ArrayList<>();
    private List<Floor> floors=new ArrayList<>();

    private int maxFloors= 50;

    private int numberOfElevators;
    private ElevatorSelectionStrategy elevatorSelectionStrategy;

    public ElevatorController(int maxFloors, int numberOfElevators) {
        this.maxFloors = maxFloors;
        this.numberOfElevators = numberOfElevators;

        for(int i=0;i<numberOfElevators;i++){
            Elevator elevator=new Elevator(i,5,0,maxFloors);
            elevators.add(elevator);
        }

        for (int i=0;i<maxFloors;i++){
            Floor floor=new Floor(i,new UpwardRequestExternalButton(i),new DownwardRequestExternalButton(i));
            floors.add(floor);
        }

    }

    public ElevatorController() {
        this.elevatorSelectionStrategy= new NearestElevatorStrategy();
    }

    public void setElevatorSelectionStrategy(ElevatorSelectionStrategy elevatorSelectionStrategy) {
        this.elevatorSelectionStrategy = elevatorSelectionStrategy;
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public void addRequest(Request request){
        requests.add(request);
    }

    public int getMaxFloors() {
        return maxFloors;
    }
}
