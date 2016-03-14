# DroolsElevator
Elevator Engine with JBoss Drools 6

The goal of the game is to implement an elevator engine.

HTTP GET requests will be send to this server :

- /call?atFloor=[-4-40]&to=[UP|DOWN]
- /go?floorToGo=[-4-40]
- /userHasEntered?cabin=[1-4]
- /userHasExited?cabin=[1-4]
- /reset?lowerFloor=[-4-0]&higherFloor=[0-40]&cabinSize=[1-10]&cabinCount=[1-4]&cause=information+message
- /nextCommand  - Possibles responses : NOTHING, UP, DOWN, OPEN or CLOSE

## Running the application locally

### Prerequisites

Here is what you need to build and run a code elevator session :

- JDK 1.8
- maven 3.x

### Steps

```
$ git clone https://github.com/guerinth/DroolsElevator.git
$ cd DroolsElevator
$ mvn clean package
$ java -jar target\ascenseur-0.0.1-SNAPSHOT.jar
```
