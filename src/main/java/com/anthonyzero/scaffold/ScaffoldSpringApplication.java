package com.anthonyzero.scaffold;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("com.anthonyzero.scaffold.*.mapper")
public class ScaffoldSpringApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ScaffoldSpringApplication.class)
                .run(args);
    }
}
