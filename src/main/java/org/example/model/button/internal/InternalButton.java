package org.example.model.button.internal;

import org.example.controller.ElevatorController;
import org.example.model.Elevator;
import org.example.util.Request;

public abstract class InternalButton {

    private Elevator elevator;
    private ElevatorController elevatorController;

    public InternalButton(Elevator elevator) {
        this.elevator=elevator;
        this.elevatorController = new ElevatorController();
    }

    public void addRequest(Request request){
        elevatorController.addRequest(request);
    }

    public Elevator getElevator() {
        return elevator;
    }

    public abstract void click();
}
