package ru.beautyradar.frontgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:front-gateway-secret.properties")
public class FrontGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontGatewayApplication.class, args);
    }

}
