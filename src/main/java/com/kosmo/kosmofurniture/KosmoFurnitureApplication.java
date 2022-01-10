package com.kosmo.kosmofurniture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class KosmoFurnitureApplication {

    public static void main(String[] args) {
        SpringApplication.run(KosmoFurnitureApplication.class, args);
    }

}
