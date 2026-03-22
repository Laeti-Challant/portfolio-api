package fr.laeti.portfolioapi.project.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProjectDetailDTO {

    private String name;
    private String description;
    private List<String> technologies;
    private List<String> competences;
    private boolean actif;
    private boolean termine;
    private String contexte;
    private String pushedAt;
    private String screenshot;
    private String repoUrl;
    private String siteUrl;
    private String readme;
}