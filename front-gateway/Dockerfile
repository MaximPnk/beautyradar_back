FROM openjdk:8-jre-alpine
RUN apk update
RUN apk add curl
WORKDIR /front-gateway
COPY target/front-gateway-0.0.1-SNAPSHOT.jar front-gateway.jar
ENTRYPOINT ["java","-jar","front-gateway.jar"]