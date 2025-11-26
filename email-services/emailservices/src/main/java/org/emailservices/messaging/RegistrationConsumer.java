package org.emailservices.messaging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.emailservices.entity.EmailSender;
import org.emailservices.repository.EmailSenderRepository;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegistrationConsumer {

    private static final Logger LOG = Logger.getLogger(RegistrationConsumer.class);

    @Inject
    ObjectMapper objectMapper;

    @Inject
    EmailSenderRepository repository;

    @Incoming("registration-in")
    @Transactional
    public void consume(String messageJson) {
        try {
            JsonNode node = objectMapper.readTree(messageJson);

            String action = node.path("action").asText();
            String email = node.path("email").asText();
            String policyCode = node.path("policyCode").asText();

            // Mock email sending: selalu SUCCESS
            LOG.infof("Mock send email to %s for policy %s, action=%s",
                    email, policyCode, action);

            EmailSender entity = new EmailSender();
            entity.setEmail(email);
            entity.setPolicyCode(policyCode);
            entity.setAction(action);
            entity.setStatus("SUCCESS");

            repository.persist(entity);
        } catch (Exception e) {
            LOG.error("Failed to process message: " + messageJson, e);
        }
    }
}