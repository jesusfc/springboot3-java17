package com.jesusfc.springboot3java17.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}")
                                 String appDesciption,
                                 @Value("${application-version}")
                                 String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("SpringBoot Open application API")
                        .version(appVersion)
                        .description(appDesciption)
                        .termsOfService("http:/www.jesusfc.com")
                        .version("1.0")
                        .contact(new Contact().
                                name("Jesús Fdez. Caraballo")
                                .email("jfcaraballo@gmail.com"))
                        .license(new License().
                                name("Apache 2.0").
                                url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("OpenApi-public")
                .pathsToMatch("/rest/**")
                .build();
    }
    /*
    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("OpenApi-admin")
                .pathsToMatch("/admin/**")
                .addMethodFilter(method -> method.isAnnotationPresent(Admin.class))
                .build();
    }
    */
}
