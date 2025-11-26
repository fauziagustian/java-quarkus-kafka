package org.emailservices.repository;

import org.emailservices.entity.EmailSender;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmailSenderRepository implements PanacheRepository<EmailSender> {
}
