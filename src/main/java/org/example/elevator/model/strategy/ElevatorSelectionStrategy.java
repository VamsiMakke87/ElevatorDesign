package org.example.elevator.model.strategy;

import org.example.elevator.Elevator;
import org.example.elevator.util.Request;

import java.util.List;

public interface ElevatorSelectionStrategy {

    public Elevator getOptimalElevator(List<Elevator> elevators, Request request);

}
