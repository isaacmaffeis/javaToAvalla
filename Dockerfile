FROM openjdk:8-jdk-alpine

WORKDIR /app

RUN mkdir -p /app/input
RUN mkdir -p /app/output

VOLUME ["app/input"]
VOLUME ["app/output"]

COPY target/javatoavalla-1.0-SNAPSHOT-jar-with-dependencies.jar app/javatoavalla-1.0-SNAPSHOT-jar-with-dependencies.jar

ENTRYPOINT ["java", "-jar", "app/javatoavalla-1.0-SNAPSHOT-jar-with-dependencies.jar"]
