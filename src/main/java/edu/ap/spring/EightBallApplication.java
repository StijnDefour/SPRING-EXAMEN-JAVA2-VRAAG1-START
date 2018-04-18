package edu.ap.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EightBallApplication {
	
	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return (args) -> {};
    }
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(EightBallApplication.class).headless(false).run(args);
	}
}