package it.engineering.aleksandar.jovanov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import it.engineering.aleksandar.jovanov.logging.LoggingInterceptor;

@Configuration
@ComponentScan(basePackages = {
		"it.engineering.aleksandar.jovanov.dao",
		"it.engineering.aleksandar.jovanov.service",
		"it.engineering.aleksandar.jovanov.mapper",
		"it.engineering.aleksandar.jovanov.config",
		"it.engineering.aleksandar.jovanov.Logger"
})
@EnableAspectJAutoProxy
public class ServiceBEConfig {

	@Bean
    public LoggingInterceptor loggingInterceptor() {
        return new LoggingInterceptor();
    }
}
