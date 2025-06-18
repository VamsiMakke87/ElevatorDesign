package org.example.model.button.external;

import org.example.util.Direction;

public class UpwardRequestExternalButton extends ExternalButton{

    private boolean isButtonClicked;

    public UpwardRequestExternalButton(int floorNumber) {
        super(floorNumber);
        this.isButtonClicked=false;
    }

    @Override
    public void click() {
        if(isButtonClicked)return; // to prevent multiple clicks
        isButtonClicked=true;
        addRequest(Direction.UP);
    }
}
