FROM openjdk:8-jre-alpine
WORKDIR /eureka-server
COPY target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server.jar
ENTRYPOINT ["java","-jar","eureka-server.jar"]