package org.example.model.strategy.impl;

import org.example.controller.ElevatorController;
import org.example.model.Elevator;
import org.example.model.strategy.ElevatorSelectionStrategy;
import org.example.util.Direction;
import org.example.util.Request;

import java.util.List;

public class NearestElevatorStrategy implements ElevatorSelectionStrategy {

    private ElevatorController elevatorController=new ElevatorController();
    @Override
    public Elevator selectOptimalElevator(Request request) {
        List<Elevator> elevators=elevatorController.getElevators();

        List<Elevator> optimalElevators= elevators.stream()
                .filter(elevator ->
                    (elevator.getDirection()== Direction.IDLE || elevator.getDirection()==request.getDirection())
                ).toList();

        Elevator opitmalElevator=null;
        int minimumDistanceToRequest=1000; // assuming 1000 as max value because no building will have 1000 floors

        for(Elevator elevator: optimalElevators){

            int distance= Math.abs(request.getSourceFloor()-elevator.getCurrentFloor());
            if(distance<minimumDistanceToRequest){
                minimumDistanceToRequest=distance;
                opitmalElevator=elevator;
            }
        }
        return opitmalElevator;

    }
}
