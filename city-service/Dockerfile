FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/city-service-1.0.jar
ADD ${JAR_FILE} city-service.jar
ENTRYPOINT ["java","-jar","/city-service.jar"]