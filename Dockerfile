FROM openjdk:11
ARG JAR_FILE=build/libs/*SHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]