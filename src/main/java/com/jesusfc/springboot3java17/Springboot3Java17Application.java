package com.jesusfc.springboot3java17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.jesusfc.springboot3java17")
public class Springboot3Java17Application {
    public static void main(String[] args) {

        // Simple/easy way
        // SpringApplication.run(Springboot3Java17Application.class, args);

        SpringApplication app = new SpringApplication(Springboot3Java17Application.class);

        // customize start up here
        app.setLogStartupInfo(false);
        app.run(args);

    }

}
