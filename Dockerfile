# Dockerfile
FROM openjdk:11-jdk
ARG JAR_FILE=build/libs/webfounder-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /webfounder.jar

ENTRYPOINT ["java","-jar","/webfounder.jar"]
