package org.example.elevator.model.button.internal;

public abstract class InternalButton {

    private String buttonName;

    public InternalButton(String buttonName) {
        this.buttonName = buttonName;
    }

    public abstract void click();

}
