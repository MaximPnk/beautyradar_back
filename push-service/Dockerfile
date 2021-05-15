FROM openjdk:8-jre-alpine
RUN apk update
RUN apk add curl
WORKDIR /push-service
COPY target/push-service-0.0.1-SNAPSHOT.jar push-service.jar
ENTRYPOINT ["java","-jar","push-service.jar"]