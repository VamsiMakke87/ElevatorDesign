package org.example.model.button.internal;

import org.example.controller.ElevatorController;
import org.example.model.Elevator;

public class FanButton extends InternalButton{

    private boolean isFanOn;

    public FanButton(Elevator elevator, ElevatorController elevatorController) {
        super(elevator,elevatorController);
        this.isFanOn=false;
    }

    @Override
    public void click() {
        isFanOn=!isFanOn;
        if(isFanOn){
            System.out.println("Elevator "+getElevator().getId()+" Fan turned on");
        }else{
            System.out.println("Elevator "+getElevator().getId()+" Fan turned off");
        }
    }
}
