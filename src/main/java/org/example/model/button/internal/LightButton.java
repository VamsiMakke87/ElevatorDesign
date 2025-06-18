package org.example.model.button.internal;

import org.example.model.Elevator;

public class FanButton extends InternalButton{

    private boolean isFanOn;

    public FanButton(Elevator elevator) {
        super(elevator);
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
