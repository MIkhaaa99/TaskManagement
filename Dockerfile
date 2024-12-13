#FROM maven:3.8.6-amazoncorretto-17 AS build
#ARG JAR_FILE=*.jar
#COPY ${JAR_FILE} application.jar
#ENTRYPOINT ["java", "-jar", "application.jar"]

FROM maven:3.8.6-amazoncorretto-17 AS build
COPY pom.xml /build/
WORKDIR /build/
COPY src /build/src/
RUN mvn package -DskipTests

FROM openjdk:17-alpine
ARG JAR_FILE=/build/target/*.jar
COPY --from=build $JAR_FILE /opt/docker-test/app.jar
ENTRYPOINT ["java", "-jar", "/opt/docker-test/app.jar"]