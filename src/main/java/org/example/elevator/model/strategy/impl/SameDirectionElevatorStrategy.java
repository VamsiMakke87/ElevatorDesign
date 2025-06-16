package org.example.elevator.model.strategy.impl;

import org.example.elevator.Elevator;
import org.example.elevator.model.strategy.ElevatorSelectionStrategy;
import org.example.elevator.util.Direction;
import org.example.elevator.util.Request;

import java.util.List;

public abstract class SameDirectionElevatorStrategy implements ElevatorSelectionStrategy {
    @Override
    public Elevator getOptimalElevator(List<Elevator> elevators, Request request) {

        Direction requestDirection = request.getDirection();

        List<Elevator> optimalElevators = elevators.stream()
                .filter(elevator ->
                        (elevator.getDirection() == Direction.IDLE || elevator.getDirection() == requestDirection)
                                && elevator.getRequests() > 0)
                .toList();

        int max = Integer.MAX_VALUE;
        Elevator optimalElevator = optimalElevators.isEmpty() ? null : optimalElevators.get(0);
        return optimalElevator;

    }
}
