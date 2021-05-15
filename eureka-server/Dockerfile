FROM openjdk:8-jre-alpine
RUN apt-get update
RUN apt-get install -y curl
WORKDIR /eureka-server
COPY target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server.jar
ENTRYPOINT ["java","-jar","eureka-server.jar"]