# 1단계: 빌드
FROM gradle:7.6.0-jdk17 AS build
WORKDIR /app
COPY --chown=gradle:gradle . .
RUN gradle build -x test

# 2단계: 실행
FROM openjdk:17
WORKDIR /app
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
