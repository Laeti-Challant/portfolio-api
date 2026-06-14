package fr.laeti.portfolioapi.contact.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.laeti.portfolioapi.contact.dto.ContactRequestDTO;
import fr.laeti.portfolioapi.contact.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<Void> sendMessage(@Valid @RequestBody ContactRequestDTO request) {
        contactService.send(request);
        return ResponseEntity.noContent().build();
    }
}
