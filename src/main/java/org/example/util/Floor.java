package org.example.util;

import org.example.model.button.external.ExternalButton;

public class Floor {

    private int floorNumber;
    private ExternalButton upwardRequestExternalButton;

    public ExternalButton downwardRequestExternalButton;

    public Floor(int floorNumber,ExternalButton upwardRequestExternalButton, ExternalButton downwardRequestExternalButton) {
        this.floorNumber=floorNumber;
        this.upwardRequestExternalButton = upwardRequestExternalButton;
        this.downwardRequestExternalButton = downwardRequestExternalButton;
    }
}
