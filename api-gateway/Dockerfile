# Stage 1: Build the application
FROM openjdk:17-jdk-slim as build

# Set the working directory inside the image
WORKDIR /workspace/app

# Copy the Gradle executable and wrapper
COPY gradle ./gradle
COPY gradlew ./
COPY build.gradle ./
COPY settings.gradle ./

# Copy the api-gateway-service project
COPY api-gateway/build.gradle ./api-gateway/build.gradle
COPY api-gateway/src ./api-gateway/src

# Grant execution permissions to Gradle wrapper
RUN chmod +x gradlew

# Build the project
RUN ./gradlew :api-gateway:build -x test

# Stage 2: Create the Docker image with the built JAR file
FROM openjdk:17-jdk-slim

# Copy the JAR file from the build stage
COPY --from=build /workspace/app/api-gateway/build/libs/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=prod"]
