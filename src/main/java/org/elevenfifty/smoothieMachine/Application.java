package org.elevenfifty.smoothieMachine;


import org.h2.server.web.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;




@SpringBootApplication
@EnableAutoConfiguration
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		log.info("Running");
	}
	
		@Bean
		ServletRegistrationBean h2servletRegistration() {
			ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
			registrationBean.addUrlMappings("/console/*");
			return registrationBean;
		}

		@Bean
		public SpringSecurityDialect securityDialect() {
			return new SpringSecurityDialect();
		}
		
	}


