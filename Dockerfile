FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY Project/pom.xml .
COPY Project/src ./src

RUN mvn clean package -DskipTests

FROM tomcat:10.1-jdk17-temurin
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080