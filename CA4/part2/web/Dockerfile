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
