FROM openjdk:8-jre-alpine
WORKDIR /api-gateway
COPY target/upload-service-0.0.1-SNAPSHOT.jar upload-service.jar
ENTRYPOINT ["java","-jar","api-gateway.jar"]