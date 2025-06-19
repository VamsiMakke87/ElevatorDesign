# 🚀 Smart Elevator System 

This project is a simulation of a smart elevator control system written in Java, designed using object-oriented principles and key design patterns. The system efficiently manages requests across multiple elevators with real-time responsiveness, concurrent execution, and easy extensibility.

---

## 📌 Project Overview

This system simulates:

* Handling internal and external elevator requests
* Real-time request processing using threads
* Request prioritization based on direction and proximity
* Internal control features such as fan, light, and emergency stop
* Ability to extend elevator behavior and strategy

---

## 🎯 Design Goals

* **Modularity:** Each functionality is encapsulated (e.g., buttons, elevators, controller).
* **Concurrency:** Each elevator operates on a separate thread.
* **Extensibility:** Easily add new buttons, request types, or strategies.
* **Scalability:** Supports any number of elevators and floors.

---

## ⚙️ Technologies Used

* Java (Concurrency APIs)
* Object-Oriented Programming
* Core Design Patterns

---

## 🧠 Design Patterns Used

### 1. Singleton Pattern

**Class:** `ElevatorController`

Ensures only one controller instance coordinates elevators and requests. Implemented via a static method:

```java
public static ElevatorController getInstance(int maxFloors, int numberOfElevators) { ... }
```

### 2. Strategy Pattern

**Interface:** `ElevatorSelectionStrategy`

Allows different algorithms for selecting the optimal elevator. You can plug in strategies like `NearestElevatorStrategy`, `LeastLoadStrategy`, etc., without altering controller logic.

```java
ElevatorSelectionStrategy strategy = new NearestElevatorStrategy();
```

### 3. Command Pattern (Implicit)

**Classes:** `FanButton`, `LightButton`, `EmergencyStopButton`, `FloorNumberInternalButton`

Encapsulates requests as button clicks to decouple request invocation from execution.

```java
button.click();
```

### 4. Observer-like Communication

While not formal, the elevator informs the controller when it's idle to check for new pending requests.

```java
elevatorController.notifyController();
```

---

## 🏗️ Class Architecture Overview

* `ElevatorController`: Singleton managing all elevators, floors, and requests.
* `Elevator`: Contains queues, buttons, state, and processes requests in its thread.
* `InternalButton` / `ExternalButton`: Base class for elevator controls.
* `Request`: Encapsulates source, destination, and direction.
* `Direction`: Enum with `UP`, `DOWN`, `IDLE`, `EMERGENCYSTOP`.

---

## 🧵 Concurrency Model

* Each elevator runs in its **own thread**: `new Thread(elevator::processRequests).start();`
* The controller maintains a **shared synchronized request queue**.
* Uses `wait()` and `notifyAll()` for thread coordination.

```java
synchronized (this) {
  while (requests.isEmpty()) wait();
  notifyAll();
}
```

---

## 🔄 Request Handling Logic

1. **External Requests** (from floor buttons):

    * Added to controller's request queue.
    * Elevator controller selects optimal elevator.

2. **Internal Requests** (from inside the elevator):

    * Added directly to elevator’s minHeap (for UP) or maxHeap (for DOWN).

3. **Direction Handling:**

    * Elevator maintains two heaps:

        * `minHeap`: processes requests going UP.
        * `maxHeap`: processes requests going DOWN.
    * Uses direction to switch queues dynamically.

4. **Emergency Stop:**

    * Flushes all requests.
    * Moves to next floor and opens doors immediately.

---

## 📦 Project Structure

```
src/
├── controller/
│   └── ElevatorController.java
├── model/
│   ├── Elevator.java
│   └── button/
│       ├── external/
│       └── internal/
├── strategy/
│   └── ElevatorSelectionStrategy.java
├── util/
│   ├── Request.java
│   ├── Direction.java
│   └── Floor.java
└── Main.java
```

---

## 🧩 Extensibility

### ➕ Add New Elevator Selection Strategy

* Create a class that implements `ElevatorSelectionStrategy`
* Override `selectOptimalElevator()`
* Set it in controller: `controller.setElevatorSelectionStrategy(new CustomStrategy());`

### ➕ Add New Button

* Extend `InternalButton` or `ExternalButton`
* Implement `click()` behavior
* Plug into elevator or floor

### ➕ Add New Features (e.g., maintenance mode)

* Extend `Elevator` and override behavior as needed

---

## ✅ How to Run

1. Clone or download the repo
2. Open in IntelliJ or any Java IDE
3. Compile and run `Main.java`
4. Sample usage:

```java
ElevatorController controller = ElevatorController.getInstance(10, 2); // maxFloors:10, numberOfElevators:2
controller.getElevator(0).getFanButton().click();
controller.getFloor(5).clickUpwardRequestExternalButton();
```

---

## 🧪 Sample Output

```
Elevator 0 Fan turned on
Elevator 0 doors open in floor 0
Elevator 0 doors close in floor 0
Elevator 0 in floor 1 [3, 5]
...
```

---

## 📚 Author

**Vamsi Makke**

---

## 🛠️ Future Improvements

* Add GUI using JavaFX or Swing
* Integrate sensors and load limits
* Web dashboard for real-time monitoring


