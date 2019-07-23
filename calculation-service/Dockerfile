FROM java:8
VOLUME /tmp
ARG JAR_FILE=target/calculation-service-1.0.jar
ADD ${JAR_FILE} calculation-service.jar
ENTRYPOINT ["java","-jar","/calculation-service.jar"]