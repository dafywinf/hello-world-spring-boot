## Project: Hello World Spring Boot Application

The objective is to create a Spring Boot application with a simple RESTful endpoint that returns a "Hello World" message along with the current date and time. The date should be retrieved from a PostgreSQL database to demonstrate database integration. The application should be containerized using Docker for easy deployment.

# Requirements
Detailed requirements for the project.

* [Requirements](./requirements.md)

# Deployment Instructions


## Local Deployment

Build and run the application locally:

```bash
# Build the Application
mvn clean install

# Build the Docker Image
 docker build -f src/main/docker/Dockerfile . -t hello-world-spring-boot
 
# Run the Docker Containers
 docker compose -f ./src/main/docker/app.yml up -d   

# Access the RESTful endpoint 
 wget -qO- localhost:8080/api/hello
```


### PostgreSQL Database Setup
Run the Docker Compose command to start  the PostgreSQL database containers:

```bash
# Start PostgreSQL database container
 docker compose -f src/main/docker/postgresql.yml up
```

Interact with the PostgreSQL database:

```bash
# Access the PostgreSQL database container
docker exec -it hello-world-postgresql-1 bash

# Connect to the PostgreSQL database via the psql command-line tool
 psql -h localhost -p 5432 -U hello-world -d hello-world

# List all tables in the database
\dt

# Query the 'hello_logs' table
SELECT * FROM hello_logs;
```


## Technical Research

Provides a brief overview of the technical research conducted for the project.

* ✅ [Docker Images with Spring Boot](https://www.baeldung.com/spring-boot-docker-images)
* [Jib vs Spring Boot Plugin for building Docker Images](https://tomgregory.com/gradle/jib-vs-spring-boot-for-building-docker-images/)
* [Reusing Docker Images with Spring Boot](https://www.baeldung.com/docker-layers-spring-boot)

