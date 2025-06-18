package org.example.model.button.internal;

import org.example.model.Elevator;

public class LightButton extends InternalButton{

    private boolean isLightOn;

    public LightButton(Elevator elevator) {
        super(elevator);
        this.isLightOn=false;
    }

    @Override
    public void click() {
        isLightOn=!isLightOn;
        if(isLightOn){
            System.out.println("Elevator "+getElevator().getId()+" Light turned on");
        }else{
            System.out.println("Elevator "+getElevator().getId()+" Light turned off");
        }
    }
}
