package com;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.")
public class ProjetoDemeterBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjetoDemeterBackendApplication.class, args);
    }
}






