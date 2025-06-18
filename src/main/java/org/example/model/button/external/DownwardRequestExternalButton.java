package org.example.model.button.external;

import org.example.util.Direction;

public class DownwardRequestExternalButton extends ExternalButton{



    public DownwardRequestExternalButton(int floorNumber) {
        super(floorNumber);
    }

    @Override
    public void click() {
        if(isButtonClicked())return; // to prevent multiple clicks
        setButtonClicked(true);
        addRequest(Direction.DOWN);
    }
}
