# Monitoring

This sample application shows how to build a simple REST API using [Spring](https://spring.io). It exposes methods to manage accounts and monitor their activities. Monitoring uses in-memory HSQLDB and does not persist data across launches. This is a deliberate choice for the sample application.

## Configuration

The configuration for Spring and Logback is stored in the *src/main/resources* folder.

## Testing

To test the application, go to its root folder and execute:

    ./gradlew test

## Running

To run the application, go to its root folder and execute:

    ./gradlew bootRun

The application starts at http://localhost:8090 by default. Open this URL to see Swagger UI with the detailed description of available APIs and have an opportunity to try them out.
