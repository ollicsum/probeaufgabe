#build stage
FROM maven:3.6.0-jdk-11-slim AS build
COPY pom.xml /app/
COPY src /app/src
RUN mvn -f /app/pom.xml clean install -Pdocker-compose

#run stage
FROM openjdk:11-jdk-slim
COPY --from=build /app/target/probe*.jar /app/probe-aufgabe.jar
ENTRYPOINT ["java","-jar","/app/probe-aufgabe.jar"]