package fr.laeti.portfolioapi.skill.bo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "skills")
@Data
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String categorie;

	@ElementCollection
	@CollectionTable(name = "skill_competences", joinColumns = @JoinColumn(name = "skill_id"))
	@Column(name = "competence")
	private List<String> competences;
}
