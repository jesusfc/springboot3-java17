package com.jesusfc.springboot3java17.configuration.YamlfileConfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties()
@PropertySource(value = "classpath:application-${spring.profiles.active}.yml", factory = YamlPropertySourceFactory.class)
@Data
public class YAMLConfig {

    private String name;
    private String environment;
    private boolean enabled;
    private List<String> servers = new ArrayList<>();
    private Map<String, Map<String, String>> defaultPropertyStyle;
}
