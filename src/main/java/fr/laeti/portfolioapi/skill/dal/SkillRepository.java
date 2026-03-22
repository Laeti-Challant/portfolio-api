package fr.laeti.portfolioapi.skill.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.laeti.portfolioapi.skill.bo.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

}
