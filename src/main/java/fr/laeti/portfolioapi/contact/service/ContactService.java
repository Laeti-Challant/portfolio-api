package fr.laeti.portfolioapi.contact.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import fr.laeti.portfolioapi.contact.dto.ContactRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactService {

    private final JavaMailSender mailSender;

    @Value("${mail.recipient}")
    private String recipient;

    public void send(ContactRequestDTO request) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setTo(recipient);
            helper.setReplyTo(request.email());
            helper.setSubject("[Portfolio] Message de " + request.name());
            helper.setText(
                "Nom : " + request.name() + "\n" +
                "Email : " + request.email() + "\n\n" +
                request.message()
            );

            mailSender.send(mimeMessage);
            log.info("Email de contact envoyé depuis {}", request.email());

        } catch (MessagingException ex) {
            log.error("Erreur de construction du message : {}", ex.getMessage());
            throw new RuntimeException("Impossible de construire l'email", ex);
        } catch (MailException ex) {
            log.error("Échec de l'envoi de l'email de contact : {}", ex.getMessage());
            throw ex;
        }
    }
}
