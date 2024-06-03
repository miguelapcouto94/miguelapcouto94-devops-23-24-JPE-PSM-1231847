### Table of Contents

1. [Introduction](#introduction)
2. [Version 1: Building the Server Inside the Dockerfile](#version-1-building-the-server-inside-the-dockerfile)
   1. [Implementation Steps](#implementation-steps)
3. [Version 2: Copying the Built JAR into the Dockerfile](#version-2-copying-the-built-jar-into-the-dockerfile)
   1. [Implementation Steps](#implementation-steps-1)
4. [Conclusion](#conclusion)

---

### Introduction

This repository contains Part 1 of the CA4 assignment, which aims to practice using Docker to create images and run
containers. The application used is the chat server from CA2, available in
the [gradle_basic_demo](https://bitbucket.org/pssmatos/gradle_basic_demo/) repository. In this assignment, two versions
of the solution are created:

1. Building the chat server inside the Dockerfile.
2. Building the server on the host and copying the JAR file into the Dockerfile.

---

### Version 1: Building the Chat Server Inside the Dockerfile

#### Implementation Steps

1. Create the Dockerfile:
   Create a file named Dockerfile in the project directory with the following content:
   ```Bash
   # Use a Gradle image with JDK 17 to build the application
   FROM gradle:jdk17 AS builder
   
   # Set the working directory for the build
   WORKDIR /CA4/Part1/version1
   
   # Clone the repository
   RUN git clone https://bitbucket.org/pssmatos/gradle_basic_demo.git
   
   # Set the working directory to the cloned repository
   WORKDIR /CA4/Part1/version1/gradle_basic_demo
   
   # Ensure the Gradle wrapper has the correct permissions
   RUN chmod +x gradlew
   
   # Build the application
   RUN ./gradlew build
   
   # Use a slim JRE image for the runtime
   FROM eclipse-temurin:17-jre
   
   # Set the working directory
   WORKDIR /app
   
   # Copy the built JAR file from the builder stage
   COPY --from=builder /CA4/Part1/version1/gradle_basic_demo/build/libs/basic_demo-0.1.0.jar /app/basic_demo-0.1.0.jar
   
   # Expose the port the server will run on
   EXPOSE 59001
   
   # Set the entry point to run the server
   ENTRYPOINT ["java", "-cp", "/app/basic_demo-0.1.0.jar", "basic_demo.ChatServerApp", "59001"]
   ```
2. Open Docker.
3. Navigate to the folder where the Dockerfile is located.
4. Build the Docker image:
   ```bash
   docker build -t miguelcouto/chat-server:version1 .
   ```
   <img src="https://i.ibb.co/5LKGQXB/dockerbuildv1.png" alt="dockerbuildv1" ></a>
5. Confirm the image is created:
   ```bash
   docker images
   ```
   <img src="https://i.ibb.co/TKQ5k1w/docker-images.png" alt="docker-images" ></a>
6. Run the Docker container:
   ```bash
   docker run -p 59001:59001 miguelcouto/chat-server:version1
   ```
   <img src="https://i.ibb.co/RHFjtRW/docker-run-v1.png" alt="docker-run-v1" ></a>
7. In another terminal, navigate to `CA2 - Part1 / gradle_basic_demo`.
8. Build and run the chat client:
   ```bash
   ./gradlew build
   ./gradlew runClient
   ```
   
   <img src="https://i.ibb.co/PFyv0kT/chatopenv1.png" alt="chatopenv1" ></a>

   <img src="https://i.ibb.co/4g5Nxmz/chat-server-runningv1.png" alt="chat-server-runningv1" ></a>
   
   (Open two different terminals for two clients)
9. The server is running in Docker and the clients are running on the host.
10. Push the image to Docker Hub:
   ```bash
   docker push miguelcouto/chat-server:version1
   ```
   <img src="https://i.ibb.co/K61Y12K/Screenshot-from-2024-06-02-17-54-54.png" alt="Screenshot-from-2024-06-02-17-54-54" border="0"></a>
---




### Version 2: Copying the Built JAR into the Dockerfile

#### Implementation Steps

1. Build the project on the host to generate the JAR file:
   ```bash
   ./gradlew build
   ```
   This will generate a JAR file in the build/libs directory.
2. Create the Dockerfile:
   Create a file named Dockerfile in the project directory with the following content:
   ```Bash
   # Use a Gradle image with JDK 21 to build the application
   FROM gradle:jdk21 AS builder
   
   # Set the working directory
   WORKDIR /app
   
   # Copy the JAR file from the host machine to the Docker image
   COPY CA2/part1/gradle_basic_demo/build/libs/basic_demo-0.1.0.jar /app/basic_demo-0.1.0.jar
   
   # Expose the port the server will run on
   EXPOSE 59001
   
   # Set the entry point to run the server
   ENTRYPOINT ["java", "-cp", "/app/basic_demo-0.1.0.jar", "basic_demo.ChatServerApp", "59001"]
     ```
3. Navigate to the folder where the Dockerfile is located.

4. Build the Docker image:
   ```bash
   docker build -t miguelcouto/chat-server:version2 -f Dockerfile ../../..
   ```
   <img src="https://i.ibb.co/5RmhzRH/docker-buildv2.png" alt="docker-buildv2" ></a>
5. Confirm the image is created:
   ```bash
   docker images
   ```
6. Run the Docker container:
   ```bash
   docker run -p 59001:59001 miguelcouto/chat-server:version2
   ```
   <img src="https://i.ibb.co/W3kGWFc/chatrunv2.png" alt="chatrunv2" ></a>
7. In another terminal, navigate to `CA2 Part1 gradle_basic_demo` and run the chat client:
   ```bash
   ./gradlew runClient
   ```
   <img src="https://i.ibb.co/D1qYsCW/chat-runv2.png" alt="chat-runv2" ></a>

   <img src="https://i.ibb.co/KNwjYkQ/chatopenv2.png" alt="chatopenv2" ></a>
8. The server is running in Docker and the clients are running on the host.
9. Push the image to Docker Hub:
   ```bash
   docker push miguelcouto/chat-server:version2
   ```
   <img src="https://i.ibb.co/pKXH7dm/Screenshot-from-2024-06-02-17-50-41.png" alt="Screenshot-from-2024-06-02-17-50-41" ></a>


---



### Conclusion

In this assignment, I demonstrated two approaches to containerizing a chat server application using Docker. Both
approaches were successfully implemented, allowing the chat server to run in a Docker container while clients connected
from the host machine. This exercise provided hands-on experience with Dockerfile creation, image building, and
container management, reinforcing the concepts and practices of containerization in a development workflow.