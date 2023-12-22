# Dockerfile in eureka-service directory
FROM openjdk:17-jdk-slim as build

WORKDIR /workspace/app

# Copy the Gradle executable and wrapper
COPY gradle ./gradle
COPY gradlew ./
COPY build.gradle ./
COPY settings.gradle ./

# Copy the eureka-service project
COPY eureka-service/build.gradle ./eureka-service/
COPY eureka-service/src ./eureka-service/src

# Grant execution permissions to Gradle wrapper
RUN chmod +x gradlew

# Build the project
RUN ./gradlew :eureka-service:build -x test

# Stage 2: Create the Docker image with the built JAR file
FROM openjdk:17-jdk-slim

COPY --from=build /workspace/app/eureka-service/build/libs/*.jar app.jar

EXPOSE 8091

ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=prod"]
