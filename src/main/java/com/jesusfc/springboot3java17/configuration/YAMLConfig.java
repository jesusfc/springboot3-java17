package com.jesusfc.springboot3java17.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
/*
@ConfigurationProperties(prefix = "yaml")
@PropertySource(value = "classpath:foo.yml", factory = YamlPropertySourceFactory.class)
 */
@Data
public class YAMLConfig {

    private String name;
    private String environment;
    private boolean enabled;
    private List<String> servers = new ArrayList<>();
    private Map<String, Map<String, String>> defaultPropertyStyle;
}
