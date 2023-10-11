FROM openjdk:17
ARG JAR_FILE=target/TopEducationApp.jar
COPY ${JAR_FILE} TopEducationApp.jar
ENTRYPOINT ["java", "-jar", "/TopEducationApp.jar"]