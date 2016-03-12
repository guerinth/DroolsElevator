# DroolsElevator
Elevator Engine with JBoss Drools

The goal of the game is to implement an elevator engine.
HTTP GET requests will be send to this server.

Events :
- /call?atFloor=[-4-40]&to=[UP|DOWN]
- /go?floorToGo=[-4-40]
- /userHasEntered?cabin=[1-4]
- /userHasExited?cabin=[1-4]
- /reset?lowerFloor=[-4-0]&higherFloor=[0-40]&cabinSize=[1-10]&cabinCount=[1-4]&cause=information+message
- /nextCommand : body of the request : NOTHING, UP, DOWN, OPEN or CLOSE

## Running the server locally

### Prerequisites

Here is what you need to build and run a code elevator session :

- JDK 1.8
- maven 3.x
- Drools 6.x

### Steps

$ git clone https://github.com/guerinth/DroolsElevator.git

$ cd DroolsElevator

$ mvn clean install

$ mvn exec:java -Dexec.mainClass="com.gop.contest.ascenseur.ParticipantServer"
