package ru.beautyradar.uploadservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableEurekaClient
@PropertySource(value = "classpath:upload-service-secret.properties")
public class UploadServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadServiceApplication.class, args);
    }

}
