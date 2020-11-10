package com.bandmeetup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * Class that assists in the configuration of MVC, it also is used to configure thymeleaf more.
 * Language: Java 13
 * Framework: Spring
 * Author: Stephen Cook <sjc5897@rit.edu>
 * Created: 10/21/20
 */

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * Registration of controllers, done via this tutorial: https://www.baeldung.com/spring-mvc-tutorial'
     * Note the tutorial uses jsp, not thymeleaf
     * @param registry A ViewController Registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/profile").setViewName("profile");
        registry.addViewController("/search").setViewName("search");
    }

    /**
     * Sets up a the systems templating engine: https://www.baeldung.com/spring-template-engines#2-spring-configuration
     * @return SpringTemplateEngine a new template engine
     */
    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine =  new SpringTemplateEngine();
        templateEngine.setTemplateResolver(thymeleafTemplateResolver());
        return templateEngine;
    }

    /**
     * Configues a Thymeleaf Template Resolver to our file structure
     * @return
     */
    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        return templateResolver;
    }
    @Bean
    /**
     * Creates a Thymeleaf view resolver
     */
    public ThymeleafViewResolver thymeleafViewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }

    /**
     * Configures our resource location for css files.
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**", "/css/**")
                .addResourceLocations("/WEB-INF/resources/", "/WEB-INF/css/");
    }
}
