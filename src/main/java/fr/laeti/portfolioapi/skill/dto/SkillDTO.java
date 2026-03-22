package fr.laeti.portfolioapi.skill.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SkillDTO {

    private String categorie;
    private List<String> competences;
}