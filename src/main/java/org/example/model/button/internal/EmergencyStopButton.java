package org.example.model.button.internal;

import org.example.controller.ElevatorController;
import org.example.model.Elevator;

public class EmergencyStopButton extends InternalButton {


    public EmergencyStopButton(Elevator elevator, ElevatorController elevatorController) {
        super(elevator,elevatorController);
    }

    @Override
    public void click() {
        getElevator().emergencyStop();
    }
}
