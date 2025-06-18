package org.example.model.strategy;

import org.example.model.Elevator;
import org.example.util.Request;

public interface ElevatorSelectionStrategy {

    public Elevator selectOptimalElevator(Request request);

}
