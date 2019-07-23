FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/calculation-service-1.0.jar
ADD ${JAR_FILE} calculation-service.jar
ENTRYPOINT ["java","-jar","/calculation-service.jar"]