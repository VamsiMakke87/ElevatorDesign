package org.example.model.button.internal;

import org.example.model.Elevator;
import org.example.util.Request;

public abstract class InternalButton {

    private Elevator elevator;
    public InternalButton(Elevator elevator) {
        this.elevator=elevator;
    }

    public void addRequest(Request request){
        elevator.addRequest(request);
    }

    public Elevator getElevator() {
        return elevator;
    }

    public abstract void click();
}
