package fr.laeti.portfolioapi.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PortfolioMetaDTO {

    private boolean visible;
    private boolean actif;
    private boolean termine;
    private String contexte;
    private String screenshot;
    private List<String> technologies;
    private List<String> competences;
}