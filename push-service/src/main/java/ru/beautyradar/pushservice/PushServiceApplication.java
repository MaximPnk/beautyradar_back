package ru.beautyradar.pushservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:push-service-secret.properties")
public class PushServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PushServiceApplication.class, args);
    }

}
