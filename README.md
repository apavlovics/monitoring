# Monitoring

This sample application shows how to build a simple REST API using Spring Boot, Spring MVC and
Spring Data JPA. It exposes methods to manage accounts and monitor their activities. Monitoring uses in-memory
HSQLDB and does not persist data across launches. This is a deliberate choice for the sample application.

## System Requirements

The application is tested to work with:

* Java 11
* Maven 3.6

## Configuration

The configuration for Spring Boot and Logback is stored in the *src/main/resources* folder.

## Testing

To test the application, go to its root folder and execute:

    mvn test

## Running

To run the application, go to its root folder and execute:

    mvn spring-boot:run

The application starts at http://localhost:8090 by default. Open this URL to see Swagger UI with the detailed
description of available APIs and have the opportunity to try them out.
