package com.jesusfc.springboot3java17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

//@EnableSwagger2WebMvc
@SpringBootApplication(scanBasePackages = "com.jesusfc.springboot3java17")
public class Springboot3Java17Application {
    public static void main(String[] args) {
        SpringApplication.run(Springboot3Java17Application.class, args);
    }

}
