package com.nosorio.xmentor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "com.nosorio.xmentor.repositories")
@EnableNeo4jRepositories(basePackages = "com.nosorio.xmentor.graphrepositories")
public class XMentorApplication {

	@Bean public RestTemplate restTemplate(){ return new RestTemplate(); }
	@Bean public WebClient webClient(){ return WebClient.builder().build(); }

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**").allowedOrigins("http://localhost:3000").allowCredentials(true);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(XMentorApplication.class, args);
	}

}
