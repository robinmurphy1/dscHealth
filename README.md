
DSC Health code assessment

------------------------------
Tech stack

Java 1.8

Maven

Swagger

JPA

Liquibase

H2

Mockito

Spring Boot

Spring Web

React

----------------------------------------------------------------------------------------------------------------

Inputs for the 'source' & 'destination' should be the NodeCode ie A, B, C, BB, DD etc

Swagger has been implemented for testing of the REST services : http://localhost:8080/swagger-ui.html 

CRUD services are available as well as the service to calculate shortest path between nodes with & without traffic delays


----------------------------------------------------------------------------------------------------------------

Run from the project directory:  mvn clean install

This creates a jar file that can be run with the following command: java -jar asessment-0.0.1-SNAPSHOT.jar

Or run the app from AssetmentApplication.java

----------------------------------------------------------------------------------------------------------------

Once the application is running open a browser and go to : http://localhost:8080

The page opens displaying the records from the data file 
