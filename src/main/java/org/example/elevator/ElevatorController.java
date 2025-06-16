package org.example.elevator;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {

    List<Elevator> elevators = new ArrayList<>();

    public ElevatorController(int n) {
        for (int i = 0; i < n; i++) {
            elevators.add(new Elevator(i + 1));
        }
    }






}
