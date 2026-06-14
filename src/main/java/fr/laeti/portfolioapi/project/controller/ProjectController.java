package fr.laeti.portfolioapi.project.controller;

import fr.laeti.portfolioapi.project.dto.ProjectDetailDTO;
import fr.laeti.portfolioapi.project.dto.ProjectSummaryDTO;
import fr.laeti.portfolioapi.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectSummaryDTO>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProjectDetailDTO> getProjectByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(projectService.getProjectByName(name));
    }
}
