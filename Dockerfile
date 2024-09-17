FROM openjdk-8-slim

WORKDIR /app

RUN mkdir -p /app/input
RUN mkdir -p /app/output

VOLUME ["app/input"]
VOLUME ["app/output"]

COPY target/javaToAvalla-1.0-SNAPSHOT-jar-with-dependencies.jar app/javaToAvalla-1.0-SNAPSHOT-jar-with-dependencies.jar

ENTRYPOINT ["java", "-jar", "app/javaToAvalla-1.0-SNAPSHOT-jar-with-dependencies.jar"]
