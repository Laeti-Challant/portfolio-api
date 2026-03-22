package fr.laeti.portfolioapi.project.client;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import fr.laeti.portfolioapi.project.model.GithubRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class GithubClient {

    private final WebClient githubWebClient;

    // Récupère tous les repos publics
    public List<GithubRepo> getAllRepos() {
        log.info("Appel GitHub : récupération des repos");
        return githubWebClient
                .get()
                .uri("/users/laeti-challant/repos?per_page=100&sort=pushed")
                .retrieve()
                .bodyToFlux(GithubRepo.class)
                .collectList()
                .block();
    }

    // Récupère le README d'un repo en Markdown brut
    public String getReadme(String repoName) {
        log.info("Appel GitHub : récupération du README de {}", repoName);
        return githubWebClient
                .get()
                .uri("/repos/laeti-challant/{repo}/readme", repoName)
                .header("Accept", "application/vnd.github.raw+json")
                .retrieve()
                .bodyToMono(String.class)
                .onErrorReturn("") // si pas de README, retourne une chaîne vide
                .block();
    }

    // Récupère le portfolio.json d'un repo
    public String getPortfolioMeta(String repoName) {
        log.info("Appel GitHub : récupération du portfolio.json de {}", repoName);
        return githubWebClient
                .get()
                .uri("/repos/laeti-challant/{repo}/contents/portfolio.json", repoName)
                .header("Accept", "application/vnd.github.raw+json")
                .retrieve()
                .bodyToMono(String.class)
                .onErrorReturn("") // si pas de portfolio.json, retourne une chaîne vide
                .block();
    }
}