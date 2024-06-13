# Use an official OpenJDK runtime as a parent image
 FROM eclipse-temurin:17-jdk-alpine
 
 # Set the working directory inside the container
 WORKDIR /app
 
 # Copy the Spring Boot application JAR file into the container
 COPY target/AuthService-1.0.0-SNAPSHOT.jar Auth-Service.jar
 
 # Expose the port that your application will run on
 EXPOSE 9090
 
 # Define the command to run your application
 CMD ["java", "-jar", "/app/Auth-Service.jar"]