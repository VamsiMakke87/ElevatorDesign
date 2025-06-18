package org.example.model.strategy;

import org.example.model.Elevator;
import org.example.util.Request;

import java.util.List;

public interface ElevatorSelectionStrategy {

    public Elevator selectOptimalElevator(List<Elevator> elevators,Request request);

}
