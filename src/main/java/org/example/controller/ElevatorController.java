package org.example.controller;

import org.example.model.Elevator;
import org.example.model.strategy.ElevatorSelectionStrategy;
import org.example.model.strategy.impl.NearestElevatorStrategy;
import org.example.util.Request;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {

    private List<Request> requests=new ArrayList<>();

    private List<Elevator> elevators=new ArrayList<>();

    private ElevatorSelectionStrategy elevatorSelectionStrategy;

    public ElevatorController() {
        this.elevatorSelectionStrategy= new NearestElevatorStrategy();
    }

    public void setElevatorSelectionStrategy(ElevatorSelectionStrategy elevatorSelectionStrategy) {
        this.elevatorSelectionStrategy = elevatorSelectionStrategy;
    }

    public List<Elevator> getElevators() {
        return elevators;
    }
}
