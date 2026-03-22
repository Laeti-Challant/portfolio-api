package fr.laeti.portfolioapi.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepo {

    private String name;
    private String description;
    private String language;

    @JsonProperty("html_url")
    private String htmlUrl;

    private String homepage;

    @JsonProperty("pushed_at")
    private String pushedAt;

    @JsonProperty("created_at")
    private String createdAt;

    private boolean fork;

    @JsonProperty("private")
    private boolean isPrivate;
}