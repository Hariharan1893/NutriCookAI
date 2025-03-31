# Use OpenJDK 21 as the base image
FROM openjdk:21

# Set working directory inside the container
WORKDIR /app

# Copy the already built JAR file into the container
COPY target/NutriCookAI-0.0.1-SNAPSHOT.jar app.jar

# Expose port 7070
EXPOSE 7070

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
