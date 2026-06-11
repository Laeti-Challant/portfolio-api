package fr.laeti.portfolioapi.project.service;

import java.util.List;

import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import fr.laeti.portfolioapi.project.client.GithubClient;
import fr.laeti.portfolioapi.project.dto.PortfolioMetaDTO;
import fr.laeti.portfolioapi.project.dto.ProjectDetailDTO;
import fr.laeti.portfolioapi.project.dto.ProjectSummaryDTO;
import fr.laeti.portfolioapi.project.exception.ProjectNotFoundException;
import fr.laeti.portfolioapi.project.model.GithubRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final GithubClient githubClient;
    private final ObjectMapper objectMapper;

    // ─── Liste des projets (cartes) ───────────────────────────────────────────

    @Cacheable("projects")
    public List<ProjectSummaryDTO> getAllProjects() {
        log.info("Chargement de la liste des projets depuis GitHub");
        return githubClient.getAllRepos().stream()
                .filter(repo -> !repo.isFork())         // on exclut les forks
                .map(this::toSummary)                   // on convertit chaque repo
                .filter(ProjectSummaryDTO::isVisible)   // on exclut les non visibles
                .toList();
    }

    // ─── Détail d'un projet ───────────────────────────────────────────────────

    @Cacheable(value = "projectDetail", key = "#name")
    public ProjectDetailDTO getProjectByName(String name) {
        log.info("Chargement du détail du projet : {}", name);

        GithubRepo repo = githubClient.getAllRepos().stream()
                .filter(r -> r.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new ProjectNotFoundException(name) );

        PortfolioMetaDTO meta = fetchMeta(repo.getName());
        String readmeHtml = convertReadme(githubClient.getReadme(repo.getName()));

        return ProjectDetailDTO.builder()
                .name(repo.getName())
                .description(repo.getDescription())
                .technologies(meta.getTechnologies())
                .competences(meta.getCompetences())
                .actif(meta.isActif())
                .termine(meta.isTermine())
                .contexte(meta.getContexte())
                .pushedAt(repo.getPushedAt())
                .screenshot(meta.getScreenshot())
                .repoUrl(repo.getHtmlUrl())
                .siteUrl(repo.getHomepage())
                .readme(readmeHtml)
                .build();
    }

    // ─── Méthodes privées ─────────────────────────────────────────────────────

    private ProjectSummaryDTO toSummary(GithubRepo repo) {
        PortfolioMetaDTO meta = fetchMeta(repo.getName());
        return ProjectSummaryDTO.builder()
                .name(repo.getName())
                .description(repo.getDescription())
                .technologies(meta.getTechnologies())
                .actif(meta.isActif())
                .termine(meta.isTermine())
                .pushedAt(repo.getPushedAt())
                .screenshot(meta.getScreenshot())
                .visible(meta.isVisible())
                .build();
    }

    private PortfolioMetaDTO fetchMeta(String repoName) {
        String json = githubClient.getPortfolioMeta(repoName);
        if (json == null || json.isBlank()) {
            return new PortfolioMetaDTO(); // valeurs par défaut si pas de portfolio.json
        }
        try {
            return objectMapper.readValue(json, PortfolioMetaDTO.class);
        } catch (Exception e) {
            log.warn("portfolio.json invalide pour le repo {} : {}", repoName, e.getMessage());
            return new PortfolioMetaDTO();
        }
    }

    private String convertReadme(String markdown) {
        if (markdown == null || markdown.isBlank()) return "";
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(parser.parse(markdown));
    }
}