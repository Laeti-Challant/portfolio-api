package fr.laeti.portfolioapi.skill.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import fr.laeti.portfolioapi.skill.dal.SkillRepository;
import fr.laeti.portfolioapi.skill.dto.SkillDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class SkillService {

	private final SkillRepository skillRepository;

    @Cacheable("skills")
    public List<SkillDTO> getAllSkills() {
        log.info("Chargement des compétences depuis PostgreSQL");
        return skillRepository.findAll()
                .stream()
                .map(skill -> SkillDTO.builder()
                        .categorie(skill.getCategorie())
                        .competences(skill.getCompetences())
                        .build())
                .toList();
    }
}
