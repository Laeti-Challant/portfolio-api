package fr.laeti.portfolioapi.contact.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContactRequestDTO(

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 100)
    String name,

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email n'est pas valide")
    String email,

    @NotBlank(message = "Le message est obligatoire")
    @Size(max = 2000, message = "Le message ne peut pas dépasser 2000 caractères")
    String message

) {}
