FROM openjdk:8-jre-alpine
WORKDIR /api-gateway
COPY target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway.jar
ENTRYPOINT ["java","-jar","api-gateway.jar"]