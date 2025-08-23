# Use an official JDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy Gradle/Maven build files and source code
COPY target/*.jar app.jar

# Expose port your app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
