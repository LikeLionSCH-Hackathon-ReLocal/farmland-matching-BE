# syntax=docker/dockerfile:1

FROM gradle:8.9-jdk21 AS build
WORKDIR /workspace
COPY gradlew ./
COPY gradle ./gradle
RUN chmod +x gradlew && ./gradlew --version

COPY build.gradle settings.gradle gradle.properties* ./
COPY src ./src

RUN ./gradlew clean bootJar -x test --no-daemon

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /workspace/build/libs/*.jar /app/app.jar
ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
