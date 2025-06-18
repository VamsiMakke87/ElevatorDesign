package org.example.model.button.external;

import org.example.controller.ElevatorController;
import org.example.util.Direction;

public class UpwardRequestExternalButton extends ExternalButton{


    public UpwardRequestExternalButton(int floorNumber, ElevatorController elevatorController) {
        super(floorNumber,elevatorController);
    }

    @Override
    public void click() {
        if(isButtonClicked())return; // to prevent multiple clicks
        setButtonClicked(true);
        addRequest(Direction.UP);
    }
}
