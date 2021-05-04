FROM openjdk:8-jre-alpine
WORKDIR /upload-service
COPY target/upload-service-0.0.1-SNAPSHOT.jar upload-service.jar
ENTRYPOINT ["java","-jar","upload-service.jar"]