package org.example.elevator.model.button.external;

import org.example.elevator.util.Direction;

public abstract class ExternalButtton {

    private int floorNumber;


    public ExternalButtton(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void click(Direction direction){

    }
}
