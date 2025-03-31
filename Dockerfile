# Step 1: Build Stage
FROM maven:3.8.6-openjdk-21 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run Stage
FROM openjdk:21-jdk
WORKDIR /app
COPY --from=build /app/target/NutriCookAI-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 7070
CMD ["java", "-jar", "app.jar"]
