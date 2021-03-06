package ru.beautyradar.frontgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableEurekaClient
@PropertySource(value = "classpath:front-gateway-secret.properties")
public class FrontGatewayApplication {

    //todo согласовать удаление из бд

    public static void main(String[] args) {
        SpringApplication.run(FrontGatewayApplication.class, args);
    }

}
