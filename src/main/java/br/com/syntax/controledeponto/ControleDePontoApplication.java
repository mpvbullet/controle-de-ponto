package br.com.syntax.controledeponto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ControleDePontoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControleDePontoApplication.class, args);
    }
}
