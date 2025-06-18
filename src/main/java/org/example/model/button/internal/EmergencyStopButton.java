package org.example.model.button.internal;

import org.example.model.Elevator;

public class EmergencyStopButton extends InternalButton {


    public EmergencyStopButton(Elevator elevator) {
        super(elevator);
    }

    @Override
    public void click() {
        getElevator().emergencyStop();
    }
}
