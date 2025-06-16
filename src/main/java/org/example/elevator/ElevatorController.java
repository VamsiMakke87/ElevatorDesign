package org.example.elevator;

import org.example.elevator.model.strategy.ElevatorSelectionStrategy;
import org.example.elevator.util.Request;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {

    private List<Elevator> elevators = new ArrayList<>();

    private List<Request> requests=new ArrayList<>();

    private ElevatorSelectionStrategy elevatorSelectionStrategy;

    public ElevatorController(int n) {
        for (int i = 0; i < n; i++) {
            elevators.add(new Elevator(i + 1,5));
        }
    }
    public void setElevatorSelectionStrategy(ElevatorSelectionStrategy elevatorSelectionStrategy) {
        this.elevatorSelectionStrategy = elevatorSelectionStrategy;
    }

    public synchronized void addRequest(Request request){
        requests.add(request);
        notifyAll();
    }

    public void processRequests(){

        while (true){

            while (!requests.isEmpty()){
                processRequest(requests.removeFirst());
            }

            try{
                wait();
            }catch (Exception e){
                System.out.println(e.printStackTrace());
            }

        }

    }

    public void processRequest(Request request){

    }

}
