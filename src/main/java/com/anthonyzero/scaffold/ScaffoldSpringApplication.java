package com.anthonyzero.scaffold;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ScaffoldSpringApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ScaffoldSpringApplication.class)
                .run(args);
    }
}
