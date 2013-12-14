package com.ifi.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc

public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void configureDefaultServletHandling(
                    DefaultServletHandlerConfigurer configurer) {
            configurer.enable();
    }
    
    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true).
            ignoreAcceptHeader(true).
            useJaf(false).
            defaultContentType(MediaType.TEXT_HTML).
            mediaType("html", MediaType.TEXT_HTML).
            mediaType("xml", MediaType.APPLICATION_XML).
            mediaType("json", MediaType.APPLICATION_JSON);
        System.out.println("The configurer is a " + configurer.getClass());
    }
}
