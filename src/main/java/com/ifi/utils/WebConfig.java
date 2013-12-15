package com.ifi.utils;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ifi.*", useDefaultFilters = false, includeFilters = @Filter(Controller.class))
public class WebConfig extends WebMvcConfigurerAdapter {
  
    @Override
    public void configureDefaultServletHandling(
                    DefaultServletHandlerConfigurer configurer) {
            configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/").setViewName("welcome");
            registry.addViewController("/articles").setViewName("articles");
    }

    @Bean(name="tilesViewResolver")
    public ViewResolver getTilesViewResolver() {
            TilesViewResolver resolver = new TilesViewResolver();
            resolver.setContentType("text/html");
            return resolver;
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
    
    @Override
    public void configureMessageConverters(
                    List<HttpMessageConverter<?>> converters) {

            // List is initially empty. Create and configure what we need.
            MappingJacksonHttpMessageConverter jmc = new MappingJacksonHttpMessageConverter();
            jmc.setPrettyPrint(true);
            converters.add(jmc);

            Jaxb2RootElementHttpMessageConverter j2 = new Jaxb2RootElementHttpMessageConverter();
            converters.add(j2);
            return;
    }
}
