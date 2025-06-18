package org.example.model.button.internal;

import org.example.controller.ElevatorController;
import org.example.model.Elevator;
import org.example.util.Direction;
import org.example.util.Request;

public class FloorNumberInternalButton extends InternalButton {

    private int floorNumber;

    public FloorNumberInternalButton(Elevator elevator, ElevatorController elevatorController, int floorNumber) {
        super(elevator,elevatorController);
        this.floorNumber=floorNumber;
    }

    @Override
    public void click() {
        int source=getElevator().getCurrentFloor();
        int destination= this.floorNumber;
        addRequest(new Request(source,destination,getDirection(source,destination)));
    }

    private Direction getDirection(int source, int destination) {
        return source>destination? Direction.DOWN:Direction.UP;
    }
}
