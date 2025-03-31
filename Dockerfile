# Use OpenJDK 21 as the base image
FROM openjdk:21-jdk

# Set the working directory inside the container
WORKDIR /app

# Add the built JAR file into the container
ADD target/NutriCookAI-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 7070

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
