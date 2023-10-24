package com.jesusfc.springboot3java17.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;


// This class is used to configure the web application
@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Bean
    // Create a new ResourceBundleMessageSource object
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();

        // Set the basename of the resource bundle
        source.setBasenames("i18n/messages");

        // Set the flag to use the code as the default message
        source.setUseCodeAsDefaultMessage(true);

        // Return the ResourceBundleMessageSource object
        return source;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(new Locale("es", "ES"));
        return sessionLocaleResolver;
    }

    /**
     * Cada vez que se pase por método Get el param "lang"
     * se ejecutará el Interceptor. Registramos el interceptor
     * en el método addInterceptors.
     */
    @Bean
    // Create a new LocaleChangeInterceptor object
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();

        // Set the parameter name to "lang"
        localeChangeInterceptor.setParamName("lang");

        // Return the LocaleChangeInterceptor object
        return localeChangeInterceptor;
    }

    @Override
    // This method adds an interceptor to the registry
    public void addInterceptors(InterceptorRegistry registry) {
        // The interceptor being added is the localeChangeInterceptor
        registry.addInterceptor(localeChangeInterceptor());
    }

}
