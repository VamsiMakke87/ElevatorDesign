package org.example.elevator.model.button.external.impl;

import org.example.elevator.model.button.external.ExternalButtton;
import org.example.elevator.util.Direction;

public abstract class FirstFloorButton extends ExternalButtton {
    public FirstFloorButton(int floorNumber) {
        super(1);
    }

    @Override
    public void click(Direction direction) {

    }
}
