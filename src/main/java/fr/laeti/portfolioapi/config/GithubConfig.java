package fr.laeti.portfolioapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "github.api")
public class GithubConfig {

	private String baseUrl;
    private String username;
    private String token;

    @Bean
    public WebClient githubWebClient() {
        return WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader("Authorization", "Bearer " + token)
            .defaultHeader("Accept", "application/vnd.github.v3+json")
            .build();
        }
}
