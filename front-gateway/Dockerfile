FROM openjdk:8-jre-alpine
WORKDIR /front-gateway
COPY target/front-gateway-0.0.1-SNAPSHOT.jar front-gateway.jar
ENTRYPOINT ["java","-jar","front-gateway.jar"]