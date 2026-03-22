package fr.laeti.portfolioapi.project.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProjectSummaryDTO {

    private String name;
    private String description;
    private List<String> technologies;
    private boolean actif;
    private boolean termine;
    private String pushedAt;
    private String screenshot;
    private boolean visible;
}