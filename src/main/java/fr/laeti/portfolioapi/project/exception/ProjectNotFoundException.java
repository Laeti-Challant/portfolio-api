package fr.laeti.portfolioapi.project.exception;

public class ProjectNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProjectNotFoundException(String name) {
        super("Projet introuvable : " + name);
    }
}
