FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/discovery-service-1.0.jar
ADD ${JAR_FILE} discovery-service.jar
ENTRYPOINT ["java","-jar","/discovery-service.jar"]