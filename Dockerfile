FROM openjdk:17
ARG JAR_FILE=target/EducationTopApp.jar
COPY ${JAR_FILE} EducationTopApp.jar
ENTRYPOINT ["java", "-jar", "/EducationTopApp.jar"]