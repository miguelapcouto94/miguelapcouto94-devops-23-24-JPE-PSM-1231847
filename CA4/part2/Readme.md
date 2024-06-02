## CA4 - Part 2: Containerized Environment with Docker

### Table of Contents

1. [Introduction](#introduction)
2. [Docker Configuration](#docker-configuration)
    - [Dockerfile for db Container](#dockerfile-for-db-container)
    - [Dockerfile for web Container](#dockerfile-for-web-container)
3. [Docker Compose Configuration](#docker-compose-configuration)
4. [Steps to Build and Run the Docker Images](#steps-to-build-and-run-the-docker-images)
    - [Building Docker Images](#building-docker-images)
    - [Running Docker Containers](#running-docker-containers)
    - [Accessing the Application](#accessing-the-application)
5. [Publishing Docker Images to Docker Hub](#publishing-docker-images-to-docker-hub)
6. [Tagging the Repository](#tagging-the-repository)
7. [Conclusion](#conclusion)

### Introduction

This assignment aims to set up a containerized environment using Docker to run the Gradle version of a Spring
application. The solution employs Docker Compose to manage two services/containers: one for the Tomcat server and Spring
application (`web`), and the other for the H2 database server (`db`).

### Docker Configuration

#### Dockerfile for db Container

The Dockerfile for the `db` container sets up the H2 database server. Save this Dockerfile in the `CA4/Part2/db`
directory. It downloads and runs the H2 database, exposing it on port 8082.

```Dockerfile
FROM openjdk:17-jdk-slim

RUN apt-get update && \
    apt-get install -y wget

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app/

# Download and set up H2 Database
RUN wget https://repo1.maven.org/maven2/com/h2database/h2/2.2.224/h2-2.2.224.jar -O /opt/h2.jar

EXPOSE 8082
EXPOSE 9092

# Command to start H2 Server
CMD ["java", "-cp", "/opt/h2.jar", "org.h2.tools.Server", "-tcp", "-tcpAllowOthers", "-tcpPort", "9092", "-web", "-webAllowOthers", "-webPort", "8082", "-ifNotExists"]
```

#### Dockerfile for web Container

The Dockerfile for the `web` container sets up the Tomcat server and deploys the Spring application. Save this
Dockerfile in the `CA4/Part2/web` directory. The Spring application JAR is copied into the container, which runs on port 8080.

```Dockerfile
# Create a basic container with Java 17 and running Tomcat 10
FROM tomcat:10-jdk17-openjdk-slim

# Create a directory for the project and clone the repository there
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app/

# Update package list and install Git
RUN apt-get update && apt-get install -y git

# Clone the repository and navigate to the project directory
RUN git clone https://github.com/miguelapcouto94/miguelapcouto94-devops-23-24-JPE-PSM-1231847 .

# Navigate to the project directory
WORKDIR /usr/src/app/CA2/part2/react-and-spring-data-rest-basic

# Change the permissions of the gradlew file to make it executable
RUN chmod +x gradlew

# Run the gradle build command
RUN ./gradlew build

# Copy the generated WAR file to the Tomcat webapps directory
RUN cp ./build/libs/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

# State the port that our application will run on
EXPOSE 8080

# Start Tomcat automatically when the container starts
CMD ["catalina.sh", "run"]
```

### Docker Compose Configuration

The `docker-compose.yml` file specifies the configuration for the `web` and `db` containers. Save this file in
the `CA4/Part2` project directory. The `web` service is dependent on the `db` service and includes environment variables
for connecting to the H2 database.

```yaml
services:
   web:
      build: web
      ports:
         - "8080:8080"
      networks:
         default:
            ipv4_address: 192.168.56.10
      depends_on:
         - "db"
   db:
      build: db
      ports:
         - "8082:8082"
         - "9092:9092"
      volumes:
         - ./data:/usr/src/data-backup
      networks:
         default:
            ipv4_address: 192.168.56.11
networks:
   default:
      ipam:
         driver: default
         config:
            - subnet: 192.168.56.0/24
```

### Steps to Build and Run the Docker Images

#### Building Docker Images

Navigate to the project directory `CA4/Part2` in your terminal and run the following command to build the Docker images:

```bash
sudo docker compose build
```

#### Running Docker Containers

After building the images, start the Docker containers with:

```bash
sudo docker compose up
```

#### Accessing the Application

To access the Spring application, open a web browser and go to `http://localhost:8080/react-and-spring-data-rest-basic-0.0.1`. To access the H2 database
console, navigate to `http://localhost:8082` and connect to the `jpadb` database using the JDBC
URL `jdbc:h2:tcp://192.168.56.11:9092/./jpadb`.

### Publishing Docker Images to Docker Hub

To publish the Docker images to Docker Hub, follow these steps:

1. Log in to Docker Hub:

 ```bash
  sudo docker login
  ```

2. Tag and push the images to Docker Hub:

 ```bash
  # Tag and push the db image
  sudo docker tag part2-db miguelcouto/ca4-part2:part2-db
  sudo docker push miguelcouto/ca4-part2:part2-db

  # Tag and push the web image
  sudo docker tag part2-web miguelcouto/ca4-part2:part2-web
  sudo docker push miguelcouto/ca4-part2:part2-web
  ```

### Tagging the Repository

To tag the repository with `ca4-part2`, execute:

```bash
git tag ca4-part2
git push origin ca4-part2
```

### Conclusion

This README details the steps to set up a containerized environment using Docker to run a Spring application. Using
Docker Compose, we orchestrated `web` and `db` services, built and published images to Docker Hub, and documented the
entire process. Follow these instructions to replicate the environment and ensure proper functionality.