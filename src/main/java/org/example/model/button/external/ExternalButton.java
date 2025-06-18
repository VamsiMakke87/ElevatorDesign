package org.example.model.button.external;

import org.example.controller.ElevatorController;
import org.example.util.Direction;
import org.example.util.Request;

public abstract class ExternalButton {

    private int floorNumber;

    private Direction direction;
    private ElevatorController elevatorController;

    public ExternalButton(int floorNumber) {
        this.floorNumber = floorNumber;
        this.elevatorController=new ElevatorController();
    }

    public abstract void click();

    public void addRequest(Direction direction){
        Request request= new Request(floorNumber,floorNumber,direction);
        elevatorController.addRequest(request);
    }

}
