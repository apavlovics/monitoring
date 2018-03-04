# Monitoring

This sample application shows how to build a simple REST API using Spring Boot, MVC and Data JPA. It exposes methods to manage accounts and monitor their activities. Monitoring uses in-memory HSQLDB and does not persist data across launches. This is a deliberate choice for the sample application.

## System Requirements

This application is tested to work with:

* Java 1.8
* Maven 3.3.9

## Configuration

The configuration for Spring Boot and Logback is stored in the *src/main/resources* folder.

## Testing

To test this application, go to its root folder and execute:

    mvn test

## Running

To run this application, go to its root folder and execute:

    mvn spring-boot:run

The application will start at http://localhost:8090 by default. Open this URL to see Swagger UI with the detailed description of available APIs.
