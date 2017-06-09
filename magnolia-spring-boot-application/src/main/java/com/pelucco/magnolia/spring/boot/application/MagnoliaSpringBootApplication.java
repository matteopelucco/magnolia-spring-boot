package com.pelucco.magnolia.spring.boot.application;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MagnoliaSpringBootApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MagnoliaSpringBootApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MagnoliaSpringBootApplication.class);
    }
	
	@Bean
	public EmbeddedServletContainerFactory servletContainerFactory() {
	    return new TomcatEmbeddedServletContainerFactory() {
	        @Override
	        protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
	            try {
	                String webappDirLocation = "../magnolia-spring-boot-webapp/target";
	                tomcat.addWebapp("/magnoliaAuthor", new
	                        File(webappDirLocation + "/magnolia-spring-boot-webapp-1.0-SNAPSHOT.war").getAbsolutePath());
	                tomcat.addWebapp("/magnoliaPublic",new
	                        File(webappDirLocation + "/magnolia-spring-boot-webapp-1.0-SNAPSHOT.war").getAbsolutePath());
	            } catch (ServletException ex) {
	                throw new IllegalStateException("Failed to add webapp", ex);
	            }
	            return super.getTomcatEmbeddedServletContainer(tomcat);
	        }

	    };
	}
	
}
