package org.example;


import org.example.controller.ElevatorController;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {

        ElevatorController elevatorController=ElevatorController.getInstance(10,2); // maxFloors=10 and numberOfElevators=2
        new Thread(elevatorController::processRequests,"Thread 1").start();
        Thread.sleep(1000);
        elevatorController.getElevator(0).getFanButton().click();
//        elevatorController.getElevator(1).getLightButton().click();
        elevatorController.getElevator(0).getFanButton().click();

        elevatorController.getFloor(9).clickUpwardRequestExternalButton();
        elevatorController.getFloor(3).clickDownwardRequestExternalButton();
        elevatorController.getFloor(9).clickDownwardRequestExternalButton();

       Thread.sleep(5000);
        elevatorController.getElevator(0).getInternalFloorButton(1).click();

    }
}