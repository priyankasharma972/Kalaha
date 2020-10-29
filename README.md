## Introduction
This project is a web application which is an implementation of a game known as KALAHA.
The application is exposed as a RESTFul Webservice.
It is a 2-player strategic game played on a Kalaha Board.
The Board consists of 6 pits per player and 1 Big Pit called as Kalaha to the right of each player.
At the start of game, each small pit has 6 stones each in it.

# Rules of The Game
1. The player who begins with the first move picks up all the stones in any of his own six pits, and sows the stones on to the right, one in each of the following pits, including his own big pit.
2. No stones are put in the opponents' big pit.
3. If the player's last stone lands in his own big pit, he gets another turn. This can be repeated several times before it's the other player's turn.
4. During the game the pits are emptied on both sides. Always when the last stone lands in an own empty pit, the player captures his own stone and all stones in the opposite pit (the other player pit) and puts them in his own big pit.
5. The game is over as soon as one of the sides runs out of stones.
6. The player who still has stones in his pits keeps them and puts them in his big pit.
7. The winner of the game is the player who has the most stones in his big pit.


## Play the Game
1. In order to play the game, please go to the root directory where pom.xml is stored and run the below command:
`./mvnw spring-boot:run`
2. Once the application is started, it can be accessed at `http://localhost:8080`

## Tech Stack
1. Java 8(Main Programming Language)
2. Spring Boot(Java-based framework for building web and enterprise applications)
3. Maven(Dependency Management)
4. HTML/CSS(very basic for the front-end implementation)
5. JQuery(To consume the RESTFul APIs)
6. JUnit(for Unit Testing)

## Endpoints
1. GET '/kalahagame/start'
This end point initializes a basic set up of the game and returns the initialized board.
2. POST '/kalahagame/play'
This end point returns the updated game board with every valid move from each player.

## Testing(Unit+Integration)
1. To run the tests, run command: `./mvnw test`
2. Test reports with results can be found under: `target/surefire-reports`

## Project Structure
1. `scr\main\java` folder contains the model, service and controller classes.
2. `scr\main\resources` folder contains the front-end including HTML, CSS and Javascript
3. `scr\test\java` folder contains the Integration and Unit Tests.
4. `pom.xml` file contains project and configuration details used by Maven to build the project.
5. `target` folder contains the artifacts of project which are generated after build and packaging.

## Possible Enhancements
1. Session Management
2. Containerization using Docker
3. Artificial Intelligence
4. Possibility to play the game between 2 remote players on a network

## Inspiration
This inspiration to develop this application is the technical assignment at bol.com.